package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.JourFerie;
import org.springframework.stereotype.Repository;
import repository.JourFerieRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JourFerieRepositoryImpl implements JourFerieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JourFerie> findAll() {
        return entityManager.createQuery("SELECT j FROM JourFerie j", JourFerie.class).getResultList();
    }

    @Override
    public Optional<JourFerie> findById(Long id) {
        JourFerie jourFerie = entityManager.find(JourFerie.class, id);
        return Optional.ofNullable(jourFerie);
    }

    @Override
    public JourFerie save(JourFerie jourFerie) {
        if (jourFerie.getId() == null) {
            entityManager.persist(jourFerie);
        } else {
            entityManager.merge(jourFerie);
        }
        return jourFerie;
    }

    @Override
    public void deleteById(Long id) {
        JourFerie jourFerie = entityManager.find(JourFerie.class, id);
        if (jourFerie != null) {
            entityManager.remove(jourFerie);
        }
    }

    
    @Override
    public boolean existsByDateFerie(LocalDate dateFerie) {
        String jpql = "SELECT COUNT(j) FROM JourFerie j WHERE j.dateFerie = :date";
        Long count = entityManager.createQuery(jpql, Long.class)
                                  .setParameter("date", dateFerie)
                                  .getSingleResult();
        return count != null && count > 0;
    }
}
