package main.java.app.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T t) throws IOException;
    List<T> findAll() throws IOException;
    void update(T t) throws IOException;
    void delete(ID id) throws IOException;

    T getById(ID id) throws FileNotFoundException, IOException;
}
