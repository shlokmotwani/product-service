package com.example.product_service.controllers;

import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Product;
import com.example.product_service.services.IProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return this.productService.getProductById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> response = null;
        try {
            List<Product> products = this.productService.getAllProducts();
            response = new ResponseEntity<>(products,
                    HttpStatus.OK);

        }
        catch(Exception e){
            response = new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping
    public Product createProduct(){
        return null;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id){
        return null;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(Long id){
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long id){

    }
}
