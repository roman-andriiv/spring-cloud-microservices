package com.example.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roman Andriiv (18.07.2023 - 17:02)
 */
@RestController
public class CurrencyExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    private final Environment environment;
    private final CurrencyExchangeRepository exchangeRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository exchangeRepository) {
        this.environment = environment;
        this.exchangeRepository = exchangeRepository;
    }


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        logger.info("retrieveExchangeValue  called with {} {}", from, to);

        CurrencyExchange currencyExchange = exchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException(String.format("Unable to find data for %s to %s", from, to));
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
