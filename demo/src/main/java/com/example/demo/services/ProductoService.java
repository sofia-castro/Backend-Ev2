package com.example.demo.services;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.models.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final Gson gson;

    ProductoService(ProductoRepository productoRepository){
        this.gson = new GsonBuilder().create();
        this.productoRepository = productoRepository;
    }

    @GetMapping("/productos/")
    ResponseEntity<String> getProductos(){
        List<Producto> productos = productoRepository.getProductos() ;
        return new ResponseEntity<>(gson.toJson(productos),HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    ResponseEntity<String> getProducto(@PathVariable Long id){
        Producto productos = productoRepository.getProducto(id);
        if(productos != null){
            return new ResponseEntity<>(gson.toJson(productos),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/productos/create")
    ResponseEntity<String> createProducto(@RequestBody String request){
        Producto tarOut = gson.fromJson(request,Producto.class);
        if (tarOut != null){
            Producto tarOut2 = productoRepository.createProducto(tarOut);
            return new ResponseEntity<>(gson.toJson(tarOut2),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @CrossOrigin(origins = {"http://localhost:8081"})
    @ResponseBody
    @RequestMapping(value ="/productos/{id}",  method =RequestMethod.PUT,consumes="application/json")
    ResponseEntity<String> updateProducto(@RequestBody String request, @PathVariable Long id){
        Producto funciona=gson.fromJson(request,Producto.class);
        Producto prodOut = productoRepository.getProducto(id);
        if(prodOut != null){
            if(funciona.getCodigo() != null){
                prodOut.setCodigo(funciona.getCodigo());
            }

            if(funciona.getNombre() != null){
                prodOut.setNombre(funciona.getNombre());
            }

            if(funciona.getFecha_V() != null){
                prodOut.setFecha_V(funciona.getFecha_V());
            }
            if(funciona.getCategoria() != null){
                prodOut.setCategoria(funciona.getCategoria());
            }
            if(funciona.getPrecio()!= null){
                prodOut.setPrecio(funciona.getPrecio());
            }
            prodOut = productoRepository.updateProducto(prodOut, id);
            return new ResponseEntity<>(gson.toJson(prodOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @DeleteMapping("/productos/{id}")
    ResponseEntity<String> deleteProducto(@PathVariable Long id){
        if(productoRepository.deleteProducto(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
