package com.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceTrend {
    private String route;
    private List<Integer> prices;   // INR prices
    private List<String> days;      // Mon–Sun labels
}
