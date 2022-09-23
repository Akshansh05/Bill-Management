package Blogic.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(Issuer issuer, boolean isEnabled, double relevanceScore) {
        super(issuer, InstrumentType.CREDIT_CARD, isEnabled, relevanceScore);
    }
}
