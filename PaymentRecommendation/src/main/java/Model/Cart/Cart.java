package Model.Cart;

import Model.Cart.Enums.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {
    private String id;
    private final double amount;
}
