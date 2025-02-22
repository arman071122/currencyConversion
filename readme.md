# Exchange Controller API Documentation

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

---# currencyConversion
