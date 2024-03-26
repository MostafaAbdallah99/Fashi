package persistence.repository.repositories;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import persistence.entities.Order;
import persistence.repository.repositories.OrderRepositoryImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderRepositoryImplTest {
    private OrderRepositoryImpl orderRepository;
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        entityManager = Mockito.mock(EntityManager.class);
        orderRepository = new OrderRepositoryImpl();
    }

    @Test
    public void testGetAllOrdersByCustomerId() {
        Integer customerId = 1;
        Order order = new Order();
        List<Order> expectedOrders = Collections.singletonList(order);

        TypedQuery<Order> query = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)).thenReturn(query);
        when(query.setParameter("customerId", customerId)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedOrders);

        List<Order> actualOrders = orderRepository.getAllOrdersByCustomerId(customerId, entityManager);

        assertEquals(expectedOrders, actualOrders);
    }
}