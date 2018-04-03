package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
@Entity
@Table(name = "PRODUCTCATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ProductCategory() {
    }


}
