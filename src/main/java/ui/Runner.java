package ui;

import service.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ProducerRepository;
import repository.ProductRepository;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

public class Runner {

    public static void main(String[] args) {
        Runner asd = new Runner();
        asd.run();
    }

    private void run() {
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
