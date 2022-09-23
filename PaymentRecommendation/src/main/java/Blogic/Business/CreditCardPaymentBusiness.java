package Blogic.Business;

import Blogic.BusinessPayments;
import Model.Cart.Enums.BusinessType;
import Model.Payment.Enums.InstrumentType;

import java.util.ArrayList;
import java.util.Arrays;

public class CreditCardPaymentBusiness extends BaseBusiness {

    public CreditCardPaymentBusiness() {
        super(BusinessType.CREDIT_CARD_PAYMENT);
        defaultAllowedPaymentInstrument();
    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments UPI = new BusinessPayments(InstrumentType.UPI, 200000.0, 1);
        BusinessPayments NET_BANKING = new BusinessPayments(InstrumentType.NET_BANKING, 200000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(InstrumentType.DEBIT_CARD, 200000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(UPI, NET_BANKING, DEBIT_CARD)));
    }
}
