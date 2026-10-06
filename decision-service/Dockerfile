FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY api-gateway/src api-gateway/src
COPY application-service/pom.xml application-service/pom.xml
COPY application-service/src application-service/src
COPY risk-service/pom.xml risk-service/pom.xml
COPY risk-service/src risk-service/src
COPY decision-service/pom.xml decision-service/pom.xml
COPY decision-service/src decision-service/src
COPY notification-service/pom.xml notification-service/pom.xml
COPY notification-service/src notification-service/src
RUN mvn -B -pl :decision-service -am -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/decision-service/target/decision-service-1.0.0.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]
