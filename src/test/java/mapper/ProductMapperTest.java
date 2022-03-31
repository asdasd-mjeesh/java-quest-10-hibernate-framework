package mapper;

import dto.ProductDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import repository.ProductRepository;
import util.HibernateUtil;

import java.lang.reflect.Proxy;

class ProductMapperTest {
    @Test
    public void mapProducerFieldToProductDtoTest() {
        var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));

        session.beginTransaction();

        ProductRepository repository = new ProductRepository(session);
        
        ProducerMapper producerMapper = new ProducerMapper();
        ProductMapper productMapper = new ProductMapper(producerMapper);
        
        var product = repository.findById(2L);
        ProductDto productDTO = productMapper.mapFrom(product.get());

        System.out.println(productDTO);

        session.getTransaction().commit();
    }

}