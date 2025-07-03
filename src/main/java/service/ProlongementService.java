package service;

import model.Prolongement;
import java.util.List;
import java.util.Optional;

public interface ProlongementService {
    List<Prolongement> findAll();
    Optional<Prolongement> findById(Long id);
    Prolongement save(Prolongement prolongement);
    void deleteById(Long id);
}
