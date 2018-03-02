package ro.msg.learning.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.repository.ProductCategoryRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.SupplierRepository;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackages = "ro.msg.learning.repository")
@EntityScan(basePackages = "ro.msg.learning.model")
public class JpaRepositoryTests {

    private static final String CATEGORY = "Diverse";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void addProductCategoryAndProduct() {
        productCategoryRepository.save(new ProductCategory(CATEGORY, "Descriere"));
        Assert.assertEquals(1, productCategoryRepository.findByName(CATEGORY).size());

        List<ProductCategory> productCategories = productCategoryRepository.findByName(CATEGORY);
        productRepository.save(new Product("Geaca Femei", "Geaca Femei, Neagra, Nr 38",
                new BigDecimal(250), 0, productCategories.get(0), supplierRepository.findOne(1)));

        Assert.assertEquals(1, productRepository.findByNameContains("Geaca").size());
    }


    @Test
    public void updateProduct() {
        List<Product> products = productRepository.findByNameContains("Geaca");

        Assert.assertTrue(products.size() >= 1);

        Product product = products.get(0);

        //Assert.assertEquals(product.getPrice().toString(), "250.00");

        product.setPrice(new BigDecimal(499.99));
        productRepository.save(product);


        Product updatedProduct = productRepository.findOne(product.getId());
        Assert.assertEquals("499.99", updatedProduct.getPrice().toString());
    }


    @Test
    public void deleteProductCategoriesAndProducts() {
        List<Product> products = productRepository.findByNameContains("Geaca");
        Assert.assertTrue(products.size() >= 1);

        products.forEach(p -> productRepository.delete(p.getId()));
        Assert.assertEquals(0, productRepository.findByNameContains("Geaca").size());


        List<ProductCategory> productCategories = productCategoryRepository.findByName(CATEGORY);
        Assert.assertTrue(productCategories.size() >= 1);

        productCategories.forEach(p -> productCategoryRepository.delete(p.getId()));
        Assert.assertEquals(0, productCategoryRepository.findByName(CATEGORY).size());
    }
}
