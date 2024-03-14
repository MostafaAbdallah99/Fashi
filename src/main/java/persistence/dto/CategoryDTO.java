package persistence.dto;


import java.util.Set;

public record CategoryDTO(
        Integer id,
        String categoryName,
        Set<TagDTO> tags
) {
}