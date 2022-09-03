package com.papeleria.v1.papeleria_v1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papeleria.v1.papeleria_v1.Repository.ProductoRepository;
import com.papeleria.v1.papeleria_v1.model.ProductoModel;

@RestController
@RequestMapping("/v1/producto")

public class ProductoController{

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Lista de productos
     * @return 
     */
    @GetMapping(path = "/")
    public Iterable<ProductoModel> getProducts(){
        return productoRepository.findAll();        
    }

    /**
     * Agregar producto
     * @return 
     */

     @PostMapping (path ="/")
     public String addProduct(@RequestBody ProductoModel producto){
        productoRepository.save(producto);
        return "AGREGADO"; 
     }

     /**
      * Editar/Actualizar producto
      */

      @PutMapping(path = "/update/{id}")
      public String updateProduct(@RequestBody ProductoModel producto, @PathVariable Integer id){
        ProductoModel p =  productoRepository.findById(id).get();
       
        p.setNombre(producto.getNombre());
        p.setCantidad(producto.getCantidad());
        p.setUrl_img(producto.getUrl_img());
        p.setPrecioProvedor(producto.getPrecioProvedor());
        p.setPrecioPublico(producto.getPrecioPublico());

        productoRepository.save(p);
        return "ACTUALIZADO CORRACTAMENTE";
      }


}