package service.impl;

import model.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PretRepository;
import service.PretService;

import java.util.List;
import java.util.Optional;

@Service
public class PretServiceImpl implements PretService {

    @Autowired
    private PretRepository pretRepository;

    @Override
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    @Override
    public Optional<Pret> findById(int id) {
        return pretRepository.findById(id);
    }

    @Override
    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    @Override
    public void deleteById(Long id) {
        pretRepository.deleteById(id);
    }
}
