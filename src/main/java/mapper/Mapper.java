package mapper;

import java.util.List;

public interface Mapper<F, T> {
    T mapFrom(F object);
    List<T> mapFrom(List<F> collection);
}
