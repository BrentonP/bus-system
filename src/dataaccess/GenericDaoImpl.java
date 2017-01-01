package dataaccess;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class GenericDaoImpl<T extends Identifiable<PK>, PK extends Serializable>
        implements GenericDao<T, PK> {

    /**
     * Use a HashMap to act as a database for this exercise, but would need to
     * use a database like SQL for persistent storage.
     */
    private final HashMap<PK, T> database;

    public GenericDaoImpl() {
        this.database = new HashMap<PK, T>();
    }

    @Override
    public T create(T newItem) {
        return database.put(newItem.getId(), newItem);
    }

    @Override
    public T findById(PK id) {
        return database.get(id);
    }

    @Override
    public Collection<T> findAll() {
        return database.values();
    }

    @Override
    public T update(T updatedItem) {
        return database.put(updatedItem.getId(), updatedItem);
    }

    @Override
    public T delete(PK id) {
        return database.remove(id);
    }
}
