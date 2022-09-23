package Blogic.Business;

import Blogic.BusinessPayments;
import Model.Cart.Enums.BusinessType;
import Model.Payment.Enums.InstrumentType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
public abstract class BaseBusiness {
    private final BusinessType businessType;
    private final List<BusinessPayments> businessPaymentsList;

    public BaseBusiness(BusinessType businessType) {
        this.businessType = businessType;
        this.businessPaymentsList = new ArrayList<>();
    }


    public void addAllowedPaymentInstrument(InstrumentType type, double allowedLimit, int priority) {
        List<BusinessPayments> businessPaymentsList = getAllowedPaymentInstrument();
        if (businessPaymentsList.size() > 0 && (priority < businessPaymentsList.get(businessPaymentsList.size() - 1).getPriority())) {
            System.out.println("Wrong priority");
        }
        BusinessPayments businessPayments = new BusinessPayments(type, allowedLimit, priority);
        businessPaymentsList.add(businessPayments);
    }

    public List<BusinessPayments> getAllowedPaymentInstrument() {
        Collections.sort(businessPaymentsList, new Comparator<BusinessPayments>() {
            @Override
            public int compare(BusinessPayments o1, BusinessPayments o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });
        return businessPaymentsList;
    }

}
