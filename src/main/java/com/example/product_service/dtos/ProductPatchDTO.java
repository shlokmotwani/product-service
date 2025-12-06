package com.example.product_service.dtos;

import com.example.product_service.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPatchDTO {
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;
}
