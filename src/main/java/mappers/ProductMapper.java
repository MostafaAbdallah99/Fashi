package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.CustomerDTO;
import persistence.dto.ProductDTO;
import persistence.entities.Customer;
import persistence.entities.Product;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );


    @Mapping(source = "categoryName", target = "category.categoryName")

    ProductDTO productToProductDTO(Product product);


    Product productDTOToProduct(ProductDTO productDTO);
}
