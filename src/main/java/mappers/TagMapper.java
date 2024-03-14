package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import persistence.dto.TagDTO;
import persistence.entities.Tag;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTO tagToTagDTO(Tag tag);

    @Mapping(target = "categories", ignore = true)
    Tag tagDTOToTag(TagDTO tagDTO);
}