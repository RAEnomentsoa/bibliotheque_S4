package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Abonnement;
import org.springframework.stereotype.Repository;
import repository.AbonnementRepository;

import java.util.List;
import java.util.Optional;

@Repository
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
}
