package services.interfaces;

import persistence.dto.CustomerDTO;
import persistence.entities.Customer;

public interface CustomerService {
     CustomerDTO login(String email, String password);

     CustomerDTO signUp(CustomerDTO customerDTO);

     boolean isEmailExists(String email);

    boolean isUsernameExists(String userName);

     CustomerDTO getCustomerById(Integer id);

    boolean updateCustomer(CustomerDTO updatedCustomer);

     boolean comparePassword(String email, String password);

     boolean changePassword(String email, String oldPassword, String newPassword);

    void storeResetPasswordToken(String email, String token);

    boolean resetPassword(String token, String newPassword);

    Customer getUserByResetPasswordToken(String token);
}
