package ro.msg.learning.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class ProductCategoryRepository {

    private static final String SELECT = "SELECT id, name, description FROM category";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ProductCategory> getAllCategories() {
        return jdbcTemplate.query(SELECT, new ProductCategoryRowMapper());
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> getCategoryByName(String name) {
        return jdbcTemplate.query(SELECT + "WHERE name = ?", new Object[]{name}, new ProductCategoryRowMapper());
    }

    @Transactional(readOnly = true)
    public List<ProductCategory> getCategoryById(int productCategoryId) {
        return jdbcTemplate.query(SELECT + "WHERE id = ?", new Object[]{productCategoryId}, new ProductCategoryRowMapper());
    }

    public void addCategory(ProductCategory productCategory) {
        jdbcTemplate.update("INSERT INTO category(name, description) VALUES (?, ?)",
                productCategory.getName(), productCategory.getDescription());
    }

    public void updateCategory(ProductCategory productCategory) {
        jdbcTemplate.update("UPDATE category SET name=?, description=? WHERE id=?",
                productCategory.getName(), productCategory.getDescription());
    }

    public void deleteCategory(int productCategoryId) {
        jdbcTemplate.update("DELETE FROM category WHERE id=?", productCategoryId);
    }

    class ProductCategoryRowMapper implements RowMapper<ProductCategory> {
        @Override
        public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductCategory productCategory = new ProductCategory(0, null, null);
            productCategory.setProductCategoryId(rs.getInt("id"));
            productCategory.setName(rs.getString("name"));
            productCategory.setDescription(rs.getString("description"));
            return productCategory;
        }
    }

}
