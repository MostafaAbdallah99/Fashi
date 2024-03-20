package services;

import mappers.*;
import persistence.dto.*;
import persistence.entities.Cart;
import persistence.entities.CartItem;
import persistence.entities.Customer;
import persistence.entities.Product;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.repositories.CartRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.repositories.UserRepositoryImpl;
import persistence.repository.utils.TransactionUtil;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartService {

    private final CartRepositoryImpl cartRepository;
    private final ProductRepositoryImpl productRepository;

    public CartService() {
        this.cartRepository = new CartRepositoryImpl();
        this.productRepository = new ProductRepositoryImpl();
    }


    private int checkProductQuantityInCart(Cart cart, Product product, int quantity) {
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                return cartItem.getQuantity() + quantity;

            }

        }
        return quantity;
    }

    public boolean removeProductFromCart(CartDTO cartDTO, ProductDTO productDTO) {
        Cart cart = CartMapper.INSTANCE.cartDTOToCart(cartDTO);
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        cart.removeProduct(product);
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.update(cart, entityManager));
    }

    public List<CartItemDTO> getCartItems(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.getCartItems(cartId, entityManager))
                .stream()
                .map(CartItemMapper.INSTANCE::cartItemToCartItemDTO)
                .collect(Collectors.toList());
    }

    public boolean cartReset(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> cartRepository.cartReset(cartId, entityManager));
    }

    public boolean checkProductQuantity(int productId, int quantity) {
        Product product = TransactionUtil.doInTransaction(entityManager -> productRepository.findById((long) productId, entityManager));
        return product.getStockQuantity() >= quantity;

    }

    public CartItemDTO createCartItemForGuest(int productId, int quantity) {
        ProductService productService = new ProductService();
        ProductDTO productDTO = productService.getProductById((long) productId);

        BigDecimal totalPrice = productDTO.productPrice().multiply(new BigDecimal(quantity));

        CartItemIdDTO cartItemIdDTO = new CartItemIdDTO(null, productId);
        CartItemDTO cartItemDTO = new CartItemDTO(cartItemIdDTO, productDTO, quantity, totalPrice);

        return cartItemDTO;
    }


    public CartDTO createCartForCustomer(CustomerDTO customerDTO) {
        Cart cart = new Cart();
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        cart.setCustomer(customer);
        TransactionUtil.doInTransaction(entityManager -> cartRepository.save(cart, entityManager));
        return CartMapper.INSTANCE.cartToCartDTO(cart);

    }

    public CartItemDTO createCartItemForCustomer(Customer customer, int productId, int quantity) {
        ProductService productService = new ProductService();
        ProductDTO productDTO = productService.getProductById((long) productId);
        BigDecimal totalPrice = productDTO.productPrice().multiply(new BigDecimal(quantity));
//
//        Cart cart = cartRepository.findById(customer.getId());
//        System.out.println("**************************************************************************");
//        System.out.println("this is the customer in the cart");
//        cart.setCustomer(customer);
//        System.out.println("Cart: " + cart.getCustomer().getId());
//        addOrUpdateProductToCart(cart, ProductMapper.INSTANCE.productDTOToProduct(productDTO), quantity);
        cartRepository.addToCart(customer.getId(), productId, quantity);
        return new CartItemDTO(new CartItemIdDTO(customer.getId(), productId), productDTO, quantity, totalPrice);

    }
}
