package Blogic.Recommendation;

import Blogic.BusinessPayments;
import Blogic.Payment.Payment;
import Blogic.Product.Product;
import Manager.CartManager;
import Manager.PaymentInstrumentManager;
import Manager.UserManager;
import Model.Payment.Enums.InstrumentType;
import Model.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentRecommendation {

    private static UserManager userManager;
    private static PaymentInstrumentManager paymentInstrumentManager;
    private static CartManager cartManager;

    public PaymentRecommendation(UserManager userManager, PaymentInstrumentManager paymentInstrumentManager, CartManager cartManager) {
        this.userManager = userManager;
        this.paymentInstrumentManager = paymentInstrumentManager;
        this.cartManager = cartManager;
    }

    private List<Payment> getPaymentRecommendation(String userId, Product product) {

        List<Payment> finalRecommendation = new ArrayList<>();
        User user = userManager.getUser(userId);
        if (user == null) {
            System.out.println("No such User");
            return null;
        }

        List<Product> products = cartManager.getFromCart(user);

        if (products.size() == 0 || !products.contains(product)) {
            System.out.println("Product not in card");
            return null;
        }
        List<Payment> userPayments = paymentInstrumentManager.getPaymentInstrument(user);

        if (userPayments.size() == 0) {
            System.out.println("No Payment Instrument of user");
            return null;
        }

        List<BusinessPayments> businessPayments = product.getBusiness().getAllowedPaymentInstrument();

        if (businessPayments.size() == 0) {
            System.out.println("No Payment Allowed for the business");
            return null;
        }
        List<InstrumentType> instrumentTypes = new ArrayList<>();
        for (BusinessPayments allowedBusinessPayment : businessPayments) {
            if (allowedBusinessPayment.getAllowedLimit() < product.getCart().getAmount()) {
                continue;
            }
            instrumentTypes.add(allowedBusinessPayment.getInstrumentType());
        }

        List<Payment> payments = paymentInstrumentManager.getBestEnabledPaymentInstrumentByInstrument(user, instrumentTypes);
        finalRecommendation.addAll(payments);

        if (finalRecommendation.size() == 0) {
            System.out.println("No recommendation found");
            return null;
        }
        return finalRecommendation;
    }

//    public List<Payment> getPaymentRecommendation(String userId) {
//
//        List<Payment> finalRecommendationFOrAllProducts = new ArrayList<>();
//
//        User user = userManager.getUser(userId);
//        if (user == null) {
//            System.out.println("No such User");
//            return null;
//        }
//
//        List<Product> products = cartManager.getFromCart(user);
//
//        if (products.size() == 0) {
//            System.out.println("No product in cart");
//        }
//
//        for (Product product : products) {
//            List<Payment> payment = getPaymentRecommendation(userId, product);
//            finalRecommendationFOrAllProducts.addAll(payment);
//        }
//        if (finalRecommendationFOrAllProducts.size() == 0) {
//            System.out.println("No recommendation found");
//            return null;
//        }
//        return finalRecommendationFOrAllProducts;
//
//    }

    public void changeUpiState(String userId, boolean state) {
        User user = userManager.getUser(userId);
        if (user == null) {
            System.out.println("No such User");
        }
        userManager.updateDeviceConfig(user, state);
        List<Payment> payments = paymentInstrumentManager.getBestEnabledPaymentInstrumentByInstrument(user, Arrays.asList(InstrumentType.UPI));
        if (payments.size() == 0) {
            System.out.println("No Upi Payment for the user");
        }
        paymentInstrumentManager.updateIsPaymentEnabledByInstrumentType(user, InstrumentType.UPI, state);

        System.out.println("Successfully updated the state  to upi state " + state);
    }

    public void printPaymentRecommendation(String userId, Product product) {

        List<Payment> recommendation = getPaymentRecommendation(userId, product);

        if (recommendation == null || recommendation.size() == 0) {
            return;
        }
        for (Payment payment : recommendation) {
            System.out.println("Issuer " + payment.getPaymentInstrument().getIssuer() + " InstrumentType " + payment.getPaymentInstrument().getInstrumentType() + " Relevance Score " + payment.getPaymentInstrument().getRelevanceScore());
        }
    }

}
