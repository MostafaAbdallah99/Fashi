package persistence.repository.interfaces;

import jakarta.persistence.EntityManager;
import persistence.entities.Customer;
import persistence.entities.Order;

import java.util.List;

public interface AdminRepo {
    default boolean makeUserAdmin(int userId, EntityManager entityManager){
        return false;
    }
    List<Order> getOrderHistoryByUsername(String username);

    List<Customer> getUsersWithOrders();

    public List<Order> getOrdersByUsername(String username);
}
