package com.nt.controller;

import com.nt.service.MealApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    private final MealApiService service;

  
    public CategoryController(MealApiService service) {
        this.service = service;
    }

    @GetMapping
    public Object listCategories() {
        return service.categories();
    }

    @GetMapping("/{category}/meals")
    public Object mealsByCategory(@PathVariable String category) {
        return service.mealsByCategory(category);
    }
}
