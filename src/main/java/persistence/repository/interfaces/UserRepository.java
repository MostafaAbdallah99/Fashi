package persistence.repository.interfaces;


import persistence.entities.Customer;

public interface UserRepository {
    public Customer findUserByEmail(String email);
    public Customer addCustomer(Customer customer);

}
