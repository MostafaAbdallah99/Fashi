package persistence.repository.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import persistence.entities.Product;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductRepositoryImpl extends GenericRepositoryImpl<Product, Long> implements ProductRepository {
    public ProductRepositoryImpl() {
        super(Product.class);
    }

    public Map<String, Object> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max, int page, int size, String searchQuery, EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        List<Predicate> predicates = buildPredicates(cb, product, categoryName, tagName, min, max, searchQuery);
        query.select(product).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((page - 1) * size);
        typedQuery.setMaxResults(size);
        List<Product> products = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        List<Predicate> countPredicates = buildPredicates(cb, countRoot, categoryName, tagName, min, max, searchQuery);
        countQuery.select(cb.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        int totalPages = (int) Math.ceil((double) count / size);
        Map<String, Object> totalPagesCount = new HashMap<>();
        totalPagesCount.put("totalPages", totalPages);
        totalPagesCount.put("totalCount", count);
        Map<String, Object> result = new HashMap<>();
        result.put("products", products);
        result.put("totalPagesCount", totalPagesCount);
        return result;
    }

    public List<Product> getProducts(int page, int size, EntityManager entityManager) {
        String qlString = "SELECT p FROM Product p WHERE p.isDeleted = false";
        TypedQuery<Product> query = entityManager.createQuery(qlString, Product.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public long getTotalProducts(EntityManager entityManager) {
        String qlString = "SELECT COUNT(p) FROM Product p WHERE p.isDeleted = false";
        TypedQuery<Long> query = entityManager.createQuery(qlString, Long.class);
        return query.getSingleResult();
    }
    public Map<String, Object> getTotalPages(int size, EntityManager entityManager) {
        long totalProducts = getTotalProducts(entityManager);
        Map<String, Object> totalPagesCount = new HashMap<>();
        totalPagesCount.put("totalPages", (int) Math.ceil((double) totalProducts / size));
        totalPagesCount.put("totalCount", totalProducts);
        return totalPagesCount;
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Product> root, String categoryName, String tagName, double min, double max, String searchQuery) {
        List<Predicate> predicates = new ArrayList<>();
        if (categoryName != null && !categoryName.isEmpty()) {
            predicates.add(cb.equal(root.get("category").get("categoryName"), categoryName));
        }
        if (tagName != null && !tagName.isEmpty()) {
            predicates.add(cb.equal(root.get("tag").get("tagName"), tagName));
        }
        if (min >= 0 && max >= 0 && min <= max) {
            predicates.add(cb.between(root.get("productPrice"), min, max));
        }
        predicates.add(cb.equal(root.get("isDeleted"), false));

        if (searchQuery != null && !searchQuery.isEmpty()) {
            predicates.add(cb.or(
                    cb.like(root.get("productName"), "%" + searchQuery + "%"),
                    cb.like(root.get("productDescription"), "%" + searchQuery + "%")
            ));
        }
        return predicates;
    }
}
