import Blogic.Product.Product;
import Blogic.Recommendation.PaymentRecommendation;
import Manager.CartManager;
import Manager.PaymentInstrumentManager;
import Manager.UserManager;
import Model.User.User;

public class RecommendationApplication {
    public static void main(String[] args) throws Exception {

        UserManager userManager = new UserManager();
        PaymentInstrumentManager paymentInstrumentManager = new PaymentInstrumentManager();
        CartManager cartManager = new CartManager();

        User user = userManager.addUser("Akshansh");

        paymentInstrumentManager.addPaymentInstrument(user, "HDFC CREDIT_CARD", true, 0.90);
        paymentInstrumentManager.addPaymentInstrument(user, "SBI CREDIT_CARD", true, 0.90);
        paymentInstrumentManager.addPaymentInstrument(user, "HDFC UPI", true, 0.95);
        paymentInstrumentManager.addPaymentInstrument(user, "SBI UPI", true, 0.75);
//        paymentInstrumentManager.addPaymentInstrument(user, "HDFC DEBIT_CARD", true, 0.95);
//        paymentInstrumentManager.addPaymentInstrument(user, "SBI DEBIT_CARD", true, 0.85);
//        paymentInstrumentManager.addPaymentInstrument(user, "HDFC NET_BANKING", true, 0.95);
//        paymentInstrumentManager.addPaymentInstrument(user, "SBI NET_BANKING", true, 0.85);


        Product creditCartPayment = cartManager.addToCart(user, "CREDIT_CARD_PAYMENT", 5000.0);
        Product ecommerce = cartManager.addToCart(user, "ECOMMERCE", 5000.0);


        PaymentRecommendation paymentRecommendation = new PaymentRecommendation(userManager, paymentInstrumentManager, cartManager);

        paymentRecommendation.printPaymentRecommendation(user.getId(), creditCartPayment);

        System.out.println("/////////////////////////");

        paymentRecommendation.printPaymentRecommendation(user.getId(), ecommerce);

        System.out.println("Changing State ");

        paymentRecommendation.changeUpiState(user.getId(), false);

        System.out.println("##########################");

        paymentRecommendation.printPaymentRecommendation(user.getId(), creditCartPayment);

        System.out.println("/////////////////////////");

        paymentRecommendation.printPaymentRecommendation(user.getId(), ecommerce);

    }
}
