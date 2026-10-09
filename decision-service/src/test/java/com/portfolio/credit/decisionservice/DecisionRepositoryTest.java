package com.portfolio.credit.decisionservice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DecisionRepositoryTest {
  @Test void decisionEntityRetainsSubmittedValues() {
    DecisionEntity entity = new DecisionEntity("demo-1","REVIEW_REQUIRED_DEMO",0.4);
    assertEquals("demo-1", entity.applicantId);
    assertEquals("REVIEW_REQUIRED_DEMO", entity.outcome);
    assertEquals(0.4, entity.debtRatio, 0.0001);
    assertNotNull(entity.createdAt);
  }
}
