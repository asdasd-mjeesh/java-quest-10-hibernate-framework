package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryBase<E, K> implements Repository<E, K> {
    private final Class<E> clazz;
    private final EntityManager entityManager;

    public RepositoryBase(Class<E> clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
