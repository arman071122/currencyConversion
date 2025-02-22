# Exchange Controller API Documentation

A Spring Boot REST API that fetches real-time exchange rates and performs currency conversion.

---

## Features
- Fetches live exchange rates from a public API  
- Converts an amount from one currency to another  
- Handles errors like invalid currency codes or API unavailability  

---

## Tech Stack
- Java 17+  
- Spring Boot  
- Maven  
- RestTemplate (for API calls)  
- JUnit (for testing)  

---

## Getting Started

### 1. Clone the Repository
```sh
git clone https://github.com/yourname/currency-converter-api.git
cd currency-converter-api
```

### 2. Build the Project
Ensure you have **Maven** installed, then run:
```sh
mvn clean install
```

### 3. Run the Application
Start the Spring Boot app with:
```sh
mvn spring-boot:run
```
By default, the API will be available at:
```
http://localhost:8080
```

---

## POST /api/v1/exchange/convert

### Request Body
**Content-Type:** application/json

```json
{
  "fromCurrency": "String",
  "toCurrency": "String",
  "amount": "double",
}
```

### Curl Command

```sh
curl -X 'POST' \
  'http://localhost:8080/api/v1/exchange/convert' 
  -H 'accept: application/json' 
  -H 'Content-Type: application/json' 
  -d '{
  "fromCurrency": "USD",
  "toCurrency": "INR",
  "amount": 3000
}'
```

### Request URL
`http://localhost:8080/api/v1/exchange/convert`

### Server Response
**Code:** 200 OK

**Response Body:**

```json
259948.07700000002
```

**Response Headers:**
- Connection: keep-alive
- Content-Type: application/json
- Date: Sat, 22 Feb 2025 06:49:10 GMT
- Keep-Alive: timeout=60
- Transfer-Encoding: chunked

---

## GET /api/v1/exchange/rates

### Curl Command

```sh
curl -X 'GET' \
  'http://localhost:8080/api/v1/exchange/rates' \
  -H 'accept: application/json'
```

### Request URL
`http://localhost:8080/api/v1/exchange/rates`

### Server Response
**Code:** 200 OK

**Response Body:**

```json
{
  "license": "https://openexchangerates.org/license",
  "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
  "base": "USD",
  "rates": { ... }
}
```

**Response Headers:**
- Connection: keep-alive
- Content-Type: application/json
- Date: Sat, 22 Feb 2025 06:49:24 GMT
- Keep-Alive: timeout=60
- Transfer-Encoding: chunked

