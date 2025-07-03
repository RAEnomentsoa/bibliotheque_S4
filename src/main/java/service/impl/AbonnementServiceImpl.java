package service.impl;

import model.Abonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AbonnementRepository;
import service.AbonnementService;

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
}
