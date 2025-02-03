package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.StockPriceDTO;
import com.Personal.blogapplication.Utils.RedisConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {
    private final RedisTemplate<String, Object> redisTemplate;

//    public void updateStockPrice(StockPriceDTO dto){
//        try {
//            redisTemplate.opsForHash().put(RedisConstants.STOCK_PRICES_KEY,dto.getSymbol(),dto.getPrice());
//            redisTemplate.opsForList().leftPush(RedisConstants.STOCK_PRICES_KEY + dto.getSymbol() x+ dto.getPrice());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void updateStockPrice(StockPriceDTO stockPriceDTO) {
        try {
            redisTemplate.opsForHash().put(RedisConstants.STOCK_PRICES_KEY, stockPriceDTO.getSymbol(), stockPriceDTO.getPrice());
            redisTemplate.opsForList().leftPush(RedisConstants.STOCK_HISTORY_KEY + stockPriceDTO.getSymbol(), stockPriceDTO.getPrice());
        } catch (Exception e) {
            throw new RuntimeException("Error updating stock price", e);
        }
    }
    public Double getStockPrice(String symbol){
        try {
            return (Double) redisTemplate.opsForHash().get(RedisConstants.STOCK_PRICES_KEY,symbol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> getStockPriceHistory(String symbol){
        try {
          return  redisTemplate.opsForList().range(RedisConstants.STOCK_HISTORY_KEY + symbol,0,-1);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving stock price history", e);
        }
    }


}
