package persistence.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ordered_at", nullable = false, columnDefinition = "TIMESTAMP default current_timestamp")
    private Date orderedAt;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    private List<OrderItem> orderItems;
}
