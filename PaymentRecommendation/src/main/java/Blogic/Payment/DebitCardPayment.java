package Blogic.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;

public class DebitCardPayment extends Payment {
    public DebitCardPayment(Issuer issuer, boolean isEnabled, double relevanceScore) {
        super(issuer, InstrumentType.DEBIT_CARD, isEnabled, relevanceScore);
    }
}