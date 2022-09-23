package Model.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInstrument {
    private String id;
    private Issuer issuer;
    private InstrumentType instrumentType;
    private boolean isPaymentEnabled;
    private double relevanceScore;
}
