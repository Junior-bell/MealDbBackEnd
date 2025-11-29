package com.nt.controller;

import com.nt.service.MealApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin
public class MealController {

    private final MealApiService service;

    
    public MealController(MealApiService service) {
        this.service = service;
    }

    @GetMapping
    public Object searchMeals(@RequestParam("search") String search) {
        return service.search(search);
    }

    @GetMapping("/{id}")
    public Object getMealById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/random")
    public Object getRandomMeal() {
        return service.randomMeal();
    }
}
