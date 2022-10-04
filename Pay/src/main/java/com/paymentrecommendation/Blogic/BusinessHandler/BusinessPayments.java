package com.paymentrecommendation.Blogic.BusinessHandler;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import lombok.Getter;

@Getter
public class BusinessPayments {
    private final PaymentInstrumentType instrumentType;
    private double allowedLimit;
    private int priority;

    public BusinessPayments(PaymentInstrumentType instrumentType, Double allowedLimit, int priority) {
        this.instrumentType = instrumentType;
        this.allowedLimit = allowedLimit;
        this.priority = priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAllowedLimit(double allowedLimit) {
        this.allowedLimit = allowedLimit;
    }
}