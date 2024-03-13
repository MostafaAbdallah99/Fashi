package persistence.repository.interfaces;


import persistence.entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsByCategory(String categoryName);

    List<Product> getProductsByTag(String tagName);

    List<Product> getProductsByCategoryAndTag(String categoryName, String tagName);

    List<Product> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max);
}
