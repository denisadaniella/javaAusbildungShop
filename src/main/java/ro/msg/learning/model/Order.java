package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    private Address address;

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

}
