package services;

import mappers.CartItemMapper;
import mappers.CartMapper;
import mappers.ProductMapper;
import persistence.dto.CartDTO;
import persistence.dto.CartItemDTO;
import persistence.dto.CartItemIdDTO;
import persistence.dto.ProductDTO;
import persistence.entities.Cart;
import persistence.entities.Product;
import persistence.repository.repositories.CartRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartService {

    private final CartRepositoryImpl cartRepository;
    private final ProductRepositoryImpl productRepository;

    public CartService() {
        this.cartRepository = new CartRepositoryImpl(Cart.class);
        this.productRepository = new ProductRepositoryImpl(Product.class);
    }
    public boolean addOrUpdateProductToCart(CartDTO cartDTO, ProductDTO productDTO, int quantity) {
        Cart cart = CartMapper.INSTANCE.cartDTOToCart(cartDTO);
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        cart.addProduct(product, quantity, product.getProductPrice());
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.update(cart, entityManager));
    }

    public boolean removeProductFromCart(CartDTO cartDTO, ProductDTO productDTO) {
        Cart cart = CartMapper.INSTANCE.cartDTOToCart(cartDTO);
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        cart.removeProduct(product);
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.update(cart, entityManager));
    }

    public List<CartItemDTO> getCartItems(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.getCartItems(cartId, entityManager)
                .stream()
                .map(CartItemMapper.INSTANCE::cartItemToCartItemDTO)
                .collect(Collectors.toList()));
    }

    public boolean cartReset(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.cartReset(cartId, entityManager));
    }

    public boolean checkProductQuantity(Long productId, int quantity) {
        Product product = TransactionUtil.doInTransaction(entityManager -> productRepository.findById(productId, entityManager));
        return product.getStockQuantity() >= quantity;

    }

    public CartItemDTO createCartItemForGuest(Long productId, int quantity) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Product product = productRepository.findById(productId, entityManager);
            BigDecimal totalPrice = product.getProductPrice().multiply(new BigDecimal(quantity));
            CartItemIdDTO cartItemIdDTO = new CartItemIdDTO(null, Math.toIntExact(productId));
            ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);
            return new CartItemDTO(cartItemIdDTO, null, productDTO, quantity, totalPrice);

        });
    }

}
