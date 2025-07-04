package service.impl;

import model.Adherent;
import model.TypeAdherent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdherentRepository;
import repository.TypeAdherentRepository;
import service.AdherentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;  

@Service
@Transactional 
public class AdherentServiceImpl implements AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Override
    public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    @Override
    public Optional<Adherent> findById(int id) {
        return adherentRepository.findById(id);
    }

    @Override
    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    @Override
    public void deleteById(int id) {
        adherentRepository.deleteById(id);
    }

       @Override
    public boolean inscrire(String nom, String email, String motDePasse, 
                          String dateNaissance, int typeId) {
        if (adherentRepository.existsByEmail(email)) {
            return false;
        }
        
        Adherent adherent = new Adherent();
        adherent.setNom(nom);
        adherent.setEmail(email);
        adherent.setMotDePasse(motDePasse); // Always encode passwords!
        adherent.setDateNaissance(LocalDate.parse(dateNaissance));
        adherent.setTypeAdherent(typeId);
        
        adherentRepository.save(adherent);
        return true;
    }

    // Additional method to get member type details
    public TypeAdherent getMemberTypeDetails(int typeId) {
        return typeAdherentRepository.findById(typeId)
            .orElseThrow(() -> new IllegalArgumentException("Type adherent invalide"));
    }

}
