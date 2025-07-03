package repository;

import model.TypePret;
import java.util.List;
import java.util.Optional;

public interface TypePretRepository {
    List<TypePret> findAll();
    Optional<TypePret> findById(Long id);
    TypePret save(TypePret typePret);
    void deleteById(Long id);
}
