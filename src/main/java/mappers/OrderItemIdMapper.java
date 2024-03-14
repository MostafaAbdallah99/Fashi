package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.OrderItemIdDTO;
import persistence.entities.OrderItemId;

@Mapper
public interface OrderItemIdMapper {
    OrderItemIdMapper INSTANCE = Mappers.getMapper(OrderItemIdMapper.class);

    OrderItemId orderItemIdDTOToOrderItemId(OrderItemIdDTO orderItemId);

    OrderItemIdDTO orderItemIdToOrderItemIdDTO(OrderItemId orderItemId);
}