package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "weight")
    private double weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productcategoryid", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    private Supplier supplier;


    public Product(String name, String description, BigDecimal price, double weight, ProductCategory productCategory, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    protected Product() {
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', description='%s', price='%.2f', weight='%.2f', productcategoryid=%d, supplierid=%d]",
                id, name, description, price, weight, productCategory.getId(), supplier.getId());
    }

}
