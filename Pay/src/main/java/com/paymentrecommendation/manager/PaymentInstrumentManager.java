package com.paymentrecommendation.manager;

import com.paymentrecommendation.Blogic.ProductHandler.Product;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.cart.Cart;
import com.paymentrecommendation.models.user.DeviceContext;
import com.paymentrecommendation.models.user.PaymentInstrument;
import com.paymentrecommendation.models.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PaymentInstrumentManager {

    private final User user;
    private final Product product;

    public PaymentInstrumentManager(User user, Cart cart) throws Exception {
        doBasicValidation(user, cart);
        this.product = new Product(cart);
        this.user = user;
    }

    private void doBasicValidation(User user, Cart cart) {
        if (user == null || cart == null){
            throw new RuntimeException("User or Cart cannot be null");
        }
    }

    public List<PaymentInstrument> getBestEnabledPaymentInstrumentByInstrument(List<PaymentInstrumentType> instrumentTypes) {
        List<PaymentInstrument> finalList = new ArrayList<>();
        List<PaymentInstrument> payments = user.getUserPaymentInstrument().getPaymentInstruments();

        for (PaymentInstrumentType instrumentType : instrumentTypes) {
            List<PaymentInstrument> filteredList = payments.stream().filter(x -> isValidInstrumentType(instrumentType, x.getPaymentInstrumentType())).collect(Collectors.toList());

            Collections.sort(filteredList, new Comparator<PaymentInstrument>() {
                @Override
                public int compare(PaymentInstrument o1, PaymentInstrument o2) {
                    return (o2.getRelevanceScore() > o1.getRelevanceScore() ? 1 : -1);
                }
            });
            finalList.addAll(filteredList);
        }
        return finalList;
    }

    private boolean isValidInstrumentType(PaymentInstrumentType businessType, PaymentInstrumentType instrumentType) {
        if (businessType.toString().equals(instrumentType.toString())) {
            if ((businessType.equals(PaymentInstrumentType.UPI)) && ((user.getUserContext() != null) && (user.getUserContext().getDeviceContext() != null) && (user.getUserContext().getDeviceContext().isUpiEnabled() == false))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void changeUpiState(boolean state) {
        if (user.getUserContext() == null || user.getUserContext().getDeviceContext() == null) {
            System.out.println("User Context is null");
            return;
        }
        DeviceContext deviceContext = user.getUserContext().getDeviceContext();
        deviceContext.setUpiEnabled(state);
        System.out.println("Successfully updated the state  to upi state " + state);
    }

}
