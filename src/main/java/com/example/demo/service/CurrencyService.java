package com.example.demo.service;

import com.example.demo.Exception.HttpException;
import com.example.demo.Exception.InvalidCurrencyException;
import com.example.demo.dto.ExchangeRates;

public interface CurrencyService {

    ExchangeRates getExchangeRates() throws HttpException;

    double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception, HttpException, InvalidCurrencyException;
}
