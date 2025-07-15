package repository;

import model.Exemplaire;
import java.util.List;
import java.util.Optional;

public interface ExemplaireRepository {
    List<Exemplaire> findAll();
    Optional<Exemplaire> findById(int id);
    Exemplaire save(Exemplaire exemplaire);
    void deleteById(Long id);
    public List<Exemplaire> findExemplairesDisponiblesByLivreId(Integer livreId);

}
