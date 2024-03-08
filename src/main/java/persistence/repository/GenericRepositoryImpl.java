package persistence.repository;

import jakarta.persistence.*;
import persistence.CustomEntityManagerFactory;
import persistence.CustomPersistenceUnit;

import java.io.Serializable;
import java.util.List;

public class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = CustomEntityManagerFactory.getInstance(new CustomPersistenceUnit()).getEntityManager();
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String query = String.format("SELECT t FROM %s t", entityClass.getSimpleName());
        return entityManager.createQuery(query, entityClass).getResultList();
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void refresh(T entity) {
        entityManager.refresh(entity);
    }
}
