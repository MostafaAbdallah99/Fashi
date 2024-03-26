package persistence.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

public record CustomerOrderDTO(
        Integer id,
        String customerName,
        Date birthday,
        String password,
        String job,
        String email,
        BigDecimal creditLimit,
        String city,
        String country,
        String streetNo,
        String streetName,
        String interests,
        CartDTO cart,
        Boolean isAdmin,
        Integer orderId,
        CustomerDTO customer,
        Instant orderedAt,
        Set<OrderItemDTO> orderItems
) {
}
