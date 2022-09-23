package Manager;

import Blogic.Payment.*;
import Model.Payment.Enums.InstrumentType;
import Model.Payment.Enums.Issuer;
import Model.User.User;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;


public class PaymentInstrumentManager {

    private final HashMap<String, List<Payment>> userPayments;

    public PaymentInstrumentManager() {
        this.userPayments = new HashMap<>();
    }

    public Payment addPaymentInstrument(User user, String paymentInstrument, boolean isEnabled, double relevanceScore) throws Exception {
        String splits[] = paymentInstrument.split(" ");
        String issuer = splits[0];
        String instrument = splits[1];
        InstrumentType instrumentType = InstrumentType.valueOf(instrument);
        Issuer issuerType = Issuer.valueOf(issuer);
        Payment payment;
        switch (instrumentType) {
            case CREDIT_CARD:
                payment = new CreditCardPayment(issuerType, isEnabled, relevanceScore);
                break;
            case UPI:
                payment = new UpiPayment(issuerType, isEnabled, relevanceScore);
                break;
            case NET_BANKING:
                payment = new NetBankingPayment(issuerType, isEnabled, relevanceScore);
                break;
            case DEBIT_CARD:
                payment = new DebitCardPayment(issuerType, isEnabled, relevanceScore);
                break;
            default:
                throw new Exception("Wrong Available Instrument");
        }

        List<Payment> payments;
        if (userPayments.containsKey(user.getId())) {
            payments = userPayments.get(user.getId());
            payments.add(payment);
        } else {
            payments = new ArrayList<>(Arrays.asList(payment));
        }
        userPayments.put(user.getId(), payments);
        return payment;
    }


    public List<Payment> getPaymentInstrument(@NotNull User user) {
        if (userPayments.containsKey(user.getId())) {
            return userPayments.get(user.getId());
        }
        return null;
    }

    public List<Payment> getBestEnabledPaymentInstrument(@NotNull User user) {
        List<Payment> payments = getPaymentInstrument(user);
        List<Payment> filteredList = payments.stream().filter(x -> x.getPaymentInstrument().isPaymentEnabled()).collect(Collectors.toList());
        Collections.sort(filteredList, new Comparator<Payment>() {
            @Override
            public int compare(Payment o1, Payment o2) {
                final double v = o2.getPaymentInstrument().getRelevanceScore() - o1.getPaymentInstrument().getRelevanceScore();
                return (int) v;
            }
        });
        return filteredList;
    }

    public List<Payment> getBestEnabledPaymentInstrumentByInstrument(@NotNull User user, List<InstrumentType> instrumentType) {
        List<Payment> payments = getPaymentInstrument(user);
        List<Payment> filteredList = payments.stream().filter(x -> x.getPaymentInstrument().isPaymentEnabled() && isValidInstrumentType(instrumentType, x.getPaymentInstrument().getInstrumentType())).collect(Collectors.toList());

        Collections.sort(filteredList, new Comparator<Payment>() {
            @Override
            public int compare(Payment o1, Payment o2) {
                final double v = o2.getPaymentInstrument().getRelevanceScore() - o1.getPaymentInstrument().getRelevanceScore();
                return (int) v;
            }
        });
        return filteredList;
    }

    private boolean isValidInstrumentType(List<InstrumentType> instrumentTypes, InstrumentType instrumentType) {

        if (instrumentTypes.size() == 0)
            return false;
        for (InstrumentType type : instrumentTypes) {
            if (type.toString().equals(instrumentType.toString())) {
                return true;
            }
        }
        return false;
    }

    public void updateRelevanceScore(User user, Payment payment, double relevanceScore) {
        if (userPayments.containsKey(user.getId())) {
            List<Payment> payments = userPayments.get(user.getId());
            payments.stream().filter(x -> x.getPaymentInstrument().getId().equals(payment.getPaymentInstrument().getId())).forEach(y -> y.getPaymentInstrument().setRelevanceScore(relevanceScore));
        }
    }

    public void updateIsPaymentEnabledByInstrumentType(User user, InstrumentType instrumentType, boolean isEnabled) {
        if (userPayments.containsKey(user.getId())) {
            List<Payment> payments = userPayments.get(user.getId());
            payments.stream().filter(x -> x.getPaymentInstrument().getInstrumentType().toString().equals(instrumentType.toString())).forEach(y -> y.getPaymentInstrument().setPaymentEnabled(isEnabled));
        }
    }

    public void updateIsPaymentEnabledByIssuerType(User user, Issuer issuer, boolean isEnabled) {
        if (userPayments.containsKey(user.getId())) {
            List<Payment> payments = userPayments.get(user.getId());
            payments.stream().filter(x -> x.getPaymentInstrument().getIssuer().toString().equals(issuer.toString())).forEach(y -> y.getPaymentInstrument().setPaymentEnabled(isEnabled));
        }
    }
}
