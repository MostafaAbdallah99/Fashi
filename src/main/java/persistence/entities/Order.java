package persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "ordered_at", nullable = false)
    private Instant orderedAt;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    public void addProduct(Product product, Integer quantity, BigDecimal amount) {
        OrderItem orderItem = new OrderItem(this, product, quantity, amount);
        orderItems.add(orderItem);
    }

    public void removeProduct(Product product) {
        for(Iterator<OrderItem> iterator = orderItems.iterator(); iterator.hasNext();) {
            OrderItem orderItem = iterator.next();
            if(orderItem.getProduct().equals(product) && orderItem.getOrder().equals(this)) {
                iterator.remove();
                orderItem.setOrder(null);
                orderItem.setProduct(null);
            }
        }
    }


}