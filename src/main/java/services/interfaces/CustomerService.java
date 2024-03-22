package services.interfaces;

import persistence.dto.CartItemDTO;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;

import java.util.List;

public interface CustomerService {
     CustomerDTO login(String email, String password);

     CustomerDTO signUp(CustomerDTO customerDTO, List<CartItemDTO> cartItems);

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
