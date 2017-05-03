package com.example.billing.controller;


import com.example.billing.model.BillingMessage;
import com.example.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitComtroller {

    @Autowired
    private Gateway paymentGateway;

    @RabbitListener(queues = "${queueName}")
    public void receiveMessage(BillingMessage message) {
        System.out.println("Received <" + message.getUserId() + " on " + message.getAmount() +">");
        int amount = message.getAmount();

        // you may want to move this conditional logic into another method/function since it is now
        // duplicated between the controller and this class. unfortunately there really isn't
        // enough code to know for sure right now.
        if (paymentGateway.createReocurringPayment(amount)){
            System.out.println("Successfully created payment for <" + amount + ">");
        } else {
            System.out.println("Creating payment for <" + amount + "> failed.");
        }
    }
}
