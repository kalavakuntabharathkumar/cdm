# Credit Decision Microservices Platform (SaaS)

A local-development starter for a credit decisioning platform, organized as five Spring Boot services:
1. **api-gateway** — public HTTP entry point and routing.
2. **application-service** — validates applications and publishes decision requests.
3. **risk-service** — consumes requests, calculates a transparent demo risk score, publishes results.
4. **decision-service** — persists decisions in MySQL and exposes decision lookup.
5. **notification-service** — consumes decision events and logs a notification record.

**Stack:** Java 17, Spring Boot 3.3, Apache Kafka, MySQL 8, JUnit 5, Cucumber, Maven, Docker Compose, Jenkins, Terraform/AWS ECS/ECR templates.

> This is a portfolio/demo implementation, not a real credit scoring or lending system. The score is illustrative and must not be used for actual credit decisions. Production systems need validated models, fairness reviews, consent/legal controls, authentication, observability, data retention, and security hardening.

## Run locally
Requirements: Docker + Docker Compose. From this directory:
```bash
docker compose up --build
```
API gateway: `http://localhost:8080`; health: `http://localhost:8080/actuator/health`.

Submit a demo application:
```bash
curl -X POST http://localhost:8080/api/applications \
  -H 'Content-Type: application/json' \
  -d '{"applicantId":"demo-100","annualIncome":72000,"monthlyDebt":900,"requestedAmount":12000}'
```
Check a decision:
```bash
curl http://localhost:8080/api/decisions/demo-100
```

## Test
With Java 17 and Maven 3.9+:
```bash
mvn test
```
The root POM is a multi-module build. Unit tests cover validation and demo score calculation. A small Cucumber feature documents the end-to-end business scenario.

## Architecture and extension points
- Kafka topics: `credit.application.submitted`, `credit.decision.completed`.
- MySQL stores decision outcomes.
- API requests carry a caller-generated `applicantId`; replace with an authenticated principal in production.
- Demo services use simple configuration and log events; add schema registry, retries, dead-letter topics, idempotency, outbox pattern, and distributed tracing before production.
