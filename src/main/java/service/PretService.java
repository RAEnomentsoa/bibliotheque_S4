package service;

import model.Pret;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PretService {
    List<Pret> findAll();
    Optional<Pret> findById(int id);
    Pret save(Pret pret);
    void deleteById(Long id);
public List<Pret> findPretsByAdherentId(int adherentId);
public boolean retournerPret(int pretId,int adherentId, int exemplaireId, LocalDate dateRetour);
}
