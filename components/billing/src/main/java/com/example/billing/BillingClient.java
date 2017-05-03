package com.example.billing;


public interface BillingClient {
    void billUser(String userId, Integer amount);
}
