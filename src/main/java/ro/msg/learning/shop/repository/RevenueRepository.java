package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Revenue;

import java.time.LocalDate;

@Repository
public interface RevenueRepository extends CrudRepository<Revenue, Integer> {

    Revenue findByLocationIdAndDate(Integer locationId, LocalDate date);

}
