package ro.msg.learning.model;

import lombok.Data;

@Data
public class ProductCategory {

    private int productCategoryId;

    private String name;

    private String description;

    public ProductCategory(int productCategoryId, String name, String description) {
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.description = description;
    }
}
