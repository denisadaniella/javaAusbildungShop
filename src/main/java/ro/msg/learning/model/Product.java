package ro.msg.learning.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "weight")
    private double weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productcategoryid", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;


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
        if (productCategory != null && supplier != null) {
            return String.format(
                    "Product[id=%d, name='%s', description='%s', price='%.2f', weight='%.2f', productcategoryid=%d, supplierid=%d] ",
                    id, name, description, price, weight, productCategory.getId(), supplier.getId());
        } else {
            return String.format(
                    "Product[id=%d] ", id);
        }
    }

    @Override
    public int hashCode() {
        if (name == null || description == null || price == null || productCategory == null || supplier == null)
            return Objects.hash(id);

        return Objects.hash(id, name, description, price, weight, productCategory.getId(), supplier.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
