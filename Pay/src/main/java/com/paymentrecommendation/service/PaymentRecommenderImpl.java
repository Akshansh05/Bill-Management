package com.paymentrecommendation.service;

import com.paymentrecommendation.Blogic.ProductHandler.Product;
import com.paymentrecommendation.manager.BusinessPaymentManager;
import com.paymentrecommendation.manager.PaymentInstrumentManager;
import com.paymentrecommendation.models.cart.Cart;
import com.paymentrecommendation.models.user.PaymentInstrument;
import com.paymentrecommendation.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class PaymentRecommenderImpl implements PaymentRecommender {

    @Override
    public List<PaymentInstrument> recommendPaymentInstruments(User user, Cart cart){
        List<PaymentInstrument> finalRecommendation = new ArrayList<>();

        PaymentInstrumentManager paymentInstrumentManager = null;
        try {
            paymentInstrumentManager = new PaymentInstrumentManager(user, cart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (paymentInstrumentManager == null) {
            System.out.println("Error in creating paymentInstrumentManager");
            return finalRecommendation;
        }
        if (cart.getCartDetail() == null || cart.getCartDetail().getCartAmount() == 0.0) {
            return finalRecommendation;
        }
        Product product = paymentInstrumentManager.getProduct();

        List<PaymentInstrument> userPayments = user.getUserPaymentInstrument().getPaymentInstruments();

        if (userPayments.size() == 0) {
            System.out.println("No Payment Instrument of user");
            return finalRecommendation;
        }

        BusinessPaymentManager businessPaymentManager = new BusinessPaymentManager(product);

        List<PaymentInstrument> payments = paymentInstrumentManager.getBestEnabledPaymentInstrumentByInstrument(businessPaymentManager.getInstrumentTypeForProducts());

        if (payments.size() == 0) {
            System.out.println("No recommendation found");
            return finalRecommendation;
        }
        finalRecommendation.addAll(payments);
        return finalRecommendation;
    }
}
