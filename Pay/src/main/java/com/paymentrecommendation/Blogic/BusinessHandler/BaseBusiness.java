package com.paymentrecommendation.Blogic.BusinessHandler;

import com.paymentrecommendation.enums.LineOfBusiness;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
public abstract class BaseBusiness {
    private final LineOfBusiness businessType;
    private final List<BusinessPayments> businessPaymentsList;

    public BaseBusiness(LineOfBusiness businessType) {
        this.businessType = businessType;
        this.businessPaymentsList = new ArrayList<>();
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
