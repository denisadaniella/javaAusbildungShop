package ro.msg.learning.shop.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class StockMapper {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LocationRepository locationRepository;

    public StockDto toStockDto(Stock stock) {
        StockDto stockDto = new StockDto();

        stockDto.setId(stock.getId());
        stockDto.setProductId(stock.getProduct().getId());
        stockDto.setLocationId(stock.getLocation().getId());
        stockDto.setQuantity(stock.getQuantity());

        return stockDto;
    }

    public List<StockDto> toStockDto(List<Stock> stocks) {
        return stocks.stream().map(this::toStockDto).collect(Collectors.toList());
    }

    public Stock toStock(StockDto stockDto) {
        Stock stock = new Stock();

        stock.setId(stockDto.getId());
        stock.setQuantity(stockDto.getQuantity());

        stock.setProduct(productRepository.findOne(stockDto.getProductId()));
        stock.setLocation(locationRepository.findOne(stockDto.getLocationId()));

        return stock;
    }

    public List<Stock> toStock(List<StockDto> stockDtos) {
        return stockDtos.stream().map(this::toStock).collect(Collectors.toList());
    }
}
