package Blogic.Business;

import Blogic.BusinessPayments;
import Model.Cart.Enums.BusinessType;
import Model.Payment.Enums.InstrumentType;

import java.util.ArrayList;
import java.util.Arrays;

public class InvestmentBusiness extends BaseBusiness {

    public InvestmentBusiness() {
        super(BusinessType.INVESTMENT);
        defaultAllowedPaymentInstrument();
    }

    private void defaultAllowedPaymentInstrument() {
        BusinessPayments UPI = new BusinessPayments(InstrumentType.UPI, 100000.0, 1);
        BusinessPayments NET_BANKING = new BusinessPayments(InstrumentType.NET_BANKING, 150000.0, 2);
        BusinessPayments DEBIT_CARD = new BusinessPayments(InstrumentType.DEBIT_CARD, 150000.0, 3);
        getAllowedPaymentInstrument().addAll(new ArrayList<>(Arrays.asList(UPI, NET_BANKING, DEBIT_CARD)));
    }
}
