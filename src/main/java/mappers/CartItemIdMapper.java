package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.CartItemIdDTO;
import persistence.entities.CartItemId;

@Mapper
public interface CartItemIdMapper {
    CartItemIdMapper INSTANCE = Mappers.getMapper(CartItemIdMapper.class);
    CartItemIdDTO cartItemToCartItemIdDTO(CartItemId cartItem);
    CartItemId cartItemIdDTOToCartItem(CartItemIdDTO cartItemIdDTO);
}