package ro.msg.learning.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @NonNull
    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders;


    public Customer(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    protected Customer() {

    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', username='%s']",
                id, firstName, lastName, username);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username);
    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
