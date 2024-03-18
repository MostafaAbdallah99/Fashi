package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import persistence.entities.Product;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl extends GenericRepositoryImpl<Product, Long> implements ProductRepository {
    public ProductRepositoryImpl(Class<Product> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Product> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max, EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (categoryName != null && !categoryName.isEmpty()) {
                predicates.add(cb.equal(product.get("category").get("categoryName"), categoryName));
        }
        if (tagName != null && !tagName.isEmpty()) {
            predicates.add(cb.equal(product.get("tag").get("tagName"), tagName));
        }
        if (min >= 0 && max >= 0 && min <= max) {
            predicates.add(cb.between(product.get("productPrice"), min, max));
        }
        query.select(product).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
