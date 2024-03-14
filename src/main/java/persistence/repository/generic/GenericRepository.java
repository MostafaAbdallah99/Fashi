package persistence.repository.generic;

import java.util.List;

public interface GenericRepository<T, ID> {
    T findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}