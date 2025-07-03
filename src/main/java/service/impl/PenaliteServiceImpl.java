package service.impl;

import model.Penalite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PenaliteRepository;
import service.PenaliteService;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteServiceImpl implements PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    @Override
    public List<Penalite> findAll() {
        return penaliteRepository.findAll();
    }

    @Override
    public Optional<Penalite> findById(Long id) {
        return penaliteRepository.findById(id);
    }

    @Override
    public Penalite save(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

    @Override
    public void deleteById(Long id) {
        penaliteRepository.deleteById(id);
    }
}
