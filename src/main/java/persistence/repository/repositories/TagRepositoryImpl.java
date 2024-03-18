package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import persistence.entities.Tag;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.TagRepository;

import java.util.List;

public class TagRepositoryImpl extends GenericRepositoryImpl<Tag, Integer> implements TagRepository {
    public TagRepositoryImpl(Class<Tag> entityClass) {
        super(entityClass);
    }

    @Override
    public Tag getTagByName(String tagName, EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tag t WHERE t.tagName = :tagName", Tag.class)
                            .setParameter("tagName", tagName)
                            .getSingleResult();
    }

    @Override
    public List<Tag> getTagsByCategoryName(String categoryName, EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tag t JOIN t.categories c WHERE c.categoryName = :categoryName", Tag.class)
                        .setParameter("categoryName", categoryName)
                        .getResultList();
    }
}
