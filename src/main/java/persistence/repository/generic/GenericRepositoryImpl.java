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
    public boolean save(T entity) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.persist(entity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T entity) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.merge(entity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(T entity) {
        try {
            TransactionUtil.doInTransactionWithoutResult(entityManager -> entityManager.remove(entity));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
