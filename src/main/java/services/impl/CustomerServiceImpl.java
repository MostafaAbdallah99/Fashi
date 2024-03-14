package services.impl;

import mappers.CustomerMapper;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.repositories.UserRepositoryImpl;
import services.interfaces.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public CustomerDTO login(String email, String password) {
        Customer customer = userRepository.findUserByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return  CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        }
        return null;
    }




    public CustomerDTO signUp(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        userRepository.addCustomer(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
    }



}