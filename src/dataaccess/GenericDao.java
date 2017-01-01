package dataaccess;

import java.io.Serializable;
import java.util.Collection;

/**
 * Having a DAO Interface means that we can change the implementation of the
 * database without needing to rewrite the rest of the program.
 *
 * Using a Generic DAO means we don't have to have as much code duplication.
 *
 * @param <T> The object type that will be stored in the database
 * @param <PK> The type (e.g. String) of the identifier (e.g. stopCode) for T.
 */
public interface GenericDao<T extends Identifiable<PK>, PK extends Serializable> {

    public T create(T newItem);

    public T findById(PK id);

    public Collection<T> findAll();

    public T update(T updatedItem);

    public T delete(PK primaryKey);
}
