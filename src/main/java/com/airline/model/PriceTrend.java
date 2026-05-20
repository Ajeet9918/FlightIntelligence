package com.airline.model;

import java.util.List;

public class PriceTrend {
    private String route;
    private List<Integer> prices;
    private List<String> days;

    public PriceTrend() {}

    public PriceTrend(String route, List<Integer> prices, List<String> days) {
        this.route = route;
        this.prices = prices;
        this.days = days;
    }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public List<Integer> getPrices() { return prices; }
    public void setPrices(List<Integer> prices) { this.prices = prices; }
    public List<String> getDays() { return days; }
    public void setDays(List<String> days) { this.days = days; }
}