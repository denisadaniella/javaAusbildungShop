package ro.msg.learning.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUPPLIER", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    protected Supplier() {

    }

    @Override
    public String toString() {
        return String.format(
                "Supplier[id=%d, name='%s']",
                id, name);
    }
}
