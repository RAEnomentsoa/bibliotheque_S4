package service;

import model.Adherent;
import java.util.List;
import java.util.Optional;

public interface AdherentService {
    List<Adherent> findAll();
    Optional<Adherent> findById(int id);
    Adherent save(Adherent adherent);
    void deleteById(int id);
    boolean inscrire(String nom, String email, String motDePasse, String dateNaissance, int typeId);
}
