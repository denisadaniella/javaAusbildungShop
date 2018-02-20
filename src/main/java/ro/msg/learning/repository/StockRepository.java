package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    List<Stock> findByProductId(Integer productId);

}
