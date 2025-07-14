package service;

import model.StatutExemplaire;
import java.util.List;
import java.util.Optional;

public interface StatutExemplaireService {
    List<StatutExemplaire> findAll();
    Optional<StatutExemplaire> findById(int id);
    StatutExemplaire save(StatutExemplaire statutExemplaire);
    void deleteById(Long id);
    List<StatutExemplaire> getExemplairesEnCoursDeReservation();
    void mettreAJourStatut(Integer exemplaireId, String nouveauStatut);
    public List<StatutExemplaire> getExemplairesReserver();

  
}
