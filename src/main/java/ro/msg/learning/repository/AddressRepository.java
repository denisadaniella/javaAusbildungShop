package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
