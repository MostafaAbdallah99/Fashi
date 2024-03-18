package persistence.repository.interfaces;

import jakarta.persistence.EntityManager;
import persistence.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category getCategoryByName(String categoryName, EntityManager entityManager);
    List<Category> getCategoriesByTagName(String tagName, EntityManager entityManager);
}
