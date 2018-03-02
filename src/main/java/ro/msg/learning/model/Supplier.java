package ro.msg.learning.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "SUPPLIER", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Supplier(String name) {
        this.name = name;
    }

    protected Supplier() {

    }

    @Override
    public String toString() {
        return String.format(
                "Supplier[id=%d, name='%s']",
                id, name);
    }

}
