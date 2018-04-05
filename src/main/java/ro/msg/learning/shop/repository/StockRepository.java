package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    Stock findByProductIdAndLocationId(Integer productId, Integer locationId);

    List<Stock> findByLocationId(Integer locationId);

    @Query("select l from Location l, Stock s " +
            "where s.location.id = l.id and s.product.id=:productId and s.quantity>=:quantity")
    List<Location> findLocationByProductIdAndQuantityGreaterThanEqual(@Param("productId") Integer productId, @Param("quantity") Integer quantity);
}
