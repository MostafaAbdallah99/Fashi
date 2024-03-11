package persistence.dto;


import java.math.BigDecimal;

public record OrderItemDTO(
        OrderItemIdDTO id,
        OrderDTO order,
        ProductDTO product,
        Integer quantity,
        BigDecimal amount
) {}