package com.example.billing.controller;

import com.example.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vnalitkin on 4/28/17.
 */
@RestController
@RequestMapping("/reoccuringPayments")
public class MainController {
    @Autowired
    private Gateway paymentGateway;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Integer paymentMonthlyAmount) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_JSON.toString());
        return paymentGateway.createReocurringPayment(paymentMonthlyAmount) ?
                new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED) :
                new ResponseEntity<String>("{errors: ['Bad  request']}", responseHeaders, HttpStatus.BAD_REQUEST);
    }
}
