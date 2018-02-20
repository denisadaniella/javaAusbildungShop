package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
