package com.paymentrecommendation.manager;

import com.paymentrecommendation.Blogic.BusinessHandler.BusinessPayments;
import com.paymentrecommendation.Blogic.ProductHandler.Product;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.ArrayList;
import java.util.List;

public class BusinessPaymentManager {

    private final Product product;

    public BusinessPaymentManager(Product product) {
        this.product = product;
    }

    public List<PaymentInstrumentType> getInstrumentTypeForProducts() {

        List<PaymentInstrumentType> instrumentTypes = new ArrayList<>();
        List<BusinessPayments> businessPayments = product.getBusiness().getAllowedPaymentInstrument();

        if (businessPayments.size() == 0) {
            System.out.println("No Payment Allowed for the business");
            return instrumentTypes;
        }
        for (BusinessPayments allowedBusinessPayment : businessPayments) {
            if (allowedBusinessPayment.getAllowedLimit() < product.getCart().getCartDetail().getCartAmount()) {
                continue;
            }
            instrumentTypes.add(allowedBusinessPayment.getInstrumentType());
        }
        return instrumentTypes;
    }
}
