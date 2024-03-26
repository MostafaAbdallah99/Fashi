package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistence.dto.OrderTotalAmountDTO;
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

    @Override
    public List<OrderTotalAmountDTO> findTotalOrderAmount(Integer customerId, EntityManager entityManager) {
        String jpql = "SELECT new persistence.dto.OrderTotalAmountDTO(oi.order.id, o.orderedAt, SUM(oi.quantity * oi.amount)) " +
                "FROM OrderItem oi " +
                "INNER JOIN oi.order o " +
                "WHERE o.customer.id = :customerId " +
                "GROUP BY oi.order.id";

        TypedQuery<OrderTotalAmountDTO> query = entityManager.createQuery(jpql, OrderTotalAmountDTO.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }


}
