package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString(exclude = "userRoles")
@EqualsAndHashCode(exclude = "userRoles")
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();

    }
}
