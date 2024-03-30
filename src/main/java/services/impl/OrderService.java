package services.impl;

import exceptions.InsufficientCreditException;
import exceptions.OutOfStockException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import persistence.dto.OrderTotalAmountDTO;
import persistence.entities.*;
import persistence.repository.repositories.CartRepositoryImpl;
import persistence.repository.repositories.OrderRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.TransactionUtil;

import java.math.BigDecimal;
import java.util.*;

public class OrderService {
    private final CartRepositoryImpl cartRepository;
    private final ProductRepositoryImpl productRepository;
    private final OrderRepositoryImpl orderRepository;

    public OrderService() {
        this.cartRepository = new CartRepositoryImpl();
        this.productRepository = new ProductRepositoryImpl();
        this.orderRepository = new OrderRepositoryImpl();
    }

    public Map<String, Object> checkout(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Cart cart = cartRepository.findById(cartId, entityManager);
            Set<CartItem> cartItems = cart.getCartItems();
            Customer customer = cart.getCustomer();
            entityManager.lock(customer, LockModeType.PESSIMISTIC_WRITE);

            BigDecimal totalCost = calculateTotalCartCost(cartId);
            if (customer.getCreditLimit().compareTo(totalCost)<0) {
                throw new InsufficientCreditException(customer.getCreditLimit());
            }

            List<Long> outOfStockProducts = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                Product product = productRepository.findById(Long.valueOf(cartItem.getProduct().getId()), entityManager);
                entityManager.lock(product, LockModeType.PESSIMISTIC_WRITE);
                if (product.getStockQuantity() < cartItem.getQuantity()) {
                    outOfStockProducts.add(Long.valueOf(product.getId()));
                }
            }

            if (!outOfStockProducts.isEmpty()) {
                throw new OutOfStockException(outOfStockProducts);
            }

            Order order = orderRepository.createOrder(cart, entityManager);

            updateProductStockAndCreateOrderItems(cartItems, order, entityManager);

            updateCustomerCreditAndClearCart(customer, totalCost, cart, entityManager);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return response;
        });
    }



    private void updateProductStockAndCreateOrderItems(Set<CartItem> cartItems, Order order, EntityManager entityManager) {
        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(Long.valueOf(cartItem.getProduct().getId()), entityManager);
            entityManager.lock(product, LockModeType.PESSIMISTIC_WRITE);
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new OutOfStockException(Collections.singletonList(Long.valueOf(product.getId())));
            }
            OrderItem orderItem = new OrderItem(order, product, cartItem.getQuantity(), cartItem.getAmount());
            order.getOrderItems().add(orderItem);
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.update(product, entityManager);
        }
    }

    private void updateCustomerCreditAndClearCart(Customer customer, BigDecimal totalCost, Cart cart, EntityManager entityManager) {
        customer.setCreditLimit(customer.getCreditLimit().subtract(totalCost));
        entityManager.merge(customer);

        for (CartItem item : cart.getCartItems()) {
            entityManager.remove(item);
        }
        cart.getCartItems().clear();
        cartRepository.update(cart, entityManager);
    }



    public BigDecimal calculateTotalCartCost(int cartId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Cart cart = cartRepository.findById(cartId, entityManager);
            Set<CartItem> cartItems = cart.getCartItems();

            BigDecimal totalCost = BigDecimal.valueOf(0.0);
            for (CartItem cartItem : cartItems) {
                Product product = productRepository.findById(Long.valueOf(cartItem.getProduct().getId()), entityManager);
                totalCost=totalCost.add(product.getProductPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));

            }
            System.out.println(totalCost);
            return totalCost;
        });
    }

    public List<OrderTotalAmountDTO> findTotalOrderAmount(Integer orderId) {
        return TransactionUtil.doInTransaction(entityManager -> orderRepository.findTotalOrderAmount(orderId, entityManager));
    }
}

