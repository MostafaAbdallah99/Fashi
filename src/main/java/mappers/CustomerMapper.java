package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );


    CustomerDTO customerToCustomerDTO(Customer customer);


    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}