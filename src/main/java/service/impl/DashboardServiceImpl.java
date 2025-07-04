package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;


import repository.DashboardRepository;
import repository.impl.DashboardRepositoryImpl;
import service.DashboardService;
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int getTotalLivres() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Livre", Integer.class);
    }

    @Override
    public int getTotalExemplaires() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Exemplaire", Integer.class);
    }

    @Override
    public int getTotalAdherents() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Adherent", Integer.class);
    }

    @Override
    public int getTotalPrets() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Pret", Integer.class);
    }

    @Override
    public int getTotalProlongements() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Prolongement", Integer.class);
    }

    @Override
    public int getTotalReservations() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Reservation", Integer.class);
    }

    @Override
    public int getTotalPenalites() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Penalite", Integer.class);
    }

    @Override
    public int getTotalJoursFeries() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM JourFerie", Integer.class);
    }

    @Override
    public int getPretToday() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Pret WHERE date_pret = CURRENT_DATE", Integer.class);
    }

    @Override
    public int getPretEnRetard() {
        String sql = "SELECT COUNT(*) FROM Pret WHERE date_retour IS NULL AND DATEDIFF(CURRENT_DATE, date_pret) > 14";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
