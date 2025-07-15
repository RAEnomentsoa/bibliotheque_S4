package service;

import model.Exemplaire;
import java.util.List;
import java.util.Optional;

public interface ExemplaireService {
    List<Exemplaire> findAll();
    Optional<Exemplaire> findById(Long id);
    Exemplaire save(Exemplaire exemplaire);
    void deleteById(Long id);
    List<Exemplaire> findExemplairesDisponiblesByLivreId(Integer livreId);
}
