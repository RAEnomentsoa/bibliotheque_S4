package service.impl;

import model.Abonnement;
import model.Adherent;
import model.Exemplaire;
import model.Livre;
import model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.AbonnementRepository;
import repository.LivreRepository;
import repository.ReservationRepository;
import service.LivreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    @Override
    public Optional<Livre> findById(int id) {
        return livreRepository.findById(id);
    }

    @Override
    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void deleteById(Long id) {
        livreRepository.deleteById(id);
    }

     @Override
    public List<Livre> livresAvecExemplairesLibres() {
        return livreRepository.findLivresAvecExemplairesDisponibles();
    }

    @Override
    public boolean reserverLivrePourAdherent(Integer livreId, Adherent adherent) {
        // vérifier si l'adhérent a un abonnement actif
        Abonnement abonnement = abonnementRepository.findActifByAdherentId(adherent.getId());
        if (abonnement == null) {
            return false;
        }

        // trouver un exemplaire disponible pour ce livre
        Exemplaire exemplaire = livreRepository.findExemplaireDisponible(livreId);
        if (exemplaire == null) {
            return false;
        }

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaire);
        reservation.setDateReservation(LocalDate.now());
        reservationRepository.save(reservation);

        return true;
    }

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private ReservationRepository reservationRepository;
}
