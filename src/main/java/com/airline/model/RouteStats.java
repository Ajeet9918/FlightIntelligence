package com.airline.model;

public class RouteStats {
    private String route;
    private int count;

    public RouteStats() {}

    public RouteStats(String route, int count) {
        this.route = route;
        this.count = count;
    }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}