package com.papeleria.v1.papeleria_v1.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.papeleria.v1.papeleria_v1.Entity.FechaYhora;

@Entity
@Table(name = "Venta")
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fecha;
    private String descripcion;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ProductoVendido> productos;

    public VentaModel(){
        this.fecha = FechaYhora.obtenerFechaYHoraActual();
    }
       
    public VentaModel(Integer id, String fecha, String descripcion, Set<ProductoVendido> productos) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.productos = productos;
    }

    public VentaModel(String fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Float getTotal() {
        Float total=0.0f;
        for (ProductoVendido productoVendido : this.productos) {
            total += productoVendido.getTotal();
        }
        return total;
    }
   
    public Set<ProductoVendido> getProductos() {
        return productos;
    }
    public void setProductos(Set<ProductoVendido> productos) {
        this.productos = productos;
    }
  
}
