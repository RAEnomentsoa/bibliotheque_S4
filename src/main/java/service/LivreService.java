package service;

import model.Adherent;
import model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreService {
    List<Livre> findAll();
    Optional<Livre> findById(int id);
    Livre save(Livre livre);
    void deleteById(Long id);
    List<Livre> livresAvecExemplairesLibres();
    public boolean reserverLivrePourAdherent(Integer livreId, Adherent adherent);

}
