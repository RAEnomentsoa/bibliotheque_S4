package service.impl;

import model.Abonnement;
import model.Adherent;
import model.Exemplaire;
import model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import repository.AbonnementRepository;
import repository.LivreRepository;
import repository.ReservationRepository;
import service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

     @Autowired
    private LivreRepository livreRepository;

     @Autowired
    private AbonnementRepository abonnementRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
    public void setLivreRepository(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public void setAbonnementRepository(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public boolean reserverLivrePourAdherent(Integer livreId, Adherent adherent) {
        // Vérifier si l'adhérent est abonné
        if (abonnementRepository.findActifByAdherentId(adherent.getId()) == null) {
            return false; // Pas d'abonnement actif
        }

        // Vérifier s’il existe un exemplaire disponible
        Exemplaire exemplaireDispo = livreRepository.findExemplaireDisponible(livreId);
        if (exemplaireDispo == null) {
            return false; // Aucun exemplaire libre
        }

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaireDispo);
        reservation.setDateReservation(LocalDate.now());

        reservationRepository.save(reservation);

        return true;
    }
    @Override
@Transactional
public boolean reserverExemplairePourAdherent(Integer exemplaireId, Adherent adherent) {
    // Vérifie si l'utilisateur est abonné
    Abonnement abonnement = abonnementRepository.findActifByAdherentId(adherent.getId());
    if (abonnement == null) return false;

    // Vérifie si l'exemplaire est libre
    if (!livreRepository.isExemplaireDisponible(exemplaireId)) return false;

    // Crée une réservation
    Reservation reservation = new Reservation();
    reservation.setAdherent(adherent);
    reservation.setExemplaire(new Exemplaire(exemplaireId)); // constructeur avec ID
    reservation.setDateReservation(LocalDate.now());

    reservationRepository.save(reservation);
    return true;
}

public String getNomAdherentParExemplaireId(int exemplaireId) {
    Reservation reservation = reservationRepository.findFirstByExemplaireId(exemplaireId);
    if (reservation != null && reservation.getAdherent() != null) {
        Adherent adherent = reservation.getAdherent();
        return adherent.getNom();
    }
    return "Non réservé";
}

}
