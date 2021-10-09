package com.itis.servletsapp.database;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(String username);
    List<T> findAll();
    T save(T item);
    void delete(K id);
}
