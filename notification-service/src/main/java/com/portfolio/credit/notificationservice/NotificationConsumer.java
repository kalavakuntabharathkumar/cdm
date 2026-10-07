package com.portfolio.credit.notificationservice;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class NotificationConsumer {
  @KafkaListener(topics="credit.decision.completed", groupId="notification-service")
  public void notifyApplicant(Map<String,Object> event) {
    // Demo notification sink. Replace with an approved email/SMS provider in a real system.
    System.out.printf("DEMO NOTIFICATION applicant=%s outcome=%s%n", event.get("applicantId"), event.get("outcome"));
  }
}
