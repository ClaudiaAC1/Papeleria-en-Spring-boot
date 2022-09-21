package com.papeleria.v1.papeleria_v1.Repository;

import org.springframework.data.repository.CrudRepository;

import com.papeleria.v1.papeleria_v1.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>{
   public Producto findProductoByNombre(String nombre);
}
