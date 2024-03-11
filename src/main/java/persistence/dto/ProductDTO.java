package persistence.dto;

import java.math.BigDecimal;

public record ProductDTO(
        Integer id,
        String productName,
        String productImage,
        Integer stockQuantity,
        String productDescription,
        BigDecimal productPrice,
        CategoryDTO category,
        TagDTO tag
) {}