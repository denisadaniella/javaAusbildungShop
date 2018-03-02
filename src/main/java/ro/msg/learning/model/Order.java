package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    private Address address;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    public Order(Customer customer, Location location, Address address) {
        this.customer = customer;
        this.location = location;
        this.address = address;
    }

    protected Order() {

    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%d, locationid=%d, customerid=%d, addressid=%d]",
                id, location.getId(), customer.getId(), address.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, location.getId(), customer.getId(), address.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
