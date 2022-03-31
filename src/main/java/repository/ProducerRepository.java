package repository;

import entity.Producer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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
}
