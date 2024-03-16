package persistence.repository.interfaces;


import persistence.entities.Customer;

public interface UserRepository {
    public Customer findUserByEmail(String email);
    public boolean addCustomer(Customer customer);

    boolean isEmailExists(String email);

    boolean isUsernameExists(String userName);

    public boolean updateCustomer(Customer customer);


}
