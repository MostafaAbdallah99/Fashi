package persistence.repository.generic;

import java.util.List;

public interface GenericRepository<T, ID> {
    T findById(ID id);

    T findReferenceById(ID id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void refresh(T entity);
}