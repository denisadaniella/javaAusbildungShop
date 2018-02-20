package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
