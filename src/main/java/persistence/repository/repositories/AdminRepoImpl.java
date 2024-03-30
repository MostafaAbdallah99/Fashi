package persistence.repository.repositories;

import jakarta.persistence.TypedQuery;
import persistence.entities.Customer;
import persistence.entities.Order;
import persistence.repository.interfaces.AdminRepo;
import persistence.repository.TransactionUtil;

import java.util.List;

public class AdminRepoImpl implements AdminRepo {
    @Override
    public List<Order> getOrderHistoryByUsername(String username) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Order> query = entityManager.createQuery(
                    "SELECT o FROM Order o WHERE o.customer.customerName = :username", Order.class);
            query.setParameter("username", username);
            return query.getResultList();
        });
    }

    @Override
    public List<Customer> getUsersWithOrders() {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery(
                    "SELECT c FROM Customer c WHERE c.isAdmin = false", Customer.class);
            return query.getResultList();
        });
    }

    public Customer getCustomerByUsername(String username) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery(
                    "SELECT c FROM Customer c WHERE c.customerName = :username", Customer.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        });
    }







    public static void main(String[] args) {
        AdminRepoImpl adminRepo = new AdminRepoImpl();
        Customer orders = adminRepo.getCustomerByUsername("maher");


    }


}
