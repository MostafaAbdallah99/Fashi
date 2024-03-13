package persistence.repository.interfaces;

import persistence.entities.Tag;

import java.util.List;

public interface TagRepository {
    Tag getTagByName(String tagName);
    List<Tag> getTagsByCategoryName(String categoryName);
}
