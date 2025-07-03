package repository;

import model.EtatExemplaire;
import java.util.List;
import java.util.Optional;

public interface EtatExemplaireRepository {
    List<EtatExemplaire> findAll();
    Optional<EtatExemplaire> findById(Long id);
    EtatExemplaire save(EtatExemplaire etatExemplaire);
    void deleteById(Long id);
}
