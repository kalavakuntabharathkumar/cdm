package com.portfolio.credit.decisionservice;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class DecisionConsumer {
  private final DecisionRepository repository;
  public DecisionConsumer(DecisionRepository repository) { this.repository = repository; }
  @KafkaListener(topics="credit.decision.completed", groupId="decision-service")
  public void save(Map<String,Object> event) {
    String id=String.valueOf(event.get("applicantId"));
    String outcome=String.valueOf(event.get("outcome"));
    double ratio=Double.parseDouble(String.valueOf(event.get("debtRatio")));
    repository.findByApplicantId(id).ifPresentOrElse(existing -> { existing.outcome=outcome; existing.debtRatio=ratio; repository.save(existing); },
      () -> repository.save(new DecisionEntity(id,outcome,ratio)));
  }
}
