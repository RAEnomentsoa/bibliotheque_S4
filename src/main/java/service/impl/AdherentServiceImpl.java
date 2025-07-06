package service.impl;

import model.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import repository.AdherentRepository;
import service.AdherentService;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentServiceImpl implements AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Override
    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    @Override
    public Optional<Adherent> findById(Long id) {
        return adherentRepository.findById(id);
    }

    @Override
    @Transactional
    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    @Override
    public void deleteById(Long id) {
        adherentRepository.deleteById(id);
    }

    @Override
public Adherent findByEmailAndPassword(String email, String motDePasse) {
    return adherentRepository.findByEmailAndMotDePasse(email, motDePasse);
}


}
