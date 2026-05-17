package com.airline.service;

import com.airline.model.PriceTrend;
import com.airline.model.RouteStats;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer — equivalent of the helper functions in Python's app.py.
 *
 * Python → Java mapping:
 *   fetch_airline_data()      → fetchAirlineData()
 *   analyze_routes()          → analyzeRoutes()
 *   generate_price_trends()   → generatePriceTrends()
 *   generate_ai_insights()    → generateAiInsights()
 *
 * requests.get() + response.json()  →  RestTemplate + ObjectMapper (Jackson)
 * pandas DataFrame value_counts()   →  HashMap + stream sort
 */
@Service
public class AviationService {

    private static final String API_KEY  = "994fc8777b1a9c5331ec81679d93eb2e";
    private static final String BASE_URL = "http://api.aviationstack.com/v1/flights";
    private static final List<String> DAYS = List.of("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper  = new ObjectMapper();
    private final Random random              = new Random();

    // -----------------------------------------------------------------------
    // fetch_airline_data(limit=20)
    // -----------------------------------------------------------------------
    public List<JsonNode> fetchAirlineData(int limit) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("limit", limit)
                .toUriString();

        try {
            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(json);
            JsonNode data = root.get("data");
            if (data != null && data.isArray()) {
                List<JsonNode> result = new ArrayList<>();
                data.forEach(result::add);
                return result;
            }
        } catch (Exception e) {
            // log and fall through to empty list
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    // -----------------------------------------------------------------------
    // analyze_routes(data)
    // Replaces: pd.DataFrame + value_counts() + reset_index()
    // -----------------------------------------------------------------------
    public List<RouteStats> analyzeRoutes(List<JsonNode> flights) {
        Map<String, Integer> routeCounts = new LinkedHashMap<>();

        for (JsonNode flight : flights) {
            try {
                String dep = flight.path("departure").path("airport").asText(null);
                String arr = flight.path("arrival").path("airport").asText(null);
                if (dep != null && arr != null && !dep.isBlank() && !arr.isBlank()) {
                    String route = dep + " → " + arr;
                    routeCounts.merge(route, 1, Integer::sum);
                }
            } catch (Exception ignored) {}
        }

        // Sort descending by count (mirrors value_counts() default)
        return routeCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(e -> new RouteStats(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    // -----------------------------------------------------------------------
    // generate_price_trends(routes)
    // -----------------------------------------------------------------------
    public List<PriceTrend> generatePriceTrends(List<RouteStats> routes) {
        List<PriceTrend> trends = new ArrayList<>();
        for (RouteStats rs : routes) {
            List<Integer> prices = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                prices.add(random.nextInt(9001) + 3000); // 3000–12000 INR
            }
            trends.add(new PriceTrend(rs.getRoute(), prices, DAYS));
        }
        return trends;
    }

    // -----------------------------------------------------------------------
    // generate_ai_insights(routes)
    // -----------------------------------------------------------------------
    public String generateAiInsights(List<RouteStats> routes) {
        if (routes.isEmpty()) {
            return "No data available to analyze trends.";
        }
        RouteStats top = routes.get(0);
        return "The most frequent route this week is <b>" + top.getRoute() + "</b> with "
                + "<b>" + top.getCount() + "</b> flights. "
                + "This suggests a high travel demand between these two airports. "
                + "Consider booking early to avoid price hikes.";
    }
}
