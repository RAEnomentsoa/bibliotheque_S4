package service;

import model.TypeAdherent;
import java.util.List;
import java.util.Optional;

public interface TypeAdherentService {
    List<TypeAdherent> findAll();
    Optional<TypeAdherent> findById(Long id);
    TypeAdherent save(TypeAdherent typeAdherent);
    void deleteById(Long id);
}
