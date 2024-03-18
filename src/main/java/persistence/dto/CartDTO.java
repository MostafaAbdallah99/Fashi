package persistence.dto;


import java.util.Set;

public record CartDTO(
        Integer id,
        Set<CartItemDTO> cartItems
) {
}
