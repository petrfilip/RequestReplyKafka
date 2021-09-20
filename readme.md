Request

```http request
POST http://localhost:8080/sum
Content-Type: application/json

{
  "firstNumber": 1,
  "secondNumber": 2
}
```

Response

```http response
{
  "firstNumber": 0,
  "secondNumber": 0,
  "sum": 0
}
```

Kafka in docker

```
docker run --rm -p 2181:2181 -p 3030:3030 -p 8081-8083:8081-8083 -p 9581-9585:9581-9585 -p 9092:9092 -e ADV_HOST=127.0.0.1 landoop/fast-data-dev:latest
```

GUI for kafka

```
docker run --rm -it -p 8000:8000 -e "KAFKA_REST_PROXY_URL=http://localhost:8082"  landoop/kafka-topics-ui
```

Source: https://dzone.com/articles/synchronous-kafka-using-spring-request-reply-1