package services.impl;

import persistence.entities.Customer;
import persistence.entities.Order;
import persistence.repository.interfaces.AdminRepo;
import persistence.repository.repositories.AdminRepoImpl;

import java.util.List;

public class AdminServiceImpl {


    private final AdminRepo adminRepo;

    public AdminServiceImpl() {
        this.adminRepo = new AdminRepoImpl();
    }

    public List<Customer> getUsersWithOrders() {
        return adminRepo.getUsersWithOrders();
    }

    public List<Order> getOrderHistoryByUsername(String username) {
        return adminRepo.getOrderHistoryByUsername(username);
    }
}

