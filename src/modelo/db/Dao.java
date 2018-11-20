package modelo.db;

import java.util.List;

public interface Dao <T> {
    void create(T object);
    void delete(T object);
    void update(T object);
    List<T> all();
    T findById(int id);
}
