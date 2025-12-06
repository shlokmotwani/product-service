package com.example.product_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String imageUrl;

    @ManyToOne
    private Category category;
}
