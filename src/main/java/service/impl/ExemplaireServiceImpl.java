package service.impl;

import model.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ExemplaireRepository;
import service.ExemplaireService;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Override
    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    @Override
    public Optional<Exemplaire> findById(Long id) {
        return exemplaireRepository.findById(id);
    }

    @Override
    public Exemplaire save(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    @Override
    public void deleteById(Long id) {
        exemplaireRepository.deleteById(id);
    }
        @Override
    public List<Exemplaire> findExemplairesDisponiblesByLivreId(Integer livreId) {
        return exemplaireRepository.findExemplairesDisponiblesByLivreId(livreId);
    }

}
