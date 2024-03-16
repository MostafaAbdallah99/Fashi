package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.CategoryDTO;
import persistence.entities.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

}
