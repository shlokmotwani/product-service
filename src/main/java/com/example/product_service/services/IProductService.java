package com.example.product_service.services;

import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
}
