package service;

import org.junit.jupiter.api.Test;
import util.HibernateUtil;

class StoreTest {
    @Test
    public void getAllProducers() {
        var sessionFactory = HibernateUtil.buildSessionFactory();



        Store store = new Store(sessionFactory);

        var result = store.getProducts();
        System.out.println(result);

    }

}