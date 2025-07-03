package service;

import model.Penalite;
import java.util.List;
import java.util.Optional;

public interface PenaliteService {
    List<Penalite> findAll();
    Optional<Penalite> findById(Long id);
    Penalite save(Penalite penalite);
    void deleteById(Long id);
}
