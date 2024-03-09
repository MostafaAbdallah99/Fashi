package persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
class CartItemPK implements Serializable {
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_id")
    private int productId;
}