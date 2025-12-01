package com.example.product_service.controllers;

import com.example.product_service.models.Product;
import com.example.product_service.services.IProductService;
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
    public Product getProductById(@PathVariable("id") Long id){
        return this.productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return null;
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
