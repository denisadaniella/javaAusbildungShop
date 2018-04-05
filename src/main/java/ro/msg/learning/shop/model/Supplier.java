package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
@Entity
@Table(name = "SUPPLIER", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products;

    public Supplier(String name) {
        this.name = name;
    }

}
