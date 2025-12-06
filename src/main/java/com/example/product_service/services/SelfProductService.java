package com.example.product_service.services;

import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Category;
import com.example.product_service.models.Product;
import com.example.product_service.repositories.CategoryRepository;
import com.example.product_service.repositories.ProductRepository;
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
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product editProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
