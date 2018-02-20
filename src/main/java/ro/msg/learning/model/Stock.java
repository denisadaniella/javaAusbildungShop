package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STOCK", uniqueConstraints = @UniqueConstraint(columnNames = {"productid", "locationid"}))
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @Column(name = "quantity")
    private Integer quantity;

    public Stock(Product product, Location location, Integer quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }

    protected Stock() {

    }

    @Override
    public String toString() {
        return String.format(
                "Stock[id=%d, productid=%d, locationid=%d, quantity=%d]",
                id, product.getId(), location.getId(), quantity);
    }
}
