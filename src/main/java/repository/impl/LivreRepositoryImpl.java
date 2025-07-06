package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Exemplaire;
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
    public Optional<Livre> findById(int id) {
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

    @Override
    public Exemplaire findExemplaireDisponible(Integer livreId) {
        String jpql = """
            SELECT e FROM Exemplaire e
            WHERE e.livre.id = :livreId
              AND e.id NOT IN (
                SELECT r.exemplaire.id FROM Reservation r
              )
        """;

        TypedQuery<Exemplaire> query = entityManager.createQuery(jpql, Exemplaire.class);
        query.setParameter("livreId", livreId);
        query.setMaxResults(1);

        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
public List<Livre> findLivresAvecExemplairesDisponibles() {
    String jpql = """
        SELECT DISTINCT l FROM Livre l
        WHERE EXISTS (
            SELECT e FROM Exemplaire e
            WHERE e.livre.id = l.id
              AND e.id NOT IN (
                  SELECT r.exemplaire.id FROM Reservation r
              )
        )
    """;

    return entityManager.createQuery(jpql, Livre.class).getResultList();
}
@Override
public boolean isExemplaireDisponible(Integer exemplaireId) {
   String jpql = """
    SELECT COUNT(se) = 0 FROM StatutExemplaire se
    WHERE se.exemplaire.id = :id AND se.etatExemplaire.libelle <> 'libre'
""";
    return entityManager.createQuery(jpql, Boolean.class)
            .setParameter("id", exemplaireId)
            .getSingleResult();
}


}
