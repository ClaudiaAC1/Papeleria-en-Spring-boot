package com.papeleria.v1.papeleria_v1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProductoV")
public class ProductoVendido {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Float cantidad;
    private Float precio;
    private String nombre;
    
    @ManyToOne
    @JoinColumn
    private VentaModel venta;

    public ProductoVendido(){}
    
    
    public ProductoVendido(Integer id, Float cantidad, Float precio, String nombre, VentaModel venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
        this.venta = venta;
    }


    public ProductoVendido(Float cantidad, Float precio, String nombre, VentaModel venta) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
        this.venta = venta;
    }

    public Float getTotal(){
        return this.cantidad * this.precio;
    }
    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public VentaModel getVenta() {
        return venta;
    }

    public void setVenta(VentaModel venta) {
        this.venta = venta;
    }

    
}
