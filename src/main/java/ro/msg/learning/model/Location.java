package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LOCATION", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    private Address address;

    public Location(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    protected Location() {

    }

    @Override
    public String toString() {
        return String.format(
                "Location[id=%d, name='%s', addressid=%d]",
                id, name, address.getId());
    }
}
