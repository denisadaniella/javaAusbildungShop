package ro.msg.learning.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private int productId;

    private String name;

    private String description;

    private BigDecimal price;

    private double weight;

    private int category; //productCategoryId

    private int supplier; //supplierId


    public Product(int productId, String name, String description, BigDecimal price, double weight, int category, int supplier) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.supplier = supplier;
    }

}
