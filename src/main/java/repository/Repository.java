package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    E save(E entity);              //  C
    Optional<E> findById(K id);    //  R #1
    List<E> findAll();             //  R #2
    List<E> findAll(String name);  //  R #3
    void update(E entity);         //  U
    void delete(E entity);             //  D
}
