package persistence.repository.interfaces;

import jakarta.persistence.EntityManager;
import persistence.entities.Tag;

import java.util.List;

public interface TagRepository {
    Tag getTagByName(String tagName, EntityManager entityManager);
    List<Tag> getTagsByCategoryName(String categoryName, EntityManager entityManager);
}
