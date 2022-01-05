package com.example.demo.repositories;
import com.example.demo.models.Producto;
import java.util.List;

public interface ProductoRepository{

    public Long countProducto();
    public Producto createProducto(Producto Producto);
    public List<Producto> getProductos();  
    public Producto getProducto(Long id);
    public Producto updateProducto(Producto Producto, Long id);
    public boolean deleteProducto(Long id);
    
}