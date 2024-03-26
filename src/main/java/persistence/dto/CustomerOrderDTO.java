package persistence.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record CustomerOrderDTO(
        CustomerDTO customer,
        List<OrderTotalAmountDTO> orders
) {
}
