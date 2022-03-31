package repository;

import entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductRepository extends RepositoryBase<Product, Long> {

    public ProductRepository(EntityManager entityManager) {
        super(Product.class, entityManager);
    }

    @Override
    public List<Product> findAll(String name) {
        Query query = super.getEntityManager().createQuery("from Product where name=:name");
        return query.setParameter("name", name)
                .getResultList();
    }
}
