package com.example.demo.models;
import java.util.Date;

public class Producto {
    private Long id;
    private Long codigo;
    private String nombre;
    private Date fecha_v;
    private float precio;
    private String categoria;
    private boolean deleted;

    //GETTERS

    public Long getId(){
        return id;
    }
    
    public Long getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public Date getFecha_V(){
         return fecha_v;
    }

    public float getPrecio(){
        return precio;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public boolean getDeleted(){
        return deleted;
    }
    
    //SETTERS
    
    public void setId(Long id){
        this.id = id;
    }
    
    public void setCodigo(Long cod){
        this.codigo = cod;
    }
    
    public void setNombre(String nom){
        this.nombre = nom;
    }

    public void setFecha_V (Date fechaV){
        this.fecha_v = fechaV;
    }

    public void setPrecio(Float precioNew){
        this.precio = precioNew;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setDeleted(boolean estado){
        this.deleted = estado;
    }
}