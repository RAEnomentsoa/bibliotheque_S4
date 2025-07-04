package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Bibliothecaire;
import org.springframework.stereotype.Repository;
import repository.BibliothecaireRepository;
import repository.DashboardRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private int count(String entityName) {
        String jpql = "SELECT COUNT(e) FROM " + entityName + " e";
        return ((Number) entityManager.createQuery(jpql).getSingleResult()).intValue();
    }

    @Override public int countLivres() { return count("Livre"); }
    @Override public int countExemplaires() { return count("Exemplaire"); }
    @Override public int countAdherents() { return count("Adherent"); }
    @Override public int countPrets() { return count("Pret"); }
    @Override public int countProlongements() { return count("Prolongement"); }
    @Override public int countReservations() { return count("Reservation"); }
    @Override public int countPenalites() { return count("Penalite"); }
    @Override public int countJoursFeries() { return count("JourFerie"); }
}
