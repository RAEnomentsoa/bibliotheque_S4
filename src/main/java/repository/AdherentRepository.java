package repository;

import model.Adherent;
import java.util.List;
import java.util.Optional;

public interface AdherentRepository {
    List<Adherent> findAll();
    Optional<Adherent> findById(int id);
    Adherent save(Adherent adherent);
    void deleteById(int id);
    boolean existsByEmail(String email);
}
