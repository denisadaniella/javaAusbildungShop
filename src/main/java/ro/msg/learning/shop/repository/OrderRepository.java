package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.dto.LocationOrderDto;
import ro.msg.learning.shop.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("select new ro.msg.learning.shop.dto.LocationOrderDto(l.id, sum(p.price * od.quantity)) from Location l, Order o, OrderDetail od, Product p " +
            "where o.location.id = l.id and o.id = od.order.id and od.product.id = p.id " +
            "and o.createTime between :salesDateBegin and :salesDateEnd " +
            "group by l.id")
    List<LocationOrderDto> aggregateSalesByDate(@Param("salesDateBegin") LocalDateTime salesDateBegin, @Param("salesDateEnd") LocalDateTime salesDateEnd);

}
