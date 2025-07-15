package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Pret;
import org.springframework.stereotype.Repository;
import repository.PretRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PretRepositoryImpl implements PretRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pret> findAll() {
        return entityManager.createQuery("SELECT p FROM Pret p", Pret.class).getResultList();
    }

    @Override
    public Optional<Pret> findById(int id) {
        Pret pret = entityManager.find(Pret.class, id);
        return Optional.ofNullable(pret);
    }

    @Override
    @Transactional
    public Pret save(Pret pret) {
        if (pret.getId() == null) {
            entityManager.persist(pret);
        } else {
            entityManager.merge(pret);
        }
        return pret;
    }

    @Override
    public void deleteById(Long id) {
        Pret pret = entityManager.find(Pret.class, id);
        if (pret != null) {
            entityManager.remove(pret);
        }
    }
}
