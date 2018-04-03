package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(exclude = {"order", "product"})
@Entity
@Table(name = "ORDERDETAIL", uniqueConstraints = @UniqueConstraint(columnNames = {"orderid", "productid"}))
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;

    @NonNull
    @Column(nullable = false)
    private int quantity;

    protected OrderDetail() {

    }

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
