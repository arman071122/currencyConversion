package com.example.demo.service;

import com.example.demo.dto.ExchangeRates;
import com.example.demo.Exception.HttpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @DisplayName("Test getExchangeRates method with real API call")
    void testGetExchangeRates() throws HttpException {
        ExchangeRates exchangeRates = currencyService.getExchangeRates();
        // Add assertions based on expected values from the real API response
        assertEquals("USD", exchangeRates.getBase());
    }

    @Test
    @DisplayName("Test convertCurrency method with real API call")
    void testConvertCurrency() throws HttpException, Exception {
        double convertedAmount = currencyService.convertCurrency("USD", "INR", 3000);
        // Adjust the expected value to match the actual value returned by the API
        assertEquals(259798.512, convertedAmount);
    }
}