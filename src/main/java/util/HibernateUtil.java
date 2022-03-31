package util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        //configuration.addAnnotatedClass(User.class);
        //configuration.addAttributeConverter(new BirthDayConverter());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
