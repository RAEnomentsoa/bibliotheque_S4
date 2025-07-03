package service.impl;

import model.Bibliothecaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BibliothecaireRepository;
import service.BibliothecaireService;

import java.util.List;
import java.util.Optional;

@Service
public class BibliothecaireServiceImpl implements BibliothecaireService {

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    @Override
    public List<Bibliothecaire> findAll() {
        return bibliothecaireRepository.findAll();
    }

    @Override
    public Optional<Bibliothecaire> findById(Long id) {
        return bibliothecaireRepository.findById(id);
    }

    @Override
    public Bibliothecaire save(Bibliothecaire bibliothecaire) {
        return bibliothecaireRepository.save(bibliothecaire);
    }

    @Override
    public void deleteById(Long id) {
        bibliothecaireRepository.deleteById(id);
    }

    @Override
    public Bibliothecaire login(String nom, String motDePasse) {
        return bibliothecaireRepository.findByNomAndMotDePasse(nom, motDePasse);
    }
}
