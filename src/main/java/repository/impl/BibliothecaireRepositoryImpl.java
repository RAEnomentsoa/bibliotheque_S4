package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Bibliothecaire;
import org.springframework.stereotype.Repository;
import repository.BibliothecaireRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class BibliothecaireRepositoryImpl implements BibliothecaireRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bibliothecaire> findAll() {
        return entityManager.createQuery("SELECT b FROM Bibliothecaire b", Bibliothecaire.class).getResultList();
    }

    @Override
    public Optional<Bibliothecaire> findById(int id) {
        Bibliothecaire bibliothecaire = entityManager.find(Bibliothecaire.class, id);
        return Optional.ofNullable(bibliothecaire);
    }

    @Override
    public Bibliothecaire save(Bibliothecaire bibliothecaire) {
        if (bibliothecaire.getId() == null) {
            entityManager.persist(bibliothecaire);
        } else {
            entityManager.merge(bibliothecaire);
        }
        return bibliothecaire;
    }

    @Override
    public void deleteById(Long id) {
        Bibliothecaire bibliothecaire = entityManager.find(Bibliothecaire.class, id);
        if (bibliothecaire != null) {
            entityManager.remove(bibliothecaire);
        }
    }

    @Override
    public Bibliothecaire findByNomAndMotDePasse(String nom, String motDePasse) {
        TypedQuery<Bibliothecaire> query = entityManager.createQuery(
            "SELECT b FROM Bibliothecaire b WHERE b.nom = :nom AND b.motDePasse = :motDePasse", Bibliothecaire.class);
        query.setParameter("nom", nom);
        query.setParameter("motDePasse", motDePasse);
        return query.getResultStream().findFirst().orElse(null);
    }
}
