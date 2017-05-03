package com.example.ums.config;

import com.example.billing.BillingClient;
import com.example.billing.RabbitBillingClient;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


@Configuration
@Profile("cloud")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudConf {

    @Value("${queueName}")
    String queueName;

    private final ConnectionFactory connectionFactory;

    @Autowired
    public CloudConf(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public BillingClient billingClient() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        //rabbitTemplate.setRoutingKey("spring-boot");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return new RabbitBillingClient(rabbitTemplate, queueName);
    }

}

