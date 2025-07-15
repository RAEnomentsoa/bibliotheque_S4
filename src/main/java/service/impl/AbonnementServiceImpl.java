package service.impl;

import model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AbonnementRepository;
import service.AbonnementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AbonnementServiceImpl implements AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Override
    public List<Abonnement> findAll() {
        return abonnementRepository.findAll();
    }

    @Override
    public Optional<Abonnement> findById(Long id) {
        return abonnementRepository.findById(id);
    }

    @Override
    public Abonnement save(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public void deleteById(Long id) {
        abonnementRepository.deleteById(id);
    }
    @Override
public boolean aUnAbonnementActif(Integer adherentId) {
    Abonnement abonnement = abonnementRepository.findActifByAdherentId(adherentId);
    if (abonnement == null) return false;
    LocalDate today = LocalDate.now();
    return !abonnement.getDateDebut().isAfter(today) && !abonnement.getDateFin().isBefore(today);
}

}
