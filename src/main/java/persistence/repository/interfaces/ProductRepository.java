package persistence.repository.interfaces;


import jakarta.persistence.EntityManager;
import persistence.entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max, EntityManager entityManager);
}
