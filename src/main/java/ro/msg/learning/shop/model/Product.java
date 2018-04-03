package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@ToString(exclude = {"productCategory", "supplier", "orderDetails"})
@EqualsAndHashCode(exclude = {"productCategory", "supplier", "orderDetails"})
@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;


    private String description;

    @NonNull
    @Column(nullable = false)
    private BigDecimal price;


    private double weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productcategoryid", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;


    public Product(String name, String description, BigDecimal price, double weight, ProductCategory productCategory, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    public Product() {
    }


}
