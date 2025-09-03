package com.example.bfh.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void startProcess() {
        try {
            // Step 1: Call generateWebhook API
            String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("name", "John Doe");
            requestBody.put("regNo", "REG12347"); // <-- change your RegNo
            requestBody.put("email", "john@example.com");

            ResponseEntity<Map> response = restTemplate.postForEntity(url, requestBody, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String webhookUrl = (String) response.getBody().get("webhook");
                String accessToken = (String) response.getBody().get("accessToken");

                // Step 2: Solve SQL (manual step: check Question 1 or 2 PDF)
                String finalQuery = solveSqlQuestion("47"); // pass last two digits

                // Step 3: Submit final query
                submitFinalQuery(webhookUrl, accessToken, finalQuery);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String solveSqlQuestion(String lastDigits) {
        int num = Integer.parseInt(lastDigits);
        if (num % 2 == 0) {
            // Even → Question 2 (download PDF)
            return "SELECT ... FROM ... WHERE ..."; // replace with actual SQL
        } else {
            // Odd → Question 1 (download PDF)
            return "SELECT ... FROM ... JOIN ..."; // replace with actual SQL
        }
    }

    private void submitFinalQuery(String webhookUrl, String token, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                request,
                String.class
        );

        System.out.println("Submission Response: " + response.getBody());
    }
}
