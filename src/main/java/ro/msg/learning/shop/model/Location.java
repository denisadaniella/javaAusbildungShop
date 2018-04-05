package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = "address")
@EqualsAndHashCode(exclude = "address")
@Entity
@Table(name = "LOCATION", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NoArgsConstructor
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

    public Location(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
