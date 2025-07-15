package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Abonnement;
import org.springframework.stereotype.Repository;
import repository.AbonnementRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AbonnementRepositoryImpl implements AbonnementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Abonnement> findAll() {
        return entityManager.createQuery("SELECT a FROM Abonnement a", Abonnement.class).getResultList();
    }

    @Override
    public Optional<Abonnement> findById(Long id) {
        Abonnement abonnement = entityManager.find(Abonnement.class, id);
        return Optional.ofNullable(abonnement);
    }

    @Override
    public Abonnement save(Abonnement abonnement) {
        if (abonnement.getId() == null) {
            entityManager.persist(abonnement);
        } else {
            entityManager.merge(abonnement);
        }
        return abonnement;
    }

    @Override
    public void deleteById(Long id) {
        Abonnement abonnement = entityManager.find(Abonnement.class, id);
        if (abonnement != null) {
            entityManager.remove(abonnement);
        }
    }

  @Override
public Abonnement findActifByAdherentId(Integer adherentId) {
    //mijery hoe abonne ve izy
    String jpql = """
        SELECT a FROM Abonnement a
        WHERE a.adherent.id = :adherentId
          AND :today BETWEEN a.dateDebut AND a.dateFin
    """;

    TypedQuery<Abonnement> query = entityManager.createQuery(jpql, Abonnement.class);
    query.setParameter("adherentId", adherentId);
    query.setParameter("today", LocalDate.now()); 

    List<Abonnement> result = query.getResultList();
    return result.isEmpty() ? null : result.get(0);
}

}
