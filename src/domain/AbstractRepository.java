package domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a high level class repository. Common methods are implemented here
 * @param <T> is an entity class
 */
public abstract class  AbstractRepository<T extends Serializable> {
    /**
     * This map is for keeping our data consistent. It's key is Id of our entity. While this is a map, finding an element by id is very fast
     */
    protected Map<Integer,T> entityMap;

    public AbstractRepository() {
        /**
         * This guarantee the data is thread safe
         */
        entityMap = new ConcurrentHashMap<>();
    }

    protected void add(T entity)
    {
        entityMap.put(entity.hashCode(),entity);
    }
    protected Optional<T> find(T t)
    {
        Optional<T> tOptional = Optional.ofNullable(entityMap.get(t.hashCode()));
        return tOptional;
    }

}
