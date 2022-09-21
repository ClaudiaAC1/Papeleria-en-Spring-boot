package com.papeleria.v1.papeleria_v1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.papeleria.v1.papeleria_v1.Repository.ProductoRepository;
import com.papeleria.v1.papeleria_v1.model.Producto;

@Controller
public class ProductoController {

  @Autowired
  private ProductoRepository productoRepository;

  @GetMapping(value = "/addProduct")
  public String agregarProducto(Model model) {
    model.addAttribute("producto", new Producto());
    
    return "Productos/agregar";
  }

  /**
   * Lista de productos
   * 
   * @param modelo
   * @return vista con tabla de productos
   */
  @GetMapping("/list")
  public String getProducts(Model modelo) {
    modelo.addAttribute("productos", productoRepository.findAll());
    return "Productos/listProduct";
  }

 
  /**
   * Agregar un nuevo producto a la BD
   * 
   * @param modelo
   * @return
   */
  @PostMapping(path = "/addProduct")
  public String addProduct(@ModelAttribute @Validated Producto producto, BindingResult bindingResult,
      RedirectAttributes redirectAttrs) {
    if (bindingResult.hasErrors()) {
      return "/addProduct";
    }

    if (productoRepository.findProductoByNombre(producto.getNombre()) != null) {
      redirectAttrs
              .addFlashAttribute("mensaje", "Ya existe un producto con ese nombre")
              .addFlashAttribute("clase", "warning");
      return "redirect:/productos/addProduct";
  }

    productoRepository.save(producto);
    redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/addProduct";
  }

  /**
   * Editar producto seleccionado 
   * @param id
   * @param model
   * @return
   */
  @GetMapping(value = "/editar/{id}")
  public String formularioEditar(@PathVariable int id, Model model) {
      model.addAttribute("producto", productoRepository.findById(id).orElse(null));
      return "Productos/editar";
  }

  @PostMapping(value = "/editar/{id}")
  public String actualizarProducto(@ModelAttribute @Validated Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
    if (bindingResult.hasErrors()) {
      return "Productos/editar";
    }
    productoRepository.save(producto);
    redirectAttrs
            .addFlashAttribute("mensaje", "Editado correctamente")
            .addFlashAttribute("clase", "success");
    return "redirect:/list";
}


 

}