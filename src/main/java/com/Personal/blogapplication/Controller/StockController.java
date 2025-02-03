package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.StockPriceDTO;
import com.Personal.blogapplication.Service.RedisPublisherService;
import com.Personal.blogapplication.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final RedisPublisherService redisPublisherService;

    @PostMapping("/update")
    public String updateStockPrice(@RequestBody StockPriceDTO stockPriceDTO) {
        stockService.updateStockPrice(stockPriceDTO);
        redisPublisherService.publishStockAlert(stockPriceDTO.getSymbol(), "Stock price updated!");
        return "Stock price updated successfully";
    }

    @GetMapping("/{symbol}/price")
    public Double getStockPrice(@PathVariable String symbol) {
        return stockService.getStockPrice(symbol);
    }

    @GetMapping("/{symbol}/history")
    public List<Object> getStockPriceHistory(@PathVariable String symbol) {
        return stockService.getStockPriceHistory(symbol);
    }
}