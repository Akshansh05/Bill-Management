package com.paymentrecommendation.Blogic.BusinessHandler;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.ArrayList;
import java.util.Arrays;

public class InvestmentBusiness extends BaseBusiness {
    public InvestmentBusiness() {
        super(LineOfBusiness.INVESTMENT);
        defaultAllowedPaymentInstrument();
    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments UPI = new BusinessPayments(PaymentInstrumentType.UPI, 100000.0, 1);
        BusinessPayments NETBANKING = new BusinessPayments(PaymentInstrumentType.NETBANKING, 150000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(PaymentInstrumentType.DEBIT_CARD, 150000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(UPI, NETBANKING, DEBIT_CARD)));
    }
}
