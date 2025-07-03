package service.impl;

import model.EtatExemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EtatExemplaireRepository;
import service.EtatExemplaireService;

import java.util.List;
import java.util.Optional;

@Service
public class EtatExemplaireServiceImpl implements EtatExemplaireService {

    @Autowired
    private EtatExemplaireRepository etatExemplaireRepository;

    @Override
    public List<EtatExemplaire> findAll() {
        return etatExemplaireRepository.findAll();
    }

    @Override
    public Optional<EtatExemplaire> findById(Long id) {
        return etatExemplaireRepository.findById(id);
    }

    @Override
    public EtatExemplaire save(EtatExemplaire etatExemplaire) {
        return etatExemplaireRepository.save(etatExemplaire);
    }

    @Override
    public void deleteById(Long id) {
        etatExemplaireRepository.deleteById(id);
    }
}
