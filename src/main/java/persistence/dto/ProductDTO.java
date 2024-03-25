package persistence.dto;

import persistence.entities.ProductSize;

import java.math.BigDecimal;

public record ProductDTO(
        Integer id,
        String productName,
        String productImage,
        Integer stockQuantity,
        String productDescription,
        BigDecimal productPrice,
        ProductSize productSize,
        CategoryDTO category,
        TagDTO tag,
        String categoryName,
        Boolean isDeleted
) {
}