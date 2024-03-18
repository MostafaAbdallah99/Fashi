package persistence.repository.interfaces;

import jakarta.persistence.EntityManager;
import persistence.entities.CartItem;
import java.util.List;

public interface CartRepository {
    List<CartItem> getCartItems(int cartId, EntityManager entityManager);
    boolean cartReset(int cartId, EntityManager entityManager);
}
