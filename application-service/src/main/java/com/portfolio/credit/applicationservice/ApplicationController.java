package com.portfolio.credit.applicationservice;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
@RestController
public class ApplicationController {
  private final KafkaTemplate<String,Object> kafka;
  public ApplicationController(KafkaTemplate<String,Object> kafka) { this.kafka = kafka; }
  @PostMapping("/applications")
  public Map<String,Object> submit(@RequestBody Map<String,Object> request) {
    String id = String.valueOf(request.getOrDefault("applicantId", ""));
    double income = number(request.get("annualIncome"));
    double debt = number(request.get("monthlyDebt"));
    double amount = number(request.get("requestedAmount"));
    if (id.isBlank() || income <= 0 || debt < 0 || amount <= 0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "applicantId, positive annualIncome/requestedAmount, and non-negative monthlyDebt are required");
    Map<String,Object> event = Map.of("applicantId", id, "annualIncome", income, "monthlyDebt", debt, "requestedAmount", amount);
    kafka.send("credit.application.submitted", id, event);
    return Map.of("status","ACCEPTED","applicantId",id,"message","Application submitted for asynchronous demo evaluation");
  }
  private double number(Object value) { try { return Double.parseDouble(String.valueOf(value)); } catch(Exception e) { return -1; } }
}
