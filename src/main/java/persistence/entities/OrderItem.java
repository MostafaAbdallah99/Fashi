package persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "order_item")
public class OrderItem {
    @EmbeddedId
    private OrderItemPK id;

    @ManyToOne
    @JoinColumn(name = "product_id",  nullable=false, insertable=false, updatable=false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false, insertable=false, updatable=false)
    private Order order;

}

