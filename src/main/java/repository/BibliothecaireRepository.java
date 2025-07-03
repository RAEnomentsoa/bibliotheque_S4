package repository;

import java.util.List;
import java.util.Optional;

import model.Bibliothecaire;

public interface BibliothecaireRepository {

    List<Bibliothecaire> findAll();
    Optional<Bibliothecaire> findById(Long id);
    Bibliothecaire save(Bibliothecaire categorie);
    void deleteById(Long id);
    Bibliothecaire findByNomAndMotDePasse(String nom, String motDePasse);
}
