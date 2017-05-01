package com.example.billing;

import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private final RestTemplate restTemplate;

    private final String url;

    public BillingClient(String url) {
        this.url = url;
        restTemplate = new RestTemplate();
    }

    public void billUser(String userId, Integer amount) {
        restTemplate.postForEntity (url, amount, String.class);
    }
}

