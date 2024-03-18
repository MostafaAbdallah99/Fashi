package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import persistence.entities.Category;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.CategoryRepository;

import java.util.List;

public class CategoryRepositoryImpl extends GenericRepositoryImpl<Category, Integer> implements CategoryRepository {
    public CategoryRepositoryImpl(Class<Category> entityClass) {
        super(entityClass);
    }

    @Override
    public Category getCategoryByName(String categoryName, EntityManager entityManager) {
        return entityManager.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName", Category.class)
                            .setParameter("categoryName", categoryName)
                            .getSingleResult();
    }

    @Override
    public List<Category> getCategoriesByTagName(String tagName, EntityManager entityManager) {
        return entityManager.createQuery("SELECT c FROM Category c JOIN c.tags t WHERE t.tagName = :tagName", Category.class)
                        .setParameter("tagName", tagName)
                        .getResultList();
    }


}
