package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
                entityManager.persist(customer);
            });
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean isEmailExists(String email) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.email = :email", Customer.class);
            query.setParameter("email", email);
            return !query.getResultList().isEmpty();
        });
    }

    @Override
    public boolean isUsernameExists(String userName) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.customerName = :userName", Customer.class);
            query.setParameter("userName", userName);
            return !query.getResultList().isEmpty();
        });
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        return TransactionUtil.doInTransaction(entityManager -> {
                    Customer existingCustomer = entityManager.find(Customer.class, customer.getId());
                    if (existingCustomer == null) {
                        throw new IllegalArgumentException("Customer with id " + customer.getId() + " does not exist.");
                    }
                    if (customer.getCustomerName() != null) {
                        existingCustomer.setCustomerName(customer.getCustomerName());
                    }
                    if (customer.getBirthday() != null) {
                        existingCustomer.setBirthday(customer.getBirthday());
                    }
                    if (customer.getPassword() != null) {
                        existingCustomer.setPassword(customer.getPassword());
                    }
                    if (customer.getJob() != null) {
                        existingCustomer.setJob(customer.getJob());
                    }
                    if (customer.getEmail() != null) {
                        existingCustomer.setEmail(customer.getEmail());
                    }
                    if (customer.getCreditLimit() != null) {
                        existingCustomer.setCreditLimit(customer.getCreditLimit());
                    }
                    if (customer.getCity() != null) {
                        existingCustomer.setCity(customer.getCity());
                    }
                    if (customer.getCountry() != null) {
                        existingCustomer.setCountry(customer.getCountry());
                    }
                    if (customer.getStreetNo() != null) {
                        existingCustomer.setStreetNo(customer.getStreetNo());
                    }
                    if (customer.getStreetName() != null) {
                        existingCustomer.setStreetName(customer.getStreetName());
                    }
                    if (customer.getInterests() != null) {
                        existingCustomer.setInterests(customer.getInterests());
                    }
                    entityManager.merge(existingCustomer);
                    return true;
                });
    }

}