package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class  AbstractRepository<T extends Serializable> {
    protected Map<Integer,T> entityMap;

    public AbstractRepository() {
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
