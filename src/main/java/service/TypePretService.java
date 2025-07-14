package service;

import model.TypePret;
import java.util.List;
import java.util.Optional;

public interface TypePretService {
    List<TypePret> findAll();
    Optional<TypePret> findById(int id);
    TypePret save(TypePret typePret);
    void deleteById(Long id);
}
