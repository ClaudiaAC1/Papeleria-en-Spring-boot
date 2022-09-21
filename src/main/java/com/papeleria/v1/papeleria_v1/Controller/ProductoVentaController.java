package com.papeleria.v1.papeleria_v1.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.papeleria.v1.papeleria_v1.Entity.ProductoParaVender;
import com.papeleria.v1.papeleria_v1.Repository.ProductoRepository;
import com.papeleria.v1.papeleria_v1.Repository.ProductoVentaRepository;
import com.papeleria.v1.papeleria_v1.Repository.VentaRepository;
import com.papeleria.v1.papeleria_v1.model.Producto;
import com.papeleria.v1.papeleria_v1.model.ProductoVendido;
import com.papeleria.v1.papeleria_v1.model.VentaModel;

@Controller

public class ProductoVentaController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoVentaRepository productoVentaRepository;

    @GetMapping("/vender")
    public String interfazVender(Model model, HttpServletRequest request) {
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender p: carrito) total += p.getTotal();
        model.addAttribute("total", total);
        return "Ventas/vender";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request,
            RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        
       Producto productoPorNombre =  productoRepository.findProductoByNombre(producto.getNombre());
        if (productoPorNombre == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el nombre " + producto.getNombre() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender";
        }

        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getNombre().equals(productoPorNombre.getNombre())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(productoPorNombre.getId(),productoPorNombre.getNombre(), productoPorNombre.getCantidad(), productoPorNombre.getPrecioProvedor(), productoPorNombre.getPrecioPublico(), 1f));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/vender/";

    }

    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        
        return "redirect:/vender/";
    }

   
    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }

    
    @PostMapping(value = "/terminar")
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        // Si no hay carrito o está vacío, regresamos inmediatamente
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/vender/";
        }
        
        VentaModel v = ventaRepository.save(new VentaModel());
        // Recorrer el carrito
        for (ProductoParaVender productoParaVender : carrito) {
            // Obtener el producto fresco desde la base de datos
            Producto p = productoRepository.findById(productoParaVender.getId()).orElse(null);
            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            // Le restamos existencia
            p.restarCantidad(productoParaVender.getCantidad());
            // Lo guardamos con la existencia ya restada
            productoRepository.save(p);
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(), productoParaVender.getPrecioPublico(), productoParaVender.getNombre(), v);
            // Y lo guardamos
            productoVentaRepository.save(productoVendido);
        }

        // Al final limpiamos el carrito
        this.limpiarCarrito(request);
        // e indicamos una venta exitosa
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }

   
    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }   

    /**
     * @param carrito
     * @param request
     */
    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }

    /**
     * @param request
     * @return
     */
    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession()
                .getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

}
