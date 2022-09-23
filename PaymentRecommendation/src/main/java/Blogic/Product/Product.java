package Blogic.Product;

import Blogic.Business.BaseBusiness;
import Blogic.Business.CreditCardPaymentBusiness;
import Blogic.Business.ECommerceBusiness;
import Blogic.Business.InvestmentBusiness;
import Model.Cart.Cart;
import Model.Cart.Enums.BusinessType;
import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private final Cart cart;
    private final BaseBusiness business;

    public Product(Double amount, BusinessType type) throws Exception {
        cart = new Cart(UUID.randomUUID().toString(), amount);
        switch (type) {
            case CREDIT_CARD_PAYMENT:
                business = new CreditCardPaymentBusiness();
                break;
            case ECOMMERCE:
                business = new ECommerceBusiness();
                break;
            case INVESTMENT:
                business = new InvestmentBusiness();
                break;
            default:
                throw new Exception("Wrong Business Type");

        }

    }
}
