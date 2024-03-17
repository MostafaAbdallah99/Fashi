package persistence.repository.interfaces;


import persistence.entities.Customer;

public interface UserRepository {
    public Customer findUserByEmail(String email);

    public boolean addCustomer(Customer customer);

    boolean isEmailExists(String email);

    boolean isUsernameExists(String userName);

    public boolean updateCustomer(Customer customer);

    public boolean comparePassword(String email, String password);

    public boolean changePassword(String email, String oldPassword, String newPassword);


    Customer findUserById(Integer id);

    Customer findUserByResetPasswordToken(String token);
}
