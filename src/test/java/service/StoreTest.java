package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import repository.ProducerRepository;
import repository.ProductRepository;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

class StoreTest {
    @Test
    public void getAllProducers() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        var producerRepository = new ProducerRepository(session);
        var productRepository = new ProductRepository(session);

        Store store = new Store(producerRepository, productRepository);

        var result = store.getProducts();
        System.out.println(result);

        session.getTransaction().commit();
    }

}