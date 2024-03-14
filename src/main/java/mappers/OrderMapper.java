package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.OrderDTO;
import persistence.entities.Order;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);
}