package repository;

import model.Adherent;
import java.util.List;
import java.util.Optional;

public interface AdherentRepository {
    List<Adherent> findAll();
    Optional<Adherent> findById(Long id);
    Adherent save(Adherent adherent);
    void deleteById(Long id);
    Adherent findByEmailAndMotDePasse(String email, String motDePasse);

}
