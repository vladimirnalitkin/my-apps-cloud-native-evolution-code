package com.example.billing.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Builder
public class BillingMessage implements Serializable {
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private Integer amount;

}
