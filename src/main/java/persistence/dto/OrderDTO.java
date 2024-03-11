package persistence.dto;

import java.time.Instant;
import java.util.Set;

public record OrderDTO(
        Integer id,
        CustomerDTO customer,
        Instant orderedAt,
        Set<OrderItemDTO> orderItems
) {}