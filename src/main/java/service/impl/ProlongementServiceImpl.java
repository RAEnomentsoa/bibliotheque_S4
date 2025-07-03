package service.impl;

import model.Prolongement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProlongementRepository;
import service.ProlongementService;

import java.util.List;
import java.util.Optional;

@Service
public class ProlongementServiceImpl implements ProlongementService {

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Override
    public List<Prolongement> findAll() {
        return prolongementRepository.findAll();
    }

    @Override
    public Optional<Prolongement> findById(Long id) {
        return prolongementRepository.findById(id);
    }

    @Override
    public Prolongement save(Prolongement prolongement) {
        return prolongementRepository.save(prolongement);
    }

    @Override
    public void deleteById(Long id) {
        prolongementRepository.deleteById(id);
    }
}
