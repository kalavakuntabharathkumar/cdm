package com.portfolio.credit.decisionservice;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
@RestController
public class DecisionController {
  private final DecisionRepository repository;
  public DecisionController(DecisionRepository repository) { this.repository=repository; }
  @GetMapping("/decisions/{applicantId}")
  public Map<String,Object> get(@PathVariable String applicantId) {
    DecisionEntity d=repository.findByApplicantId(applicantId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Decision not available yet"));
    return Map.of("applicantId",d.applicantId,"outcome",d.outcome,"debtRatio",d.debtRatio,"createdAt",d.createdAt.toString());
  }
}
