package service.impl;

import model.JourFerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JourFerieRepository;
import service.JourFerieService;

import java.util.List;
import java.util.Optional;

@Service
public class JourFerieServiceImpl implements JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @Override
    public List<JourFerie> findAll() {
        return jourFerieRepository.findAll();
    }

    @Override
    public Optional<JourFerie> findById(Long id) {
        return jourFerieRepository.findById(id);
    }

    @Override
    public JourFerie save(JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }

    @Override
    public void deleteById(Long id) {
        jourFerieRepository.deleteById(id);
    }
}
