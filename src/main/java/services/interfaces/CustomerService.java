package services.interfaces;

import persistence.dto.CustomerDTO;

public interface CustomerService {
     CustomerDTO login(String email, String password);

     CustomerDTO signUp(CustomerDTO customerDTO);

     boolean isEmailExists(String email);

    boolean isUsernameExists(String userName);

     CustomerDTO getCustomerById(Integer id);

    boolean updateCustomer(CustomerDTO updatedCustomer);

     boolean comparePassword(String email, String password);

     boolean changePassword(String email, String oldPassword, String newPassword);
}
