package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public Optional<StatutExemplaire> findById(int id) {
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

        @Override
    public List<StatutExemplaire> findByEtatExemplaireLibelle(String libelle) {
        String jpql = "SELECT se FROM StatutExemplaire se " +
                    "WHERE se.id IN (" +
                    "  SELECT MAX(s2.id) " +
                    "  FROM StatutExemplaire s2 " +
                    "  GROUP BY s2.exemplaire.id" +
                    ") " +
                    "AND se.etatExemplaire.libelle = :libelle";

        TypedQuery<StatutExemplaire> query = entityManager.createQuery(jpql, StatutExemplaire.class);
        query.setParameter("libelle", libelle);
        return query.getResultList();
    }

}

        
    