package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.CartDTO;
import persistence.entities.Cart;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    CartDTO cartToCartDTO(Cart cart);
    Cart cartDTOToCart(CartDTO cartDTO);
}