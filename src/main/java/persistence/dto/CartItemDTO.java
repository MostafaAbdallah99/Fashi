package persistence.dto;


import java.math.BigDecimal;

public record CartItemDTO(
        CartItemIdDTO id,
        ProductDTO product,
        Integer quantity,
        BigDecimal amount
) {}