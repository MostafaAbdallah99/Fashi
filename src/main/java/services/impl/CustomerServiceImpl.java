package services.impl;

import mappers.CustomerMapper;
import org.mindrot.jbcrypt.BCrypt;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.repositories.UserRepositoryImpl;
import services.interfaces.CustomerService;

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
}