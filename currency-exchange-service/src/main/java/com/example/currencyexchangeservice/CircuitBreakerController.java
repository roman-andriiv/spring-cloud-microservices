package com.example.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Roman Andriiv (23.07.2023 - 11:01)
 */
@RestController
public class CircuitBreakerController {
    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Sample API call received");
        return new RestTemplate()
                .getForEntity("http://localhost:8080/sample-api", String.class)
                .getBody();
    }

    public String hardcodedResponse(Exception e){
        return "Fallback response";
    }
}
