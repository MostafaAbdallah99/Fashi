package persistence.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "product_name" , nullable = false, length = 50 , unique = true)
    private String name;

    @Column(name = "product_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal price;

    @Column(name = "product_description" , nullable = false, length = 300)
    private String description;

    @Column(name = "product_image" , nullable = false, length = 500)
    private String image;

    @Column(name = "product_stock" , nullable = false)
    private Integer stock;

    @Column(name = "product_size" , nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private ProductSize size;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    Set<CartItem> cartItems=new HashSet<>();

    @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    Set<OrderItem> orderItems=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", nullable = false)
    private Tag tag;


}
