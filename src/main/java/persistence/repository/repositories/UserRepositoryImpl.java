package persistence.repository.repositories;

import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.utils.TransactionUtil;
import persistence.repository.utils.TransactionUtil;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public Customer findUserByEmail(String email) {
        return TransactionUtil.doInTransaction(entityManager ->
                (Customer) entityManager.createQuery("SELECT u FROM Customer u WHERE u.email = :email", Customer.class)
                        .setParameter("email", email)
                        .getSingleResult()
        );
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> {
                entityManager.persist(customer);
            });
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}