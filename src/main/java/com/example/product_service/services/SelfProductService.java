package com.example.product_service.services;

import com.example.product_service.dtos.ProductPatchDTO;
import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Category;
import com.example.product_service.models.Product;
import com.example.product_service.projections.ProductWithTitleAndPrice;
import com.example.product_service.repositories.CategoryRepository;
import com.example.product_service.repositories.ProductRepository;
import jakarta.persistence.Tuple;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(String.format("Product with id: %s does not exist.", id));
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        Optional<Category> categoryInDB = categoryRepository.findByName(category.getName());
        if(categoryInDB.isEmpty()){
            categoryRepository.save(category);
        }
        else{
            category = categoryInDB.get();
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(String.format("Product with id: %s does not exist.", id));
        }

        Product productInDB = productOptional.get();
        productInDB.setTitle(product.getTitle());
        productInDB.setPrice(product.getPrice());
        productInDB.setDescription(product.getDescription());
        productInDB.setImageUrl(product.getImageUrl());

        Category category = product.getCategory();
        Optional<Category> categoryInDB = categoryRepository.findByName(category.getName());
        if(categoryInDB.isEmpty()){
            categoryRepository.save(category);
        }
        else{
            category = categoryInDB.get();
        }
        productInDB.setCategory(category);
        return productRepository.save(productInDB);
    }

    @Override
    public Product patchProduct(Long id, ProductPatchDTO productPatchDTO) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(String.format("Product with id: %s does not exist.", id));
        }
        
        Product productInDB = productOptional.get();
        if(productPatchDTO.getTitle() != null){
            productInDB.setTitle(productPatchDTO.getTitle());
        }

        if(productPatchDTO.getPrice() == 0.0){
            productInDB.setPrice(productPatchDTO.getPrice());
        }

        if(productPatchDTO.getDescription() != null){
            productInDB.setDescription(productPatchDTO.getDescription());
        }

        if(productPatchDTO.getImageUrl() != null){
            productInDB.setImageUrl(productPatchDTO.getImageUrl());
        }
        
        if(productPatchDTO.getCategory() != null){
            Category category = productPatchDTO.getCategory();
            Optional<Category> categoryInDB = categoryRepository.findByName(category.getName());
            if(categoryInDB.isEmpty()){
                categoryRepository.save(category);
            }
            else{
                category = categoryInDB.get();
            }
            productInDB.setCategory(category);
        }
        return productRepository.save(productInDB);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

//    public List<ProductWithTitleAndPrice> getAllProductsWithTitleAndPrice(){
//        return productRepository.getAllProductWithTitleAndPrice();
//    }
}
