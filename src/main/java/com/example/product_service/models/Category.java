package com.example.product_service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "categories")
public class Category extends BaseModel{
    private String name;

//    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
//    private List<Product> products;
}
