package repository;

import model.StatutExemplaire;
import java.util.List;
import java.util.Optional;

public interface StatutExemplaireRepository {
    List<StatutExemplaire> findAll();
    Optional<StatutExemplaire> findById(int id);
    StatutExemplaire save(StatutExemplaire statutExemplaire);
    void deleteById(Long id);
    List<StatutExemplaire> findByEtatExemplaireLibelle(String libelle);
}
