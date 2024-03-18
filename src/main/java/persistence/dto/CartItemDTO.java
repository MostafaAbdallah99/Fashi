package persistence.dto;


import java.math.BigDecimal;

public record CartItemDTO(
        CartItemIdDTO id,
//        CartDTO cart,
        ProductDTO product,
        Integer quantity,
        BigDecimal amount
) {}