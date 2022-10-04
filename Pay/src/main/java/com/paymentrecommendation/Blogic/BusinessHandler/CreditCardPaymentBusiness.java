package com.paymentrecommendation.Blogic.BusinessHandler;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.ArrayList;
import java.util.Arrays;

public class CreditCardPaymentBusiness extends BaseBusiness {
    public CreditCardPaymentBusiness() {
        super(LineOfBusiness.CREDIT_CARD_BILL_PAYMENT);
        defaultAllowedPaymentInstrument();
    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments UPI = new BusinessPayments(PaymentInstrumentType.UPI, 200000.0, 1);
        BusinessPayments NETBANKING = new BusinessPayments(PaymentInstrumentType.NETBANKING, 200000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(PaymentInstrumentType.DEBIT_CARD, 200000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(UPI, NETBANKING, DEBIT_CARD)));
    }
}
