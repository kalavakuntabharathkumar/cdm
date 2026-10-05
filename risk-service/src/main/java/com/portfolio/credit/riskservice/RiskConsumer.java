package com.portfolio.credit.riskservice;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class RiskConsumer {
  private final KafkaTemplate<String,Object> kafka;
  public RiskConsumer(KafkaTemplate<String,Object> kafka) { this.kafka = kafka; }
  @KafkaListener(topics="credit.application.submitted", groupId="risk-service")
  public void evaluate(Map<String,Object> app) {
    double income = Double.parseDouble(String.valueOf(app.get("annualIncome")));
    double debt = Double.parseDouble(String.valueOf(app.get("monthlyDebt")));
    double amount = Double.parseDouble(String.valueOf(app.get("requestedAmount")));
    // Transparent synthetic rule for demonstration only; not a credit model.
    double debtRatio = (debt * 12.0) / income;
    String outcome = (debtRatio < 0.35 && amount <= income * 0.25) ? "REVIEW_APPROVED_DEMO" : "REVIEW_REQUIRED_DEMO";
    kafka.send("credit.decision.completed", String.valueOf(app.get("applicantId")),
      Map.of("applicantId", app.get("applicantId"), "outcome", outcome, "debtRatio", debtRatio));
  }
}
