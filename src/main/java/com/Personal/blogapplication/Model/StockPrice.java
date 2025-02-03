package com.Personal.blogapplication.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockPrice {
    private String symbol;
    private Double price;
    private String timestamp;
}
