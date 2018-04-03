package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.service.StockExportImportService;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockExportImportController {

    @Autowired
    StockExportImportService stockExportImportService;


    @GetMapping(value = "/export")
    public @ResponseBody
    List<StockDto> exportStocks(@RequestParam Integer locationId) {
        return stockExportImportService.exportStocks(locationId);
    }

    @PostMapping(value = "/import", consumes = "text/csv", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String importStock(@RequestBody List<StockDto> stockDtos) {

        List<StockDto> stocks = stockExportImportService.importStocks(stockDtos);
        return String.valueOf(stocks.toString());
    }

}