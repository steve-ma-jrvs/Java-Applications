package ca.jrvs.apps.twitter.dao;

public interface CrdDao<T, ID> {

    /**
     * Create an entity to the underlying storage
     *
     * @param entity entity that to be created
     * @return created entity
     */
    T create(T entity);

    /**
     * Find an entity by its id
     *
     * @param id entity id
     * @return found entity
     */
    T findById(ID id);

    /**
     * Delete an entity by its ID
     *
     * @param id of the entity to be deleted
     * @return deleted entity
     */
    T deleteById(ID id);
}
