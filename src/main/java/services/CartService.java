package services;

import mappers.CartItemMapper;
import mappers.CartMapper;
import mappers.ProductMapper;
import persistence.dto.CartDTO;
import persistence.dto.CartItemDTO;
import persistence.dto.ProductDTO;
import persistence.entities.Cart;
import persistence.entities.Product;
import persistence.repository.repositories.CartRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CartService {

    private final CartRepositoryImpl cartRepository;

    public CartService() {
        this.cartRepository = new CartRepositoryImpl(Cart.class);
    }
    public boolean addOrUpdateProductToCart(CartDTO cartDTO, ProductDTO productDTO, int quantity) {
        Cart cart = CartMapper.INSTANCE.cartDTOToCart(cartDTO);
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        cart.addProduct(product, quantity, product.getProductPrice());
        return cartRepository.update(cart);
    }

    public boolean removeProductFromCart(CartDTO cartDTO, ProductDTO productDTO) {
        Cart cart = CartMapper.INSTANCE.cartDTOToCart(cartDTO);
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        cart.removeProduct(product);
        return cartRepository.update(cart);
    }

    public List<CartItemDTO> getCartItems(int cartId) {
        return cartRepository.getCartItems(cartId)
                .stream()
                .map(CartItemMapper.INSTANCE::cartItemToCartItemDTO)
                .collect(Collectors.toList());
    }

    public boolean cartReset(int cartId) {
        return cartRepository.cartReset(cartId);
    }
}
