package persistence.dto;


public record OrderItemIdDTO(
        Integer orderId,
        Integer productId
) {}