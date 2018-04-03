package ro.msg.learning.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockExportImportService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackages = "ro.msg.learning.shop.repository")
//@ContextConfiguration(classes = { StrategyConfiguration.class })
public class ServiceMockTests {

    private static final String CATEGORY = "Diverse";
    private static final String SUPPLIER = "FashionDays";


    @Mock
    private ProductRepository productRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private Product product1, product2;

    @Mock
    private Location location1, location2;

    @Mock
    private Stock stockP1L1, stockP1L2, stockP2L1, stockP2L2;


    @InjectMocks
    private StockExportImportService stockExportImportService;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private StockMapper stockMapper;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);


        ProductCategory productCategory = new ProductCategory(CATEGORY, "Fashion Category");
        Supplier supplier = new Supplier(SUPPLIER);

        product1 = new Product(1, "Women's Jacket", "Women's Jacket, Black, Size 38",
                new BigDecimal(250), 0, productCategory, supplier, null);
        product2 = new Product(2, "Men's Jeans", "Men's Jeans, Blue, Size 38",
                new BigDecimal(250), 0, productCategory, supplier, null);

        location1 = new Location("Deposit Cluj", new Address());
        location2 = new Location("Deposit Buc", new Address());

        stockP1L1 = new Stock(1, product1, location1, 1);
        stockP1L2 = new Stock(2, product1, location2, 2);
        stockP2L1 = new Stock(3, product2, location1, 10);
        stockP2L2 = new Stock(4, product2, location2, 20);


        when(productRepository.findOne(1)).thenReturn(product1);
        when(productRepository.findOne(2)).thenReturn(product2);

        when(locationRepository.findOne(1)).thenReturn(location1);
        when(locationRepository.findOne(2)).thenReturn(location2);

        when(stockRepository.findByLocationId(1)).thenReturn(new ArrayList<>(Arrays.asList(stockP1L1, stockP2L1)));
        when(stockRepository.findByLocationId(2)).thenReturn(new ArrayList<>(Arrays.asList(stockP1L2, stockP2L2)));
    }


    @Test
    public void exportStock() {

        List<StockDto> stockDtos = stockExportImportService.exportStocks(1);
        assertThat(stockDtos.size(), equalTo(2));
        assertThat(stockDtos.get(0).getId(), equalTo((1)));
        assertThat(stockDtos.get(1).getId(), equalTo((3)));

        stockDtos = stockExportImportService.exportStocks(2);
        assertThat(stockDtos.size(), equalTo(2));
        assertThat(stockDtos.get(0).getId(), equalTo((2)));
        assertThat(stockDtos.get(1).getId(), equalTo((4)));

    }

    @Test(expected = LocationNotFoundException.class)
    public void exportStockForNoLocation() throws LocationNotFoundException {
        try {
            System.out.println("Location Not Found Exception expected.");
            stockExportImportService.exportStocks(3);
        } catch (LocationNotFoundException exc) {
            System.out.println(exc.getMessage());
            throw exc;
        }
    }

}
