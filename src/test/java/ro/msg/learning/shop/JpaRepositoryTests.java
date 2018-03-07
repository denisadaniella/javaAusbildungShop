package ro.msg.learning.shop;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackages = "ro.msg.learning.shop.repository")
public class JpaRepositoryTests {

    private static final String CATEGORY = "Diverse";
    private static final String SUPPLIER = "FashionDays";

    private Product product;
    private ProductCategory productCategory;
    private Supplier supplier;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    @Before
    public void setupAddProductProductCategorySupplier() {

        //Add Product Category, Supplier, Product
        productCategory = productCategoryRepository.save(new ProductCategory(CATEGORY, "Descriere"));
        Assert.assertEquals(1, productCategoryRepository.findByName(CATEGORY).size());

        supplier = supplierRepository.save(new Supplier(SUPPLIER));
        Assert.assertEquals(1, supplierRepository.findByName(SUPPLIER).size());

        product = productRepository.save(new Product("Geaca Femei", "Geaca Femei, Neagra, Nr 38",
                new BigDecimal(250), 0, productCategory, supplier));

        Assert.assertEquals(1, productRepository.findByNameContains("Geaca").size());

    }

    @Test
    public void getProductProductCategorySupplier() {

        Product retrievedProduct = productRepository.findOne(product.getId());
        Assert.assertNotNull(retrievedProduct);

        ProductCategory retrievedProductCategory = productCategoryRepository.findOne(retrievedProduct.getProductCategory().getId());
        Assert.assertNotNull(retrievedProduct);
        Assert.assertEquals(productCategory.getId(), retrievedProductCategory.getId());

        Supplier retrievedSupplier = supplierRepository.findOne(retrievedProduct.getSupplier().getId());
        Assert.assertNotNull(retrievedSupplier);
        Assert.assertEquals(supplier.getId(), retrievedSupplier.getId());

    }

    @Test
    public void updateProduct() {

        //Update Product

        Assert.assertEquals("250", product.getPrice().toString());

        product.setPrice(new BigDecimal(499.99));
        productRepository.save(product);

        Product updatedProduct = productRepository.findOne(product.getId());
        Assert.assertEquals("499.99", updatedProduct.getPrice().toString());

    }

    @Test
    public void deleteProductProductCategorySupplier() {
        //Delete Product Category, Supplier, Product
        int sizeProductsBeforeDelete = productRepository.findByNameContains("Geaca").size();
        productRepository.delete(product.getId());
        Assert.assertEquals(sizeProductsBeforeDelete - 1, productRepository.findByNameContains("Geaca").size());

        int sizeProductCategoriesBeforeDelete = productCategoryRepository.findByName(CATEGORY).size();
        productCategoryRepository.delete(productCategory.getId());
        Assert.assertEquals(sizeProductCategoriesBeforeDelete - 1, productCategoryRepository.findByName(CATEGORY).size());

        int sizeSuppliersBeforeDelete = supplierRepository.findByName(SUPPLIER).size();
        supplierRepository.delete(supplier.getId());
        Assert.assertEquals(sizeSuppliersBeforeDelete - 1, supplierRepository.findByName(SUPPLIER).size());
    }


    @After
    public void cleanupDeleteProdcutsProductCategoriesSuppliers() {
        List<Product> products = productRepository.findByNameContains("Geaca");
        products.forEach(p -> productRepository.delete(p.getId()));
        Assert.assertEquals(0, productRepository.findByNameContains("Geaca").size());


        List<ProductCategory> productCategories = productCategoryRepository.findByName(CATEGORY);
        productCategories.forEach(p -> productCategoryRepository.delete(p.getId()));
        Assert.assertEquals(0, productCategoryRepository.findByName(CATEGORY).size());

        List<Supplier> suppliers = supplierRepository.findByName(SUPPLIER);
        suppliers.forEach(p -> supplierRepository.delete(p.getId()));
        Assert.assertEquals(0, supplierRepository.findByName(SUPPLIER).size());
    }
}
