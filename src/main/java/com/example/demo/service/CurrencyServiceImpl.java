package com.example.demo.service;

import com.example.demo.Exception.APIException;
import com.example.demo.Exception.HttpException;
import com.example.demo.Exception.InvalidCurrencyException;
import com.example.demo.config.ApiConfig;
import com.example.demo.dto.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyServiceImpl(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ExchangeRates getExchangeRates() throws HttpException, APIException {
        final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=" + apiConfig.getKey();
        try {
            return restTemplate.getForObject(API_URL, ExchangeRates.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new HttpException("Http exception with status code: " + e.getStatusCode());
        } catch (Exception e) {
            throw new APIException("API call failed with error: " + e.getMessage());
        }
    }

    @Override
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception, HttpException {

        ExchangeRates exchangeRates = getExchangeRates();
        Map<String, Double> rates = exchangeRates.getRates();
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new InvalidCurrencyException("Invalid currency code");
        }
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }
}
