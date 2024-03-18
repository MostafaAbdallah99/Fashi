package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import persistence.entities.Cart;
import persistence.entities.CartItem;
import persistence.entities.CartItemId;
import persistence.entities.Product;
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

    public CartItem findCartItemById(CartItemId id) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.find(CartItem.class, id));
    }

    public boolean addToCart(int cartID, int productID, int quantity) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> {
                Cart cart = entityManager.find(Cart.class, cartID);
                CartItemId cartItemId = new CartItemId(cartID, productID);
                CartItem cartItem = findCartItemById(cartItemId);

                if (cartItem == null) {
                    cartItem = new CartItem();
                    cartItem.setId(cartItemId);
                    cartItem.setCart(cart);
                    cartItem.setProduct(entityManager.find(Product.class, productID));
                    cartItem.setQuantity(quantity);
                    cartItem.setAmount(cartItem.getProduct().getProductPrice().multiply(new java.math.BigDecimal(quantity)));
                    entityManager.persist(cartItem);
                    cart.getCartItems().add(cartItem);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    cartItem.setAmount(cartItem.getProduct().getProductPrice().multiply(new java.math.BigDecimal(cartItem.getQuantity())));
                    entityManager.merge(cartItem);
                }

                entityManager.merge(cart);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
