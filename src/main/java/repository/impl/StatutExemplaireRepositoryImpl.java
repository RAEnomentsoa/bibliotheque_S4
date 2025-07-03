package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.StatutExemplaire;
import org.springframework.stereotype.Repository;
import repository.StatutExemplaireRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class StatutExemplaireRepositoryImpl implements StatutExemplaireRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StatutExemplaire> findAll() {
        return entityManager.createQuery("SELECT s FROM StatutExemplaire s", StatutExemplaire.class).getResultList();
    }

    @Override
    public Optional<StatutExemplaire> findById(Long id) {
        StatutExemplaire statutExemplaire = entityManager.find(StatutExemplaire.class, id);
        return Optional.ofNullable(statutExemplaire);
    }

    @Override
    public StatutExemplaire save(StatutExemplaire statutExemplaire) {
        if (statutExemplaire.getId() == null) {
            entityManager.persist(statutExemplaire);
        } else {
            entityManager.merge(statutExemplaire);
        }
        return statutExemplaire;
    }

    @Override
    public void deleteById(Long id) {
        StatutExemplaire statutExemplaire = entityManager.find(StatutExemplaire.class, id);
        if (statutExemplaire != null) {
            entityManager.remove(statutExemplaire);
        }
    }
}
