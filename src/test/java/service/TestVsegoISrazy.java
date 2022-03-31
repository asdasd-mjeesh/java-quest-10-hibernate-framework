package service;

import entity.Producer;
import entity.Product;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import repository.ProducerRepository;
import repository.ProductRepository;
import util.HibernateUtil;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

// TODO: ЛЕНЬ ПИСАТЬ ТЕСТЫ
class TestVsegoISrazy {

    // TODO: REPOSITORY CRUD TESTS --begin
    @Test
    void testRepositorySave() {
        Producer producer = Producer.builder()
                .name("test_producer")
                .contact(1337)
                .build();

        Product product = Product.builder()
                .name("test_watter")
                .producer(producer)
                .shelfLife(LocalDate.of(2000, 2, 2))
                .cost(5)
                .count(2)
                .build();

        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        var producerRepository = new ProducerRepository(session);
        producerRepository.save(producer);
        var productRepository = new ProductRepository(session);
        productRepository.save(product);
    }

    @Test
    void testRepositoryUpdate() {
        Producer producer = Producer.builder()
                .id(1L)
                .build();


    }

    @Test
    void testRepositoryFindById() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        var producerRepository = new ProducerRepository(session);
        var result = producerRepository.findById(1L);
        System.out.println(result);
        System.out.println(result.get().getProducts());

        session.getTransaction().commit();
    }

    @Test
    void testRepositoryFindAll() {

    }

    @Test
    void testRepositoryDelete() {

    }

    // TODO: REPOSITORY CRUD TESTS --end


    @Test
    void saveProductWithProducerTest() {
        Producer producer = Producer.builder()
                .name("Google")
                .contact(1337)
                .build();

        Product product = Product.builder()
                .name("watter")
                .producer(producer)
                .shelfLife(LocalDate.of(2000, 2, 2))
                .build();

        @Cleanup var factory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = factory.openSession();

        session.beginTransaction();

        session.save(producer);
        session.save(product);

        session.getTransaction().commit();
    }

    @Test
    void saveProductWithoutProducerTest() {
        Producer producer = Producer.builder()
                .id(1L)
                .name("Google")
                .contact(1337)
                .build();

        Product product = Product.builder()
                .name("watter")
                .producer(producer)
                .shelfLife(LocalDate.of(2000, 2, 2))
                .build();

        @Cleanup var factory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = factory.openSession();

        session.beginTransaction();

        session.save(product);

        session.getTransaction().commit();
    }

}