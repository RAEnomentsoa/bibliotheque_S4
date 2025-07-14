package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Exemplaire;
import org.springframework.stereotype.Repository;
import repository.ExemplaireRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExemplaireRepositoryImpl implements ExemplaireRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Exemplaire> findAll() {
        return entityManager.createQuery("SELECT e FROM Exemplaire e", Exemplaire.class).getResultList();
    }

    @Override
    public Optional<Exemplaire> findById(int id) {
        Exemplaire exemplaire = entityManager.find(Exemplaire.class, id);
        return Optional.ofNullable(exemplaire);
    }

    @Override
    public Exemplaire save(Exemplaire exemplaire) {
        if (exemplaire.getId() == null) {
            entityManager.persist(exemplaire);
        } else {
            entityManager.merge(exemplaire);
        }
        return exemplaire;
    }

    @Override
    public void deleteById(Long id) {
        Exemplaire exemplaire = entityManager.find(Exemplaire.class, id);
        if (exemplaire != null) {
            entityManager.remove(exemplaire);
        }
    }

    @Override
public List<Exemplaire> findExemplairesDisponiblesByLivreId(Integer livreId) {
  String jpql = """
    SELECT e FROM Exemplaire e
    WHERE e.livre.id = :livreId
    AND NOT EXISTS (
        SELECT s FROM StatutExemplaire s
        WHERE s.exemplaire.id = e.id AND s.etatExemplaire.libelle <> 'libre'
    )
""";
    return entityManager.createQuery(jpql, Exemplaire.class)
                        .setParameter("livreId", livreId)
                        .getResultList();
}

}
