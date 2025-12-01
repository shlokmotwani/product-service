package com.example.product_service.services;

import com.example.product_service.dtos.FakeStoreProductDto;
import com.example.product_service.models.Category;
import com.example.product_service.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto dto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeStoreProductDto.class);
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    public Product convertFakeStoreDtoToProduct(FakeStoreProductDto dto){
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImage());

        Category category = new Category();
        category.setName(dto.getCategory());
        product.setCategory(category);

        return product;
    }
}
