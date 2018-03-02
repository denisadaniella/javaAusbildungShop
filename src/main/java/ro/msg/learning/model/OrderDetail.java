package ro.msg.learning.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDERDETAIL", uniqueConstraints = @UniqueConstraint(columnNames = {"orderid", "productid"}))
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;

    @NonNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    protected OrderDetail() {

    }

    @Override
    public String toString() {
        return String.format(
                "OrderDetail[id=%d, orderid=%d, productid=%d, quantity=%d]",
                id, order.getId(), product.getId(), quantity);
    }
}
