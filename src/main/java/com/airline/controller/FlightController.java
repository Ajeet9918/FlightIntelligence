package com.airline.controller;

import com.airline.model.PriceTrend;
import com.airline.model.RouteStats;
import com.airline.service.AviationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class FlightController {

    @Autowired
    private AviationService aviationService;
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @PostMapping("/results")
    public String results(Model model) {
        List<JsonNode> rawData       = aviationService.fetchAirlineData(20);
        List<RouteStats> routeStats  = aviationService.analyzeRoutes(rawData);
        List<PriceTrend> priceTrends = aviationService.generatePriceTrends(routeStats);
        String insights              = aviationService.generateAiInsights(routeStats);

        // Model attributes map 1-to-1 with Jinja2 template variables
        model.addAttribute("popularRoutes", routeStats);   // popular_routes
        model.addAttribute("priceTrends", priceTrends);    // price_trends
        model.addAttribute("insights", insights);          // insights

        return "results";
    }
}
