package com.example.billing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Slf4j
public class BillingClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String url;

    public BillingClient(String url) {
        this.url = url;
    }

    @HystrixCommand(fallbackMethod = "backupbillUser")
    public void billUser(String userId, Integer amount) {
        restTemplate.postForEntity(url, amount, String.class);
    }

    public void backupbillUser(String userId, Integer amount) {
        log.info(String.format("Error %s : %d", userId, amount));
    }
}

