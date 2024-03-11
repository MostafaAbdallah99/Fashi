package persistence.dto;

import java.io.Serializable;


public record CartItemIdDTO(Integer cartId, Integer productId) implements Serializable {
}