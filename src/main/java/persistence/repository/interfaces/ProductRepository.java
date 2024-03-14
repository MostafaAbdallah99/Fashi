package persistence.repository.interfaces;


import persistence.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List<Product> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max);
}
