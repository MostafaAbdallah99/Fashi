package persistence.repository.interfaces;

import jakarta.persistence.EntityManager;
import persistence.dto.OrderTotalAmountDTO;
import persistence.entities.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrdersByCustomerId(Integer id, EntityManager entityManager);
    List<OrderTotalAmountDTO> findTotalOrderAmount(Integer customerId, EntityManager entityManager);
}
