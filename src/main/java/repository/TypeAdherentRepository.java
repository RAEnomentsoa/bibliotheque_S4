package repository;

import model.TypeAdherent;
import java.util.List;
import java.util.Optional;

public interface TypeAdherentRepository {
    List<TypeAdherent> findAll();
    Optional<TypeAdherent> findById(int id);
    TypeAdherent save(TypeAdherent typeAdherent);
    void deleteById(int id);
}
