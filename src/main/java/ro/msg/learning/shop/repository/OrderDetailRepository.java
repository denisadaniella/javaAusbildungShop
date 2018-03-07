package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

}
