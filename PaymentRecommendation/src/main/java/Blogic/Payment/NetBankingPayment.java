package Blogic.Payment;

import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;

public class NetBankingPayment extends Payment {



    public NetBankingPayment(Issuer issuer, boolean isEnabled, double relevanceScore) {
        super(issuer, InstrumentType.NET_BANKING, isEnabled, relevanceScore);
    }
}
