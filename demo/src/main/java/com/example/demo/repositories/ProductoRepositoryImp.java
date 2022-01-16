package com.example.demo.repositories;
import com.example.demo.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ProductoRepositoryImp implements ProductoRepository{

    @Autowired
    private Sql2o sql2o;
    
    @Override
    public Long countProducto(){
        String query = "select count(*) from producto";
        Connection conn = sql2o.open();
        Long resultado = (Long) conn.createQuery(query,true).executeAndFetchFirst(Long.class);
        return resultado + 1; 
    }

    @Override
    public Producto createProducto(Producto producto){
        Long id_prueba = countProducto();
        String query = "INSERT into producto (id,codigo,nombre,fecha_v,precio,categoria) values (:id, :codigo, :nombre, :fecha_v, :precio, :categoria)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true).addParameter("id",id_prueba)
                    .addParameter("codigo", producto.getCodigo())
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("fecha_v", producto.getFecha_V())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("categoria",producto.getCategoria())
                    .executeUpdate().getKey();
                    
            producto.setId(id_prueba);
            return producto;
        } 
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    @Override
    public Producto getProducto(Long id){
        String query = "select * from producto where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Producto.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Producto> getProductos(){
        String query = "select * from Producto where  deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Producto.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteProducto(Long id){
         String query = "update Producto set deleted = true where id = :id and deleted = false";
         try(Connection conn = sql2o.open()){
            id = conn.createQuery(query).addParameter("id",id).executeUpdate().getKey(Long.class);
         }
         catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
     }

    @Override 
    public Producto updateProducto(Producto Producto, Long id){
        String query = "update Producto set codigo = :codigo, nombre = :nombre, fecha_v = :fecha_v, precio = :precio, categoria = :categoria where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query)
                            .addParameter("id", id)
                            .addParameter("codigo", Producto.getCodigo())
                            .addParameter("nombre", Producto.getNombre())
                            .addParameter("fecha_v", Producto.getFecha_V())
                            .addParameter("id_estado", Producto.getPrecio())
                            .addParameter("cant_voluntarios", Producto.getCategoria())
                            .executeUpdate().getKey(Long.class);
            Producto.setId(insertedid);
            return Producto;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
