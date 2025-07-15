package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Reservation;
import org.springframework.stereotype.Repository;
import repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        return Optional.ofNullable(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            entityManager.persist(reservation);
        } else {
            entityManager.merge(reservation);
        }
        return reservation;
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }

        public Reservation findFirstByExemplaireId(Integer exemplaireId) {
        List<Reservation> results = entityManager.createQuery(
            "SELECT r FROM Reservation r WHERE r.exemplaire.id = :id ORDER BY r.dateReservation DESC",
            Reservation.class)
        .setParameter("id", exemplaireId)
        .setMaxResults(1)
        .getResultList();

    return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Optional<Reservation> findFirstByExemplaireIdOrderByDateReservationDesc(Integer exemplaireId) {
          TypedQuery<Reservation> query = entityManager.createQuery(
        "SELECT r FROM Reservation r WHERE r.exemplaire.id = :exemplaireId ORDER BY r.dateReservation DESC",
        Reservation.class
    );
    query.setParameter("exemplaireId", exemplaireId);
    query.setMaxResults(1);

    List<Reservation> resultList = query.getResultList();
    return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
public boolean existsByExemplaireIdAndDateReservationAfter(Integer exemplaireId, java.time.LocalDate date) {
    Long count = entityManager.createQuery(
        "SELECT COUNT(r) FROM Reservation r WHERE r.exemplaire.id = :exemplaireId AND r.dateReservation > :date",
        Long.class)
        .setParameter("exemplaireId", exemplaireId)
        .setParameter("date", date)
        .getSingleResult();
    return count > 0;
}
}
