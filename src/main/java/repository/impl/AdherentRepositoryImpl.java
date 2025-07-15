package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public Optional<Adherent> findById(int id) {
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

     @Override
    public Adherent findByEmailAndMotDePasse(String email, String motDePasse) {
        try {
            String jpql = "SELECT a FROM Adherent a WHERE a.email = :email AND a.motDePasse = :motDePasse";
            TypedQuery<Adherent> query = entityManager.createQuery(jpql, Adherent.class);
            query.setParameter("email", email);
            query.setParameter("motDePasse", motDePasse);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // si aucun résultat trouvé
        }
    }
}
