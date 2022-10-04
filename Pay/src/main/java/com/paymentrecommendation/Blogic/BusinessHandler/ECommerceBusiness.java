package com.paymentrecommendation.Blogic.BusinessHandler;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.ArrayList;
import java.util.Arrays;

public class ECommerceBusiness extends BaseBusiness {
    public ECommerceBusiness() {
        super(LineOfBusiness.COMMERCE);
        defaultAllowedPaymentInstrument();

    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments CREDIT_CARD = new BusinessPayments(PaymentInstrumentType.CREDIT_CARD, 250000.0, 1);
        BusinessPayments UPI = new BusinessPayments(PaymentInstrumentType.UPI, 100000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(PaymentInstrumentType.DEBIT_CARD, 200000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(CREDIT_CARD, UPI, DEBIT_CARD)));
    }
}
