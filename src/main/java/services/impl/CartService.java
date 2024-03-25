package services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mappers.*;
import persistence.dto.*;
import persistence.entities.Cart;
import persistence.entities.CartItem;
import persistence.entities.Customer;
import persistence.entities.Product;
import persistence.repository.repositories.CartRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    public boolean removeProductFromCart(int cartID, int productID) {
        return TransactionUtil.doInTransaction(entityManager -> {
        Cart cart =cartRepository.findById( cartID, entityManager);
        Product product = productRepository.findById((long) productID, entityManager);
        cart.removeProduct(product);
        return cartRepository.update(cart, entityManager);}
        );
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
        if(!checkProductQuantity(productId, quantity)){
            return null;
        }
        else {
            ProductService productService = new ProductService();
            ProductDTO productDTO = productService.getProductById((long) productId);

            BigDecimal totalPrice = productDTO.productPrice().multiply(new BigDecimal(quantity));

            CartItemIdDTO cartItemIdDTO = new CartItemIdDTO(null, productId);
            CartItemDTO cartItemDTO = new CartItemDTO(cartItemIdDTO, productDTO, quantity, totalPrice);

            return cartItemDTO;
        }
    }


    public CartDTO createCartForCustomer(CustomerDTO customerDTO) {
        Cart cart = new Cart();
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        cart.setCustomer(customer);
        TransactionUtil.doInTransaction(entityManager -> cartRepository.save(cart, entityManager));
        return CartMapper.INSTANCE.cartToCartDTO(cart);

    }

    public CartItemDTO createCartItemForCustomer(Customer customer, int productId, int quantity) {
        if(!checkProductQuantity(productId, quantity)){
            return null;
        }
        else {
            addProductToCart(customer.getId(), productId, quantity);
            ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(TransactionUtil.doInTransaction(entityManager -> productRepository.findById((long) productId, entityManager)));
            return new CartItemDTO(new CartItemIdDTO(customer.getId(), productId), productDTO, quantity, productDTO.productPrice().multiply(new BigDecimal(quantity)));
        }

    }

    public boolean addProductToCart(int cartId, int productId, int quantity) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Cart cart = cartRepository.findById(cartId, entityManager);
            Product product = productRepository.findById((long) productId, entityManager);
            cart.addProduct(product, quantity, product.getProductPrice().multiply(new BigDecimal(quantity)));
            return cartRepository.update(cart, entityManager);
        });
    }


    public List<CartItemDTO> getCartItems(String cartItemsJson) {
        System.out.println(cartItemsJson+"     cartItemsJson");
        Type listType = new TypeToken<ArrayList<CartItemDTO>>(){}.getType();
        List<CartItemDTO> cartItems = new Gson().fromJson(cartItemsJson, listType);
        return cartItems;
    }
}
