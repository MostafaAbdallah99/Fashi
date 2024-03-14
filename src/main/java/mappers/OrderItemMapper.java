package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.OrderItemDTO;
import persistence.entities.OrderItem;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);
}
