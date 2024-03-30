package persistence.repository.repositories;

import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.TransactionUtil;

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
            TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.persist(customer));
            System.out.println("Customer ID in add customer function: " + customer.getId());
            return customer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
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
            if (customer.getEmail() != null) {
                existingCustomer.setEmail(customer.getEmail());
            }
            if (customer.getBirthday() != null) {
                existingCustomer.setBirthday(customer.getBirthday());
            }
            if (customer.getJob() != null) {
                existingCustomer.setJob(customer.getJob());
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
            if (customer.getCreditLimit() != null) {
                existingCustomer.setCreditLimit(customer.getCreditLimit());
            }
            if (customer.getResetPasswordToken() != null) {
                existingCustomer.setResetPasswordToken(customer.getResetPasswordToken());
            }
            if (customer.getPassword() != null) {
                String hashed =  BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
                existingCustomer.setPassword(hashed);
            }
            entityManager.persist(existingCustomer);
            return true;
        });
    }


    @Override
    public boolean comparePassword(String email, String password) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.email = :email", Customer.class);
            query.setParameter("email", email);
            List<Customer> customers = query.getResultList();
            if (customers.isEmpty()) {
                return false;
            }
            Customer customer = customers.get(0);
            return BCrypt.checkpw(password, customer.getPassword());
        });
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return TransactionUtil.doInTransaction(entityManager -> {
            if (comparePassword(email, oldPassword)) {
                Customer customer = findUserByEmail(email);
                String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                customer.setPassword(hashedNewPassword);
                entityManager.merge(customer);
                System.out.println("Password changed successfully for user: " + email); // Add this line
                return true;
            } else {
                System.out.println("Failed to change password for user: " + email); // Add this line
                return false;
            }
        });
    }

    @Override
    public Customer findUserById(Integer id) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.find(Customer.class, id));
    }



    public Customer findUserByResetPasswordToken(String token) {
        return TransactionUtil.doInTransaction(entityManager -> {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT u FROM Customer u WHERE u.resetPasswordToken = :token", Customer.class);
            query.setParameter("token", token);
            List<Customer> customers = query.getResultList();
            return customers.isEmpty() ? null : customers.get(0);
        });
    }
}