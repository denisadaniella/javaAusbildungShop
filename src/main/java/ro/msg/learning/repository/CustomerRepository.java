package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
