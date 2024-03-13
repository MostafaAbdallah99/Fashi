package persistence.dto;


import java.util.Set;

public record TagDTO(
        Integer id,
        String tagName,
        Set<CategoryDTO> categories
//        Set<ProductDTO> products
) {}