package services.interfaces;

import persistence.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO login(String email, String password);
    public CustomerDTO signUp (CustomerDTO customerDTO) ;

}
