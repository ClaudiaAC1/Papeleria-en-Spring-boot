package com.papeleria.v1.papeleria_v1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Float cantidad; 
    private Float  precioProvedor; 
    private Float  precioPublico; 
   

    
    public Producto(){}

    
    public Producto(Integer id, String nombre, Float cantidad, Float precioProvedor, Float precioPublico) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioProvedor = precioProvedor;
        this.precioPublico = precioPublico;
    }


    public Producto(String nombre, Float cantidad, Float precioProvedor, Float precioPublico) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioProvedor = precioProvedor;
        this.precioPublico = precioPublico;
    }
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void restarCantidad(Float existencia) {
        this.cantidad -= existencia;
    }
    public Float getCantidad(){
        return cantidad;
    }

    public String getNombre(){
        return nombre;
    }

    public Float getPrecioProvedor(){
        return precioProvedor;
    }

    public Float getPrecioPublico(){
        return precioPublico;
    }

    

    public void setCantidad(Float cantidad){
        this.cantidad=cantidad;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setPrecioProvedor(Float precioProvedor){
        this.precioProvedor=precioProvedor;
    }

    public void setPrecioPublico(Float precioPublico){
        this.precioPublico=precioPublico;
    }

    
}
