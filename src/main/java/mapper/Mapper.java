package mapper;

import java.util.List;

public interface Mapper<F, T> {
    T fullMap(F object);
    List<T> fullMap(List<F> collection);
}
