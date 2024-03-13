package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.TagDTO;
import persistence.entities.Tag;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

//    @Mapping(target = "products", ignore = true)
    TagDTO tagToTagDTO(Tag tag);

    Tag tagDTOToTag(TagDTO tagDTO);
}