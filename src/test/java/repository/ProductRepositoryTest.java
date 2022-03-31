package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

class ProductRepositoryTest {
    @Test
    public void findByIdTest() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        ProductRepository repository = new ProductRepository(session);
        var result = repository.findById(6L);
        System.out.println(result.get());

        session.getTransaction().commit();
    }

}