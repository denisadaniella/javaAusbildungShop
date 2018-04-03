package ro.msg.learning.shop;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.util.CsvUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvUtilTests {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    CsvUtil<StockDto> csvUtil;
    List<StockDto> stocks;
    private ByteArrayInputStream byteArrayInputStream;

    @Before
    public void setup() {

        csvUtil = new CsvUtil<>();

        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void exportToCsv() {
        stocks = new ArrayList<>();
        stocks.add(new StockDto(1, 2, 3, 4));
        stocks.add(new StockDto(11, 22, 33, 44));

        byteArrayOutputStream.reset();
        try {
            csvUtil.toCsv(stocks, StockDto.class, byteArrayOutputStream);
            String expectedString = "productId,locationId,quantity\n" +
                    "2,3,4\n" +
                    "22,33,44\n";
            Assert.assertEquals("These should be equals: ", expectedString, byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void importFromCsv() {
        String inputCSVString = "productId,locationId,quantity\n" +
                "2,3,4\n" +
                "22,33,44\n";

        byteArrayInputStream = new ByteArrayInputStream(inputCSVString.getBytes());
        try {
            stocks = csvUtil.fromCsv(StockDto.class, byteArrayInputStream);

            Assert.assertEquals("Size of the list: ", 2, stocks.size());
            Assert.assertEquals("Quantity of the first stock: ", Integer.valueOf(4), stocks.get(0).getQuantity());
            Assert.assertEquals("Location Id of the second stock: ", Integer.valueOf(33), stocks.get(1).getLocationId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanup() {
        System.setOut(null);
    }

}
