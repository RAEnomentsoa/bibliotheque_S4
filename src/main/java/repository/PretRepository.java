package repository;

import model.Pret;
import java.util.List;
import java.util.Optional;

public interface PretRepository {
    List<Pret> findAll();
    Optional<Pret> findById(int id);
    Pret save(Pret pret);
    void deleteById(Long id);
    List<Pret> findByAdherentId(int adherentId);
}
