package ro.msg.learning.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.model.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
}
