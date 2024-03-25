package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistence.entities.Customer;
import persistence.entities.Order;
import persistence.repository.interfaces.AdminRepo;
import persistence.repository.utils.TransactionUtil;

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

    public List<Customer> getUsersWithOrders() {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery(
                    "SELECT DISTINCT c FROM Customer c JOIN FETCH c.orders", Customer.class);
            return query.getResultList();
        });
    }

    public List<Order> getOrdersByUsername(String username) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Order> query = entityManager.createQuery(
                    "SELECT o FROM Order o WHERE o.customer.customerName = :username", Order.class);
            query.setParameter("username", username);
            return query.getResultList();
        });
    }



}
