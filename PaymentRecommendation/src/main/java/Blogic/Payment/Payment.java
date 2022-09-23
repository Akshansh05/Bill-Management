package Blogic.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;
import Model.Payment.PaymentInstrument;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment {
    private final PaymentInstrument paymentInstrument;

    public Payment(Issuer issuer, InstrumentType instrumentType, boolean isEnabled, double relevanceScore) {
        paymentInstrument = new PaymentInstrument(UUID.randomUUID().toString(), issuer, instrumentType, isEnabled, relevanceScore);
    }

    public void setIsEnabled(boolean isEnabled) {
        paymentInstrument.setPaymentEnabled(isEnabled);
    }
}
