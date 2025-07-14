package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.TypePret;
import org.springframework.stereotype.Repository;
import repository.TypePretRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TypePretRepositoryImpl implements TypePretRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TypePret> findAll() {
        return entityManager.createQuery("SELECT t FROM TypePret t", TypePret.class).getResultList();
    }

    @Override
    public Optional<TypePret> findById(int id) {
        TypePret typePret = entityManager.find(TypePret.class, id);
        return Optional.ofNullable(typePret);
    }

    @Override
    public TypePret save(TypePret typePret) {
        if (typePret.getId() == null) {
            entityManager.persist(typePret);
        } else {
            entityManager.merge(typePret);
        }
        return typePret;
    }

    @Override
    public void deleteById(Long id) {
        TypePret typePret = entityManager.find(TypePret.class, id);
        if (typePret != null) {
            entityManager.remove(typePret);
        }
    }
}
