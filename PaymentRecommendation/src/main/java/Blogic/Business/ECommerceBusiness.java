package Blogic.Business;

import Blogic.BusinessPayments;
import Model.Cart.Enums.BusinessType;
import Model.Payment.Enums.InstrumentType;

import java.util.ArrayList;
import java.util.Arrays;


public class ECommerceBusiness extends BaseBusiness {

    public ECommerceBusiness() {
        super(BusinessType.ECOMMERCE);
        defaultAllowedPaymentInstrument();

    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments CREDIT_CARD = new BusinessPayments(InstrumentType.CREDIT_CARD, 250000.0, 1);
        BusinessPayments UPI = new BusinessPayments(InstrumentType.UPI, 100000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(InstrumentType.DEBIT_CARD, 200000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(CREDIT_CARD, UPI, DEBIT_CARD)));
    }
}
