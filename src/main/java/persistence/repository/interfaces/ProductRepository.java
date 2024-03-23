package persistence.repository.interfaces;


import jakarta.persistence.EntityManager;

import java.util.Map;

public interface ProductRepository {
    public Map<String, Object> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max, int page, int size, EntityManager entityManager);
}
