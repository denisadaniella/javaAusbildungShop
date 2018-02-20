package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.Supplier;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findBySupplier(Supplier supplier);

    List<Product> findByNameContains(String name);
}
