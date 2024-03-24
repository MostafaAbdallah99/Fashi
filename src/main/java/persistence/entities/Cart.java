package persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public void addProduct(Product product, Integer quantity, BigDecimal amount) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                // If the product is already in the cart, update the quantity and amount
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setAmount(cartItem.getAmount().add(amount));
                return;
            }
        }
        CartItem cartItem = new CartItem(this, product, quantity, amount);
        cartItems.add(cartItem);
    }

    public void removeProduct(Product product) {
        for(Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
            CartItem cartItem = iterator.next();
            if(cartItem.getProduct().equals(product) && cartItem.getCart().equals(this)) {
                iterator.remove();
                cartItem.setCart(null);
                cartItem.setProduct(null);
            }
        }
    }
}