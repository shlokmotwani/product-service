package com.example.product_service.services;

import com.example.product_service.dtos.ProductPatchDTO;
import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Product;
import com.example.product_service.projections.ProductWithTitleAndPrice;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    Product patchProduct(Long id, ProductPatchDTO productPatchDTO) throws ProductNotFoundException;

    void deleteProduct(Long id);

//    List<ProductWithTitleAndPrice> getAllProductsWithTitleAndPrice();
}
