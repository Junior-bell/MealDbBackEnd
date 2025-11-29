package com.nt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.ApiResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class MealApiService {

    private final StringRedisTemplate redis;
    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private static final String BASE = "https://www.themealdb.com/api/json/v1/1/";

    public MealApiService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    // ------- Generic cache function -------
    private JsonNode cacheableRequest(String key, String url) {

        try {
            // Fetch from cache
            String cached = redis.opsForValue().get(key);
            if (cached != null) {
                return mapper.readTree(cached);
            }
        } catch (Exception ignored) { }

        try {
            // Fetch from API
            JsonNode response = rest.getForObject(url, JsonNode.class);

            if (response == null)
                throw new RuntimeException("No response from external API");

            // Save to cache
            try {
                redis.opsForValue().set(key, response.toString(), Duration.ofMinutes(30));
            } catch (Exception ignored) { }

            return response;
        }
        catch (RestClientException e) {
            throw new RuntimeException("Failed to connect to MealDB API");
        }
        catch (Exception e) {
            throw new RuntimeException("Error processing data");
        }
    }

    // ------- Endpoints -------
    public ApiResponse search(String q) {
        if (q == null || q.isBlank())
            throw new RuntimeException("Search query cannot be empty");

        JsonNode data = cacheableRequest("search_" + q, BASE + "search.php?s=" + q);
        return new ApiResponse(true, data, null);
    }

    public ApiResponse categories() {
        JsonNode data = cacheableRequest("categories", BASE + "categories.php");
        return new ApiResponse(true, data, null);
    }

    public ApiResponse mealsByCategory(String cat) {
        if (cat == null || cat.isBlank())
            throw new RuntimeException("Invalid category");

        JsonNode data = cacheableRequest("cat_" + cat, BASE + "filter.php?c=" + cat);
        return new ApiResponse(true, data, null);
    }

    public ApiResponse findById(String id) {
        if (!id.matches("\\d+"))
            throw new RuntimeException("Invalid meal ID");

        JsonNode data = cacheableRequest("id_" + id, BASE + "lookup.php?i=" + id);
        return new ApiResponse(true, data, null);
    }

    public ApiResponse randomMeal() {
        JsonNode data = cacheableRequest("random_meal", BASE + "random.php");
        return new ApiResponse(true, data, null);
    }
}
