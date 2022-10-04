package com.paymentrecommendation.service;

import java.util.List;
import com.paymentrecommendation.models.cart.Cart;
import com.paymentrecommendation.models.user.PaymentInstrument;
import com.paymentrecommendation.models.user.User;

public interface PaymentRecommender {
    List<PaymentInstrument> recommendPaymentInstruments(final User user, final Cart cart);
}
