package com.example.billing;

import com.example.billing.model.BillingMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public class RabbitBillingClient implements BillingClient {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    public RabbitBillingClient(RabbitTemplate rabbitTemplate, String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    @Override
    public void billUser(String userId, Integer amount) {
        rabbitTemplate.convertAndSend(queueName, BillingMessage.builder().userId(userId).amount(amount).build());
    }
}
