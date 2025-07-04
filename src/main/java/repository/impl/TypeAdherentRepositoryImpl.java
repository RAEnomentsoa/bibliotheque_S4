package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.TypeAdherent;
import org.springframework.stereotype.Repository;
import repository.TypeAdherentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TypeAdherentRepositoryImpl implements TypeAdherentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TypeAdherent> findAll() {
        return entityManager.createQuery("SELECT t FROM TypeAdherent t", TypeAdherent.class).getResultList();
    }

    @Override
    public Optional<TypeAdherent> findById(int id) {
        TypeAdherent typeAdherent = entityManager.find(TypeAdherent.class, id);
        return Optional.ofNullable(typeAdherent);
    }

    @Override
    public TypeAdherent save(TypeAdherent typeAdherent) {
        if (typeAdherent.getId() == null) {
            entityManager.persist(typeAdherent);
        } else {
            entityManager.merge(typeAdherent);
        }
        return typeAdherent;
    }

    @Override
    public void deleteById(int id) {
        TypeAdherent typeAdherent = entityManager.find(TypeAdherent.class, id);
        if (typeAdherent != null) {
            entityManager.remove(typeAdherent);
        }
    }
}
