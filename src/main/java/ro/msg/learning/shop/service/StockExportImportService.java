package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.exception.*;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockExportImportService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final LocationRepository locationRepository;

    @Transactional
    public List<StockDto> exportStocks(Integer locationId) {

        if (locationId < 0) {
            throw new NegativeInputParameterException(locationId);
        }

        if (locationRepository.findOne(locationId) == null) {
            throw new LocationNotFoundException(locationId);
        }

        List<Stock> stocks = stockRepository.findByLocationId(locationId);
        return stockMapper.toStockDto(stocks);
    }

    public List<StockDto> importStocks(List<StockDto> stockDtos) {
        List<Stock> stocks = stockDtos.stream().map(this::importStock).collect(Collectors.toList());
        return stockMapper.toStockDto(stocks);
    }

    private Stock importStock(StockDto stockDto) {

        Stock stock = stockMapper.toStock(stockDto);

        if (stock.getProduct() == null) {
            throw new StockConsistencyException(stockDto.toString(), new NullProductException(stockDto.getProductId()));
        }

        if (stock.getLocation() == null) {
            throw new StockConsistencyException(stockDto.toString(), new NullLocationException(stockDto.getLocationId()));
        }

        Stock existingStock = stockRepository.findByProductIdAndLocationId(stock.getProduct().getId(), stock.getLocation().getId());
        if (existingStock != null) {
            Integer newQuantity = existingStock.getQuantity() + stock.getQuantity();
            existingStock.setQuantity(newQuantity);
            return stockRepository.save(existingStock);
        }


        return stockRepository.save(stock);

    }


}
