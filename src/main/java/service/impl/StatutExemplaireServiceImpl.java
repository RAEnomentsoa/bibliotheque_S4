package service.impl;

import model.Adherent;
import model.EtatExemplaire;
import model.Exemplaire;
import model.Reservation;
import model.StatutExemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import repository.BibliothecaireRepository;
import repository.EtatExemplaireRepository;
import repository.ExemplaireRepository;
import repository.ReservationRepository;
import repository.StatutExemplaireRepository;
import service.StatutExemplaireService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StatutExemplaireServiceImpl implements StatutExemplaireService {

    @Autowired
    private StatutExemplaireRepository statutExemplaireRepository;

    @Autowired
    ExemplaireRepository exemplaireRepository;

    @Autowired
    EtatExemplaireRepository etatExemplaireRepository;

    @Autowired
    BibliothecaireRepository bibliothecaireRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<StatutExemplaire> findAll() {
        return statutExemplaireRepository.findAll();
    }

    @Override
    public Optional<StatutExemplaire> findById(int id) {
        return statutExemplaireRepository.findById(id);
    }

    @Override
    public StatutExemplaire save(StatutExemplaire statutExemplaire) {
        return statutExemplaireRepository.save(statutExemplaire);
    }

    @Override
    public void deleteById(Long id) {
        statutExemplaireRepository.deleteById(id);
    }

    @Override    
      public List<StatutExemplaire> getExemplairesEnCoursDeReservation() {
    return statutExemplaireRepository.findByEtatExemplaireLibelle("en_attente");
}

    @Override    
      public List<StatutExemplaire> getExemplairesReserver() {
    return statutExemplaireRepository.findByEtatExemplaireLibelle("réservé");
}

@Transactional
public void mettreAJourStatut(Integer exemplaireId, String nouveauStatut) {
    
    Exemplaire exemplaire = exemplaireRepository.findById(exemplaireId).orElseThrow();
    EtatExemplaire nouvelEtat = etatExemplaireRepository.findByLibelle(nouveauStatut);

    StatutExemplaire statut = new StatutExemplaire();
    statut.setExemplaire(exemplaire);
    statut.setEtatExemplaire(nouvelEtat);
    statut.setDateChangement(LocalDate.now());
    // définir le bibliothécaire connecté si disponible
    statut.setBibliothecaire(bibliothecaireRepository.findById(1).get()); // par exemple

    statutExemplaireRepository.save(statut);
}



}
