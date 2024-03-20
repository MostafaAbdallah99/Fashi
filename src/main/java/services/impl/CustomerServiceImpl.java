package services.impl;

import mappers.CustomerMapper;
import org.mindrot.jbcrypt.BCrypt;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.repositories.UserRepositoryImpl;
import services.interfaces.CustomerService;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public CustomerDTO login(String email, String password) {
        Customer customer = userRepository.findUserByEmail(email);
        if (customer != null) {
            if (BCrypt.checkpw(password, customer.getPassword())) {
                return CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
            }
        }
        return null;
    }



    public CustomerDTO signUp(CustomerDTO customerDTO) {
        String hashedPassword = BCrypt.hashpw(customerDTO.password(), BCrypt.gensalt());

        CustomerDTO hashedCustomerDTO = new CustomerDTO(
                customerDTO.id(),
                customerDTO.customerName(),
                customerDTO.birthday(),
                hashedPassword, // use the hashed password here
                customerDTO.job(),
                customerDTO.email(),
                customerDTO.creditLimit(),
                customerDTO.city(),
                customerDTO.country(),
                customerDTO.streetNo(),
                customerDTO.streetName(),
                customerDTO.interests(),
                customerDTO.cart()
        );

        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(hashedCustomerDTO);
        userRepository.addCustomer(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
    }

    public boolean isEmailExists(String email) {
        return userRepository.isEmailExists(email);
    }

    public boolean isUsernameExists(String userName) {
        return userRepository.isUsernameExists(userName);
    }

    public CustomerDTO findUserByEmail(String email) {
        Customer customer = userRepository.findUserByEmail(email);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
    }
    public boolean updateCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        return userRepository.updateCustomer(customer);
    }


    public boolean comparePassword(String email, String password) {
        Customer customer = userRepository.findUserByEmail(email);
        if (customer != null) {
            return BCrypt.checkpw(password, customer.getPassword());
        }
        return false;
    }

    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = userRepository.findUserById(id);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
       return userRepository.changePassword(email, oldPassword, newPassword);
    }

    @Override
    public void storeResetPasswordToken(String email, String token) {
        Customer customer = userRepository.findUserByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            userRepository.updateCustomer(customer);
        }
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        Customer customer = userRepository.findUserByResetPasswordToken(token);
        if (customer != null) {
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            customer.setPassword(hashedPassword);
            customer.setResetPasswordToken(null);
            userRepository.updateCustomer(customer);
            return true;
        }
        return false;
    }

    @Override
    public Customer getUserByResetPasswordToken(String token) {
        return userRepository.findUserByResetPasswordToken(token);
    }

    public static void main(String[] args) {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.storeResetPasswordToken(
               "AhmedHassan@yahoo.com", "token1234");

    }
}