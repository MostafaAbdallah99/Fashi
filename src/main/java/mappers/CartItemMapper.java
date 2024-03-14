package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.CartItemDTO;
import persistence.entities.CartItem;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
}