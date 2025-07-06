package repository;

import model.Abonnement;
import java.util.List;
import java.util.Optional;

public interface AbonnementRepository {
    List<Abonnement> findAll();
    Optional<Abonnement> findById(Long id);
    Abonnement save(Abonnement abonnement);
    void deleteById(Long id);
    Abonnement findActifByAdherentId(Integer adherentId);
}
