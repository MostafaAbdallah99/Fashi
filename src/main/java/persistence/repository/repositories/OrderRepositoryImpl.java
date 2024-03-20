package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import persistence.entities.Order;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl extends GenericRepositoryImpl<Order, Integer> implements OrderRepository {
    public OrderRepositoryImpl() {
        super(Order.class);
    }

    public List<Order> getAllOrdersByCustomerId(Integer customerId, EntityManager entityManager) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }


}
