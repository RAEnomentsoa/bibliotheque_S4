package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.JourOuvrable;
import org.springframework.stereotype.Repository;
import repository.JourOuvrableRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JourOuvrableRepositoryImpl implements JourOuvrableRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JourOuvrable> findAll() {
        return entityManager.createQuery("SELECT j FROM JourOuvrable j", JourOuvrable.class).getResultList();
    }

    @Override
    public Optional<JourOuvrable> findById(Long id) {
        JourOuvrable jourOuvrable = entityManager.find(JourOuvrable.class, id);
        return Optional.ofNullable(jourOuvrable);
    }

    @Override
    public JourOuvrable save(JourOuvrable jourOuvrable) {
        if (jourOuvrable.getId() == null) {
            entityManager.persist(jourOuvrable);
        } else {
            entityManager.merge(jourOuvrable);
        }
        return jourOuvrable;
    }

    @Override
    public void deleteById(Long id) {
        JourOuvrable jourOuvrable = entityManager.find(JourOuvrable.class, id);
        if (jourOuvrable != null) {
            entityManager.remove(jourOuvrable);
        }
    }
}
