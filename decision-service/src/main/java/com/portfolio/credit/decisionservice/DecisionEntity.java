package com.portfolio.credit.decisionservice;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="credit_decisions")
public class DecisionEntity {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  @Column(nullable=false, unique=true) public String applicantId;
  @Column(nullable=false) public String outcome;
  public double debtRatio;
  public Instant createdAt = Instant.now();
  protected DecisionEntity() {}
  public DecisionEntity(String applicantId, String outcome, double debtRatio) { this.applicantId=applicantId; this.outcome=outcome; this.debtRatio=debtRatio; }
}
