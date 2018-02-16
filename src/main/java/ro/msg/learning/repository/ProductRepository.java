package ro.msg.learning.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class ProductRepository {

    private static final String SELECT = "SELECT id, name, description, price, weight, category, supplier FROM product";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT, new ProductRowMapper());
    }

    @Transactional(readOnly = true)
    public List<Product> getProductByName(String name) {
        return jdbcTemplate.query(SELECT + "WHERE name = ?", new Object[]{name}, new ProductRowMapper());
    }

    @Transactional(readOnly = true)
    public List<Product> getProductById(int productId) {
        return jdbcTemplate.query(SELECT + "WHERE id = ?", new Object[]{productId}, new ProductRowMapper());
    }

    public void addProduct(Product product) {
        jdbcTemplate.update("INSERT INTO product(name, description, price, weight, category, supplier) VALUES (?, ?, ?, ?, ?, ?)",
                product.getName(), product.getDescription(), product.getPrice(), product.getWeight(), product.getCategory(), product.getSupplier());
    }

    public void updateProduct(Product product) {
        jdbcTemplate.update("UPDATE product SET name=?, description=?, price=?, weight=?, category=?, supplier=? WHERE id=?",
                product.getName(), product.getDescription(), product.getPrice(), product.getWeight(), product.getCategory(), product.getSupplier(),
                product.getProductId());
    }

    public void deleteProduct(int productId) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", productId);
    }


    class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product(0, null, null, new BigDecimal(0), 0, 0, 0);
            product.setProductId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice((rs.getBigDecimal("price")));
            product.setWeight(rs.getDouble("weight"));
            product.setCategory(rs.getInt("category"));
            product.setSupplier(rs.getInt("supplier"));
            return product;
        }
    }

}
