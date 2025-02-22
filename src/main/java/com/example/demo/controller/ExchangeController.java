package com.example.demo.controller;

import com.example.demo.Exception.HttpException;
import com.example.demo.dto.CurrencyConversionRequest;
import com.example.demo.dto.ExchangeRates;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange")
public class ExchangeController {

    @Autowired
     CurrencyService currencyService;

    @GetMapping(value = "/rates", produces = "application/json")
    public ResponseEntity<ExchangeRates> getExchangeRates() throws HttpException {
        return ResponseEntity.ok().body(currencyService.getExchangeRates());
    }

    @PostMapping(value = "/convert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Double> convertCurrency(@RequestBody CurrencyConversionRequest request) throws Exception, HttpException {
        double convertedAmount = currencyService.convertCurrency(request.getFromCurrency(), request.getToCurrency(), request.getAmount());
        return ResponseEntity.ok().body(convertedAmount);
    }
}
