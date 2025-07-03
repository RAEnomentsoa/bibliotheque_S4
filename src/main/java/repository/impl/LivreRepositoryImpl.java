package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Livre;
import org.springframework.stereotype.Repository;
import repository.LivreRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class LivreRepositoryImpl implements LivreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Livre> findAll() {
        return entityManager.createQuery("SELECT l FROM Livre l", Livre.class).getResultList();
    }

    @Override
    public Optional<Livre> findById(Long id) {
        Livre livre = entityManager.find(Livre.class, id);
        return Optional.ofNullable(livre);
    }

    @Override
    public Livre save(Livre livre) {
        if (livre.getId() == null) {
            entityManager.persist(livre);
        } else {
            entityManager.merge(livre);
        }
        return livre;
    }

    @Override
    public void deleteById(Long id) {
        Livre livre = entityManager.find(Livre.class, id);
        if (livre != null) {
            entityManager.remove(livre);
        }
    }
}
