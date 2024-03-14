package persistence.repository.interfaces;

import persistence.entities.CartItem;
import persistence.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository {
    List<CartItem> getCartItems(int cartId);
    boolean cartReset(int cartId);
}
