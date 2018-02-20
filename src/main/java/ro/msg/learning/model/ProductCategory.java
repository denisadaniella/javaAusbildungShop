package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCTCATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ProductCategory() {
    }

    @Override
    public String toString() {
        return String.format(
                "ProductCategory[id=%d, name='%s', description='%s']",
                id, name, description);
    }
}
