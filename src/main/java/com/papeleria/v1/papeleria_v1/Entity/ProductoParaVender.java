package com.papeleria.v1.papeleria_v1.Entity;

import com.papeleria.v1.papeleria_v1.model.Producto;

public class ProductoParaVender extends Producto{
    private Float cantidad2;

    

    public ProductoParaVender(Integer id, String nombre, Float cantidad, Float precioProvedor, Float precioPublico, Float c) {
        super(id, nombre, cantidad, precioProvedor, precioPublico);
       this. cantidad2 = c;
    }

    public ProductoParaVender( String nombre, Float cantidad, Float precioProvedor, Float precioPublico, Float c) {
        super(nombre, cantidad, precioProvedor, precioPublico);
       this. cantidad2 = c;
    }


    public void aumentarCantidad() {
        this.cantidad2++;
    }

    public Float getCantidad() {
        return cantidad2;
    }

    public Float getTotal() {
        return this.getPrecioPublico() * this.cantidad2;
    }
}
