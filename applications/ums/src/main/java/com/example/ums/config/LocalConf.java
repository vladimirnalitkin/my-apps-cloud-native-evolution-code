package com.example.ums.config;

import com.example.billing.BillingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by vnalitkin on 5/1/17.
 */
@Configuration
@Profile("local")
public class LocalConf {
    @Bean
    public BillingClient billingClient(@Value("${billingEndpoint}") String billingEndpoint) {
        return new BillingClient(billingEndpoint);
    }


}
