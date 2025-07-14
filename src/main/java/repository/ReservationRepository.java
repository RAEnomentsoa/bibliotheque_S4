package repository;

import model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);
    void deleteById(Long id);
    Reservation findFirstByExemplaireId(Integer exemplaireId);
    Optional<Reservation> findFirstByExemplaireIdOrderByDateReservationDesc(Integer exemplaireId);
}
