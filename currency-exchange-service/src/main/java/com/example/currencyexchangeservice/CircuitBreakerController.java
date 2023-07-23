package com.example.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roman Andriiv (23.07.2023 - 11:01)
 */
@RestController
public class CircuitBreakerController {
    @GetMapping("/sample-api")
    public String sampleApi() {
        return "Sample API";
    }
}
