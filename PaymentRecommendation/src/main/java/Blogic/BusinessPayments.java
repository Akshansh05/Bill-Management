package Blogic;

import Model.Payment.Enums.InstrumentType;
import lombok.Getter;

@Getter
public class BusinessPayments {
    private final InstrumentType instrumentType;
    private double allowedLimit;
    private int priority;

    public BusinessPayments(InstrumentType instrumentType, Double allowedLimit, int priority) {
        this.instrumentType = instrumentType;
        this.allowedLimit = allowedLimit;
        this.priority = priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAllowedLimit(double allowedLimit) {
        this.allowedLimit = allowedLimit;
    }
}
