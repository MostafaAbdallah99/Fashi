package persistence.repository.repositories;

import persistence.entities.Product;
import persistence.repository.GenericRepositoryImpl;
import persistence.repository.interfaces.ProductRepository;


public class ProductRepositoryImpl extends GenericRepositoryImpl<Product, Long> implements ProductRepository {
    public ProductRepositoryImpl(Class<Product> entityClass) {
        super(entityClass);
    }
}
