package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = {"product", "location"})
@EqualsAndHashCode(exclude = {"product", "location"})
@Entity
@Table(name = "STOCK", uniqueConstraints = @UniqueConstraint(columnNames = {"productid", "locationid"}))
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @NonNull
    @Column(nullable = false)
    private Integer quantity;
}
