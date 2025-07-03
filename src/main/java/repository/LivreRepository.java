package repository;

import model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreRepository {
    List<Livre> findAll();
    Optional<Livre> findById(Long id);
    Livre save(Livre livre);
    void deleteById(Long id);
}
