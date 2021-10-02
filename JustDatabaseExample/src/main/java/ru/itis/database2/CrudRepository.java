package ru.itis.database2;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(K id);
    List<T> findAll();
    T save(T item);
    void update(K id, T item) throws SQLException;
    void delete(K id) throws SQLException;
}
