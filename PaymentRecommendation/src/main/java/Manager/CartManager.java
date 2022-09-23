package Manager;

import Blogic.Product.Product;
import Model.Cart.Enums.BusinessType;
import Model.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CartManager {
    private final HashMap<String, List<Product>> userCarts;

    public CartManager() {
        userCarts = new HashMap<>();
    }

    public Product addToCart(User user, String business, double amount) throws Exception {
        Product product = new Product(amount, BusinessType.valueOf(business));
        List<Product> products;
        if (userCarts.containsKey(user.getId())) {
            products = userCarts.get(user.getId());
            products.add(product);
        } else {
            products = new ArrayList<>(Arrays.asList(product));
        }
        userCarts.put(user.getId(), products);
        return product;
    }

    public List<Product> getFromCart(User user) {
        if (userCarts.containsKey(user.getId())) {
            return userCarts.get(user.getId());
        }
        return null;
    }

    public Double getTotalAmount(User user) {
        if (userCarts.containsKey(user.getId())) {
            List<Product> businesses = userCarts.get(user.getId());
            return businesses.stream().mapToDouble(x -> x.getCart().getAmount()).sum();
        }
        return null;
    }

}
