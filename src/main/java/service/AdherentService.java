package service;

import model.Adherent;
import java.util.List;
import java.util.Optional;

public interface AdherentService {
    List<Adherent> findAll();
    Optional<Adherent> findById(Long id);
    Adherent save(Adherent adherent);
    void deleteById(Long id);
    Adherent findByEmailAndPassword(String email, String motDePasse);

}
