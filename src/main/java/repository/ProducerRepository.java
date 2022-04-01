package repository;

import entity.Producer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ProducerRepository extends RepositoryBase<Producer, Long> {

    public ProducerRepository(EntityManager entityManager) {
        super(Producer.class, entityManager);
    }

    @Override
    public List<Producer> findAll(String name) {
        Query query = super.getEntityManager().createQuery("from Producer where name=:name");
        return query.setParameter("name", name)
                .getResultList();
    }

    public Optional<Producer> findByName(String name) {
        Query query = super.getEntityManager().createQuery("from Producer where name=:name");
        Producer producer = (Producer) query.setParameter("name", name)
                .getSingleResult();

        return Optional.ofNullable(producer);
    }
}
