package persistence.repository.repositories;

import jakarta.persistence.TypedQuery;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.utils.TransactionUtil;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Customer findUserByEmail(String email) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.email = :email", Customer.class);
            query.setParameter("email", email);
            List<Customer> customers = query.getResultList();
            return customers.isEmpty() ? null : customers.get(0);
        });
    }

    @Override
    public Customer addCustomer(Customer customer) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> {
                entityManager.persist(customer);
            });
            System.out.println("Customer ID in add customer function: " + customer.getId());
            return customer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}