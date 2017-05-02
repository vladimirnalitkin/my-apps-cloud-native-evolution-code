package com.example.ums.config;

import com.example.billing.BillingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by vnalitkin on 5/1/17.
 */
@Configuration
@Profile("cloud")
@EnableDiscoveryClient
public class CloudConf {

    @Bean
    public BillingClient billingClient() {
        URI uri = UriComponentsBuilder.fromUriString("//bulling-service/reoccuringPayments")
                .build()
                .toUri();

        return new BillingClient(uri.toString());
    }

}
