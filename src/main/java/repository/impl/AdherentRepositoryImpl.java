package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Adherent;
import org.springframework.stereotype.Repository;
import repository.AdherentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdherentRepositoryImpl implements AdherentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Adherent> findAll() {
        return entityManager.createQuery("SELECT a FROM Adherent a", Adherent.class).getResultList();
    }

    @Override
    public Optional<Adherent> findById(Long id) {
        Adherent adherent = entityManager.find(Adherent.class, id);
        return Optional.ofNullable(adherent);
    }

    @Override
    public Adherent save(Adherent adherent) {
        if (adherent.getId() == null) {
            entityManager.persist(adherent);
        } else {
            entityManager.merge(adherent);
        }
        return adherent;
    }

    @Override
    public void deleteById(Long id) {
        Adherent adherent = entityManager.find(Adherent.class, id);
        if (adherent != null) {
            entityManager.remove(adherent);
        }
    }
}
