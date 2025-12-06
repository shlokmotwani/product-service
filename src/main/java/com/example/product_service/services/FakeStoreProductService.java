package com.example.product_service.services;

import com.example.product_service.dtos.FakeStoreProductDto;
import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.models.Category;
import com.example.product_service.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductService implements IProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto dto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if(dto == null){
            throw new ProductNotFoundException(String.format("Product with id: %s does not exist in the DB", id));
        }
        return convertFakeStoreProductDtoToProduct(dto);


//        System.out.println("THIS LINE IS EXECUTED");
//        throw new RuntimeException();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> listOfProducts = new ArrayList<>();
        for(FakeStoreProductDto dto: productDtos){
            listOfProducts.add(convertFakeStoreProductDtoToProduct(dto));
        }
        return listOfProducts;
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
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        return;
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto dto){
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
