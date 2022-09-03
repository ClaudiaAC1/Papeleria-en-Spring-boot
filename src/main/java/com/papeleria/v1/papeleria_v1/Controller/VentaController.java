package com.papeleria.v1.papeleria_v1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papeleria.v1.papeleria_v1.Repository.VentaRepository;
import com.papeleria.v1.papeleria_v1.model.VentaModel;

@RestController
@RequestMapping("/v1/venta")
public class VentaController{
    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Lista de Ventas
     * @return
     */
    @GetMapping(path = "/")
    public Iterable<VentaModel> getVenta(){
       return  ventaRepository.findAll();

    }
    /**
     * Agregar Venta
     * @param venta
     * @return
     */
    @PostMapping("/")
    public String addVenta(@RequestBody VentaModel venta){       
        ventaRepository.save(venta);

        return "AGREGADO";
    }

    @PutMapping(path = "/update/{id}")
    public String updateVenta(@RequestBody VentaModel venta, @PathVariable Integer id){
        VentaModel v = ventaRepository.findById(id).get();

        v.setFecha(venta.getFecha());
        v.setDescripcion(venta.getDescripcion());
        v.setTotal(venta.getTotal());
        v.setProductos(venta.getProductos());
       
        ventaRepository.save(v);
        return "ACTUALIZADO CORRACTAMENTE";
    }
}

