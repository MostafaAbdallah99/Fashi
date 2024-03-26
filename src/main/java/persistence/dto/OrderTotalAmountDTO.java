package persistence.dto;

import java.math.BigDecimal;
import java.util.Date;

public record OrderTotalAmountDTO (
        Integer orderId,
        Date orderedAt,
        BigDecimal totalOrderAmount

){}