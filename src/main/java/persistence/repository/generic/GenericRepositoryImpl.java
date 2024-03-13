package persistence.repository.generic;


import persistence.repository.utils.TransactionUtil;

import java.io.Serializable;
import java.util.List;

public class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID> {
    private final Class<T> entityClass;

    public GenericRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T findById(ID id) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.find(entityClass, id));
    }

    @Override
    public T findReferenceById(ID id) {
        return TransactionUtil.doInTransaction(entityManager -> entityManager.getReference(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return TransactionUtil.doInTransaction(
                entityManager -> {
                    String query = String.format("SELECT t FROM %s t", entityClass.getSimpleName());
                    return entityManager.createQuery(query, entityClass).getResultList();
                }
        );
    }

    @Override
    public void save(T entity) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.persist(entity));
    }

    @Override
    public void update(T entity) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.merge(entity));
    }

    @Override
    public void delete(T entity) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.remove(entity));
    }

    @Override
    public void refresh(T entity) {
        TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.refresh(entity));
    }
}
