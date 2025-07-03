package service;

import model.EtatExemplaire;
import java.util.List;
import java.util.Optional;

public interface EtatExemplaireService {
    List<EtatExemplaire> findAll();
    Optional<EtatExemplaire> findById(Long id);
    EtatExemplaire save(EtatExemplaire etatExemplaire);
    void deleteById(Long id);
}
