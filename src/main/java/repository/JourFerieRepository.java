package repository;

import model.JourFerie;
import java.util.List;
import java.util.Optional;

public interface JourFerieRepository {
    List<JourFerie> findAll();
    Optional<JourFerie> findById(Long id);
    JourFerie save(JourFerie jourFerie);
    void deleteById(Long id);
}
