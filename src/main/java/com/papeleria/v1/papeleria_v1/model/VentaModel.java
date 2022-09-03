package com.papeleria.v1.papeleria_v1.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Venta")
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fecha;
    private String descripcion;
    private float total;
    
    @ManyToMany
    @JoinTable(name="venta_productos",
    joinColumns = @JoinColumn(name= "venta_id"),
    inverseJoinColumns = @JoinColumn(name= "producto_id"))
    
    private Set<ProductoModel> productos;

    public VentaModel(){}
       
    public VentaModel(String fecha, String descripcion, float total) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.total = total;
    }

    
    public VentaModel(Integer id, String fecha, String descripcion, float total, Set<ProductoModel> productos) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.total = total;
        this.productos = productos;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Modificar la fecha para que sea automatica
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    ///////
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public Set<ProductoModel> getProductos() {
        return productos;
    }
    public void setProductos(Set<ProductoModel> productos) {
        this.productos = productos;
    }
  
}
