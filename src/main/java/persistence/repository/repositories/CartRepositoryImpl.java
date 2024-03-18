package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import persistence.entities.Cart;
import persistence.entities.CartItem;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.CartRepository;

import java.util.List;

public class CartRepositoryImpl extends GenericRepositoryImpl<Cart, Integer> implements CartRepository {
    public CartRepositoryImpl(Class<Cart> entityClass) {
        super(entityClass);
    }


    @Override
    public List<CartItem> getCartItems(int cartId, EntityManager entityManager) {
        return entityManager.createQuery("SELECT c FROM CartItem c WHERE c.cart.id = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }

    @Override
    public boolean cartReset(int cartId, EntityManager entityManager) {
        try {
            entityManager.createQuery("DELETE FROM CartItem c WHERE c.cart.id = :cartId")
                    .setParameter("cartId", cartId)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
