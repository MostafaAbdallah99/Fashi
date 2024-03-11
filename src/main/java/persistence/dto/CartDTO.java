package persistence.dto;


import java.util.Set;

public record CartDTO(
        Integer id,
        CustomerDTO customer,
        Set<CartItemDTO> cartItems
) {
}
