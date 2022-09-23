package Blogic.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;

public class UpiPayment extends Payment {
    public UpiPayment(Issuer issuer, boolean isEnabled, double relevanceScore) {
        super(issuer, InstrumentType.UPI, isEnabled, relevanceScore);
    }
}
