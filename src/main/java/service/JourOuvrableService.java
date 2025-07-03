package service;

import model.JourOuvrable;
import java.util.List;
import java.util.Optional;

public interface JourOuvrableService {
    List<JourOuvrable> findAll();
    Optional<JourOuvrable> findById(Long id);
    JourOuvrable save(JourOuvrable jourOuvrable);
    void deleteById(Long id);
}
