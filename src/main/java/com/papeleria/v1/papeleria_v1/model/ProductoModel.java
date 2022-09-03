package com.papeleria.v1.papeleria_v1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producto")

public class ProductoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private int cantidad; 
    private double  precioProvedor; 
    private double  precioPublico; 
    private String url_img;

    
    public ProductoModel(){}

    public ProductoModel(String nombre, int cantidad, double precioProvedor, double precioPublico, String url_img) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioProvedor = precioProvedor;
        this.precioPublico = precioPublico;
        this.url_img = url_img;
    }
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public int getCantidad(){
        return cantidad;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecioProvedor(){
        return precioProvedor;
    }

    public double getPrecioPublico(){
        return precioPublico;
    }

    public String getUrl_img(){
        return url_img;
    }

    public void setCantidad(final int cantidad){
        this.cantidad=cantidad;
    }

    public void setNombre(final String nombre){
        this.nombre=nombre;
    }

    public void setPrecioProvedor(final double precioProvedor){
        this.precioProvedor=precioProvedor;
    }

    public void setPrecioPublico(final double precioPublico){
        this.precioPublico=precioPublico;
    }

    public void setUrl_img(final String url_img){
        this.url_img=url_img;
    }
   
}
