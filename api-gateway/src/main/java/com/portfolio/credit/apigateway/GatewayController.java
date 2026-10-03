package com.portfolio.credit.apigateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class GatewayController {
  private final RestClient client = RestClient.create();
  @Value("${APPLICATION_URL:http://localhost:8081}") String applicationUrl;
  @Value("${DECISION_URL:http://localhost:8084}") String decisionUrl;
  @PostMapping("/applications")
  public ResponseEntity<?> submit(@RequestBody Map<String,Object> body) {
    return client.post().uri(applicationUrl + "/applications").contentType(MediaType.APPLICATION_JSON).body(body).retrieve().toEntity(Map.class);
  }
  @GetMapping("/decisions/{applicantId}")
  public ResponseEntity<?> decision(@PathVariable String applicantId) {
    return client.get().uri(decisionUrl + "/decisions/" + applicantId).retrieve().toEntity(Map.class);
  }
}
