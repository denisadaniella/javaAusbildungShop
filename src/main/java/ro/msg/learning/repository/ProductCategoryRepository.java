package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {

    List<ProductCategory> findByName(String name);

}
