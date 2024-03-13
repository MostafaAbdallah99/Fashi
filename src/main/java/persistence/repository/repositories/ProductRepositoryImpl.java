package persistence.repository.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import persistence.entities.Product;
import persistence.repository.generic.GenericRepositoryImpl;
import persistence.repository.interfaces.ProductRepository;
import persistence.repository.utils.TransactionUtil;

import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl extends GenericRepositoryImpl<Product, Long> implements ProductRepository {
    public ProductRepositoryImpl(Class<Product> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.createQuery("SELECT p FROM Product p WHERE p.category.categoryName = :categoryName", Product.class)
                .setParameter("categoryName", categoryName)
                .getResultList());
    }

    @Override
    public List<Product> getProductsByTag(String tagName) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.createQuery("SELECT p FROM Product p WHERE p.tag.tagName = :tagName", Product.class)
                .setParameter("tagName", tagName)
                .getResultList());
    }

    @Override
    public List<Product> getProductsByCategoryAndTag(String categoryName, String tagName) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.createQuery("SELECT p FROM Product p WHERE p.category.categoryName = :categoryName AND p.tag.tagName = :tagName", Product.class)
                .setParameter("categoryName", categoryName)
                .setParameter("tagName", tagName)
                .getResultList());

    }

    @Override
    public List<Product> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max) {
        return TransactionUtil.doInTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> product = query.from(Product.class);
            List<Predicate> predicates = new ArrayList<>();
            if(categoryName != null && !categoryName.isEmpty()) {
                predicates.add(cb.equal(product.get("category").get("categoryName"), categoryName));
            }
            if(tagName != null && !tagName.isEmpty()) {
                predicates.add(cb.equal(product.get("tag").get("tagName"), tagName));
            }
            if(min >= 0 && max >= 0 && min <= max) {
                predicates.add(cb.between(product.get("productPrice"), min, max));
            }
            query.select(product).where(predicates.toArray(new Predicate[0]));
            return entityManager.createQuery(query).getResultList();
        });
    }
}
