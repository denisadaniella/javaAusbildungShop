package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.msg.learning.shop.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString(exclude = {"customer", "location", "address", "orderDetails"})
@EqualsAndHashCode(exclude = {"customer", "location", "address", "orderDetails"})
@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @Column(name = "createtime")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

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

}
