package service.impl;

import model.StatutExemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.StatutExemplaireRepository;
import service.StatutExemplaireService;

import java.util.List;
import java.util.Optional;

@Service
public class StatutExemplaireServiceImpl implements StatutExemplaireService {

    @Autowired
    private StatutExemplaireRepository statutExemplaireRepository;

    @Override
    public List<StatutExemplaire> findAll() {
        return statutExemplaireRepository.findAll();
    }

    @Override
    public Optional<StatutExemplaire> findById(Long id) {
        return statutExemplaireRepository.findById(id);
    }

    @Override
    public StatutExemplaire save(StatutExemplaire statutExemplaire) {
        return statutExemplaireRepository.save(statutExemplaire);
    }

    @Override
    public void deleteById(Long id) {
        statutExemplaireRepository.deleteById(id);
    }
}
