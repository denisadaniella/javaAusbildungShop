package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = "address")
@EqualsAndHashCode(exclude = "address")
@Entity
@Table(name = "LOCATION", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "addressid", referencedColumnName = "id")
    private Address address;

    protected Location() {

    }

}
