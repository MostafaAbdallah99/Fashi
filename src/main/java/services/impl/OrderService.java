package services.impl;

import jakarta.persistence.LockModeType;
import persistence.dto.OrderTotalAmountDTO;
import persistence.entities.*;
import persistence.repository.repositories.CartRepositoryImpl;
import persistence.repository.repositories.OrderRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

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

            Order order = new Order();
            order.setOrderedAt(new Date());
            order.setCustomer(cart.getCustomer());

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

            for (CartItem cartItem : cartItems) {
                Product product = productRepository.findById(Long.valueOf(cartItem.getProduct().getId()), entityManager);
                OrderItem orderItem = new OrderItem(order, product, cartItem.getQuantity(), cartItem.getAmount());
                order.getOrderItems().add(orderItem);
                product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
                productRepository.update(product, entityManager);
            }

            customer.setCreditLimit(customer.getCreditLimit().subtract(totalCost));
            entityManager.merge(customer);
            entityManager.persist(order);
            // Clear the cart

            // Clear the cart
            List<CartItem> itemsToRemove = new ArrayList<>();
            for (CartItem item : cartItems) {
                itemsToRemove.add(item);
            }
            for (CartItem item : itemsToRemove) {
                cart.removeProduct(item.getProduct());
                entityManager.remove(item);
            }
            cartRepository.update(cart, entityManager);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            return response;
        });

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

