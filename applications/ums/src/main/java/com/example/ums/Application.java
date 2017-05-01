package com.example.ums;

import com.example.billing.BillingClient;
import com.example.subscriptions.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    NamedParameterJdbcTemplate datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.debug("********Setting up database********");
        jdbcTemplate.execute("DROP TABLE subscriptions IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE subscriptions(" +
                "id SERIAL, userId VARCHAR(255), packageId VARCHAR(255))");
    }

    @Bean
    public SubscriptionRepository subscriptionRepository() {
        return new SubscriptionRepository(datasource);
    }

    @Bean
    public BillingClient billingClient(@Value("${billingEndpoint}") String billingEndpoint) {
        return new BillingClient(billingEndpoint);
    }
}
