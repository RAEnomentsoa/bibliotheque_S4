package repository;

import model.Pret;
import java.util.List;
import java.util.Optional;

public interface PretRepository {
    List<Pret> findAll();
    Optional<Pret> findById(Long id);
    Pret save(Pret pret);
    void deleteById(Long id);
}
