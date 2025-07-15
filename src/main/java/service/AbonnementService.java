package service;

import model.Abonnement;
import java.util.List;
import java.util.Optional;

public interface AbonnementService {
    List<Abonnement> findAll();
    Optional<Abonnement> findById(Long id);
    Abonnement save(Abonnement abonnement);
    void deleteById(Long id);
    boolean aUnAbonnementActif(Integer adherentId);

}
