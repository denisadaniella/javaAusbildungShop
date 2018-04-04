package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Query("select a.role from UserRole a, User b where b.username=:username and a.user.id=b.id")
    List<String> findRoleByUsername(@Param("username") String username);

}
