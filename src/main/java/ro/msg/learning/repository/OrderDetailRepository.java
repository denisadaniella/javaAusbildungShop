package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    List<OrderDetail> findByProductId(Integer productId);

    List<OrderDetail> findByOrderId(Integer orderId);
}
