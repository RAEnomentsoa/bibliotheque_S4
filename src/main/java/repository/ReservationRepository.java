package repository;

import model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);
    void deleteById(Long id);
    Reservation findFirstByExemplaireId(Integer exemplaireId);
    Optional<Reservation> findFirstByExemplaireIdOrderByDateReservationDesc(Integer exemplaireId);
    boolean existsByExemplaireIdAndDateReservationAfter(Integer exemplaireId, LocalDate date);
}
