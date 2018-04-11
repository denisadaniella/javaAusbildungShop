package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString(exclude = {"location"})
@EqualsAndHashCode(exclude = {"location"})
@Entity
@Table(name = "REVENUE", uniqueConstraints = @UniqueConstraint(columnNames = {"locationid", "salesdate"}))
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private Location location;

    @NonNull
    @Column(name = "salesdate", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "salessum", nullable = false)
    private BigDecimal sum;

    public Revenue(Location location, LocalDate date, BigDecimal sum) {
        this.location = location;
        this.date = date;
        this.sum = sum;
    }
}
