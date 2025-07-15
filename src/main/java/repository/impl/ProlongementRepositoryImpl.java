package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Prolongement;
import org.springframework.stereotype.Repository;
import repository.ProlongementRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProlongementRepositoryImpl implements ProlongementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Prolongement> findAll() {
        return entityManager.createQuery("SELECT p FROM Prolongement p", Prolongement.class).getResultList();
    }

    @Override
    public Optional<Prolongement> findById(Long id) {
        Prolongement prolongement = entityManager.find(Prolongement.class, id);
        return Optional.ofNullable(prolongement);
    }

    @Override
    public Prolongement save(Prolongement prolongement) {
        if (prolongement.getId() == null) {
            entityManager.persist(prolongement);
        } else {
            entityManager.merge(prolongement);
        }
        return prolongement;
    }

    @Override
    public void deleteById(Long id) {
        Prolongement prolongement = entityManager.find(Prolongement.class, id);
        if (prolongement != null) {
            entityManager.remove(prolongement);
        }
    }
}
