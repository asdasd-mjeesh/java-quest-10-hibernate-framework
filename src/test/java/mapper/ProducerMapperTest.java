package mapper;

import dto.ProducerDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import repository.ProducerRepository;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

class ProducerMapperTest {
    @Test
    public void mapProductsCollectionToProducerDtoTest() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        ProducerRepository repository = new ProducerRepository(session);
        ProducerMapper mapper = new ProducerMapper();
        var producer = repository.findById(1L);
        ProducerDto producerDTO = mapper.mapFrom(producer.get());

        System.out.println(producerDTO);

        session.getTransaction().commit();
    }
}