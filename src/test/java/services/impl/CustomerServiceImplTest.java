package services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mindrot.jbcrypt.BCrypt;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import persistence.repository.interfaces.UserRepository;
import services.impl.CustomerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {
    private UserRepository userRepository;
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        customerService = new CustomerServiceImpl(userRepository);
    }

    @Test
    public void testLogin() {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(hashedPassword);

        when(userRepository.findUserByEmail(email)).thenReturn(customer);

        CustomerDTO result = customerService.login(email, password);

        assertEquals(email, result.email());
    }
}