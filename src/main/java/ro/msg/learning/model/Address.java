package ro.msg.learning.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @NonNull
    @Column(name = "country", nullable = false)
    private String country;

    @NonNull
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "county")
    private String county;

    @NonNull
    @Column(name = "street")
    private String street;

    public Address(String country, String city, String county, String street) {
        this.country = country;
        this.city = city;
        this.county = county;
        this.street = street;
    }

    protected Address() {

    }

    @Override
    public String toString() {
        return String.format(
                "Address[id=%d, country='%s', city='%s', county='%s', street='%s']",
                id, country, city, county, street);
    }
}
