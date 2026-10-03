# Architecture notes
```text
Client -> API Gateway -> Application Service -> Kafka: credit.application.submitted
                                      Kafka -> Risk Service -> Kafka: credit.decision.completed
                                                      -> Decision Service -> MySQL
                                                      -> Notification Service -> demo log
```
Kafka provides asynchronous decoupling. In production, use explicit event schemas, retries and dead-letter topics, consumer idempotency, outbox or equivalent delivery guarantees, correlation IDs, and metrics for lag and end-to-end latency.
