package services.interfaces;

import persistence.entities.Customer;
import persistence.entities.Order;

import java.util.List;

public interface AdminService {
    List<Customer> getUsersWithOrders();

    List<Order> getOrderHistoryByUsername(String username);
}
