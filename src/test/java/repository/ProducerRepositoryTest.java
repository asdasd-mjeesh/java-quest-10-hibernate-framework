package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

class ProducerRepositoryTest {
    @Test
    public void findByIdTest() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        ProducerRepository repository = new ProducerRepository(session);
        var result = repository.findById(2L);
        System.out.println(result.get());

        session.getTransaction().commit();
    }

}