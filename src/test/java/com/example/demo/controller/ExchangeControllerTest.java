package com.example.demo.controller;

import com.example.demo.Exception.HttpException;
import com.example.demo.dto.CurrencyConversionRequest;
import com.example.demo.dto.ExchangeRates;
import com.example.demo.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ExchangeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private ExchangeController exchangeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeController).build();
    }

    @Test
    @DisplayName("Test getExchangeRates GET request with status OK")
    void testGetExchangeRates_WithStatusOK() throws Exception, HttpException {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setLicense("Test License");
        exchangeRates.setDisclaimer("Test Disclaimer");
        exchangeRates.setBase("USD");
        exchangeRates.setDate("2023-10-01");
        exchangeRates.setRates(Map.of("INR", 74.85));

        when(currencyService.getExchangeRates()).thenReturn(exchangeRates);

        mockMvc.perform(get("/api/v1/exchange/rates")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"license\":\"Test License\",\"disclaimer\":\"Test Disclaimer\",\"base\":\"USD\",\"date\":\"2023-10-01\",\"rates\":{\"INR\":74.85}}"));
    }

    @Test
    @DisplayName("Test convertCurrency POST request with status OK")
    void testConvertCurrency_WithStatusOK() throws Exception, HttpException {
        CurrencyConversionRequest request = new CurrencyConversionRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("INR");
        request.setAmount(3000);

        when(currencyService.convertCurrency("USD", "INR", 3000)).thenReturn(259948.077);

        mockMvc.perform(post("/api/v1/exchange/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fromCurrency\":\"USD\",\"toCurrency\":\"INR\",\"amount\":3000}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("259948.077"));
    }
}