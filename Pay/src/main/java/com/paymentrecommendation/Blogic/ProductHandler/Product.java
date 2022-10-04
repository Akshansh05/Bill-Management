package com.paymentrecommendation.Blogic.ProductHandler;

import com.paymentrecommendation.Blogic.BusinessHandler.BaseBusiness;
import com.paymentrecommendation.Blogic.BusinessHandler.CreditCardPaymentBusiness;
import com.paymentrecommendation.Blogic.BusinessHandler.ECommerceBusiness;
import com.paymentrecommendation.Blogic.BusinessHandler.InvestmentBusiness;
import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.models.cart.Cart;
import lombok.Getter;

@Getter
public class Product {
    private final BaseBusiness business;
    private final Cart cart;


    public Product(Cart cart) throws Exception {
        this.cart = cart;
        LineOfBusiness type = cart.getLineOfBusiness();
        if (type == null) {
            throw new Exception("The line of business is not supported");
        }
        switch (type) {
            case CREDIT_CARD_BILL_PAYMENT:
                business = new CreditCardPaymentBusiness();
                break;
            case COMMERCE:
                business = new ECommerceBusiness();
                break;
            case INVESTMENT:
                business = new InvestmentBusiness();
                break;
            default:
                throw new Exception("The line of business is not supported");

        }
    }
}