package service;

import model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreService {
    List<Livre> findAll();
    Optional<Livre> findById(Long id);
    Livre save(Livre livre);
    void deleteById(Long id);
}
