package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.EtatExemplaire;
import org.springframework.stereotype.Repository;
import repository.EtatExemplaireRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class EtatExemplaireRepositoryImpl implements EtatExemplaireRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EtatExemplaire> findAll() {
        return entityManager.createQuery("SELECT e FROM EtatExemplaire e", EtatExemplaire.class).getResultList();
    }

    @Override
    public Optional<EtatExemplaire> findById(Long id) {
        EtatExemplaire etatExemplaire = entityManager.find(EtatExemplaire.class, id);
        return Optional.ofNullable(etatExemplaire);
    }

    @Override
    public EtatExemplaire save(EtatExemplaire etatExemplaire) {
        if (etatExemplaire.getId() == null) {
            entityManager.persist(etatExemplaire);
        } else {
            entityManager.merge(etatExemplaire);
        }
        return etatExemplaire;
    }

    @Override
    public void deleteById(Long id) {
        EtatExemplaire etatExemplaire = entityManager.find(EtatExemplaire.class, id);
        if (etatExemplaire != null) {
            entityManager.remove(etatExemplaire);
        }
    }

       @Override
    public EtatExemplaire findByLibelle(String libelle) {
        String jpql = "SELECT e FROM EtatExemplaire e WHERE e.libelle = :libelle";
        TypedQuery<EtatExemplaire> query = entityManager.createQuery(jpql, EtatExemplaire.class);
        query.setParameter("libelle", libelle);
        return query.getResultStream().findFirst().orElse(null);
    }
}
