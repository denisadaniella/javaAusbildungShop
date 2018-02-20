package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}
