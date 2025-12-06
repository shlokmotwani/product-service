package com.example.product_service.repositories;

import com.example.product_service.models.Product;
import com.example.product_service.projections.ProductWithTitleAndPrice;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    @Override
    void deleteById(Long aLong);

    // @Query(value = "SELECT p.title AS title, p.price AS price FROM Product p", nativeQuery = true)
    // List<ProductWithTitleAndPrice> getAllProductWithTitleAndPrice();
}
