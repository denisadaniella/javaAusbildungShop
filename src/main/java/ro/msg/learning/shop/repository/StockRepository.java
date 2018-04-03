package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    List<Stock> findByProductIdAndQuantityGreaterThanEqual(Integer productId, Integer quantity);

    Stock findByProductIdAndLocationId(Integer productId, Integer locationId);

    List<Stock> findByLocationId(Integer locationId);
}
