package persistence.repository.interfaces;

import persistence.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category getCategoryByName(String categoryName);
    List<Category> getCategoriesByTagName(String tagName);
}
