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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public void addProduct(Product product, Integer quantity, BigDecimal amount) {
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