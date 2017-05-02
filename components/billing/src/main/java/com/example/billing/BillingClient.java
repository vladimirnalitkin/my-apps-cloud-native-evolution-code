package com.example.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    @Autowired
    private  RestTemplate restTemplate;

    private final String url;

    public BillingClient(String url) {
        this.url = url;
    }

    public void billUser(String userId, Integer amount) {
        restTemplate.postForEntity (url, amount, String.class);
    }
}

