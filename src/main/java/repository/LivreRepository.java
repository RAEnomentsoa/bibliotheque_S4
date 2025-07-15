package repository;

import model.Exemplaire;
import model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreRepository {
    List<Livre> findAll();
    Optional<Livre> findById(int id);
    Livre save(Livre livre);
    void deleteById(Long id);
    Exemplaire findExemplaireDisponible(Integer livreId);
     List<Livre> findLivresAvecExemplairesDisponibles();
     boolean isExemplaireDisponible(Integer exemplaireId);

}
