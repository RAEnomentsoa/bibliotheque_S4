package service;

import model.Bibliothecaire;
import java.util.List;
import java.util.Optional;

public interface BibliothecaireService {
    List<Bibliothecaire> findAll();

    Optional<Bibliothecaire> findById(Long id);

    Bibliothecaire save(Bibliothecaire bibliothecaire);

    void deleteById(Long id);

    Bibliothecaire login(String nom, String motDePasse);
}
