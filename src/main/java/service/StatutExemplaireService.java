package service;

import model.StatutExemplaire;
import java.util.List;
import java.util.Optional;

public interface StatutExemplaireService {
    List<StatutExemplaire> findAll();
    Optional<StatutExemplaire> findById(Long id);
    StatutExemplaire save(StatutExemplaire statutExemplaire);
    void deleteById(Long id);
}
