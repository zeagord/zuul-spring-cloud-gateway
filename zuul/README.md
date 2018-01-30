# Gateway Implementation using Zuul

### Features

1. Zuul
    - Filters
    - Routing
    - Hystrix with Circuit Breaker
2. Micrometer - for metrics aggregating
    - Prometheus format
3. Zipkin
4. Gatling Setup for Load/Performance test


### How to RUN

* Clone the code and import in your favourite IDE with gradle (or you can run from your global gradle)
* For success response -> ```curl localhost:8765/api/self/hello```
* For Timeout Error to make circuit open -> ```curl localhost:8765/api/self/timeout```
* For routing to downstream with erroroneous service -> ```curl localhost:8765/api/myservice/get```

