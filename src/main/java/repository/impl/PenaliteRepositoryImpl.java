package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Penalite;
import org.springframework.stereotype.Repository;
import repository.PenaliteRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PenaliteRepositoryImpl implements PenaliteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Penalite> findAll() {
        return entityManager.createQuery("SELECT p FROM Penalite p", Penalite.class).getResultList();
    }

    @Override
    public Optional<Penalite> findById(Long id) {
        Penalite penalite = entityManager.find(Penalite.class, id);
        return Optional.ofNullable(penalite);
    }

    @Override
    public Penalite save(Penalite penalite) {
        if (penalite.getId() == null) {
            entityManager.persist(penalite);
        } else {
            entityManager.merge(penalite);
        }
        return penalite;
    }

    @Override
    public void deleteById(Long id) {
        Penalite penalite = entityManager.find(Penalite.class, id);
        if (penalite != null) {
            entityManager.remove(penalite);
        }
    }
}
