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
    public boolean addCustomer(Customer customer) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> {
                entityManager.merge(customer);
            });
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}