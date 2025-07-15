package service.impl;

import model.Penalite;
import model.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PretRepository;
import service.PretService;
import service.StatutExemplaireService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PretServiceImpl implements PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private StatutExemplaireService statutExemplaireService;

    @Autowired
    private repository.JourFerieRepository jourFerieRepository;

    @Autowired
    private repository.PenaliteRepository penaliteRepository;

    @Override
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    @Override
    public Optional<Pret> findById(int id) {
        return pretRepository.findById(id);
    }

    @Override
    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    @Override
    public void deleteById(Long id) {
        pretRepository.deleteById(id);
    }
    @Override
    public List<Pret> findPretsByAdherentId(int adherentId) {
    return pretRepository.findByAdherentId(adherentId);
}

private boolean estWeekend(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
}

private boolean estJourFerie(LocalDate date) {
    return jourFerieRepository.existsByDateFerie(date);
}


@Override
 public boolean retournerPret(int pretId, int adherentId, int exemplaireId, LocalDate dateRetour){


 // Vérifier si jour ouvrable
    if (estWeekend(dateRetour) || estJourFerie(dateRetour)) {
        throw new IllegalArgumentException("Le retour ne peut être effectué que pendant les jours ouvrables.");
    }

    // Charger le prêt
    Pret pret = findById(pretId).orElseThrow(() ->
            new IllegalArgumentException("Prêt non trouvé pour l'ID : " + pretId)
    );

    LocalDate datePret = pret.getDatePret();
    int dureePret = pret.getAdherent().getTypeAdherent().getDureePret();
    LocalDate dateLimite = datePret.plusDays(dureePret);

    // Vérifier si retour dans les délais
    boolean retourValide = (!dateRetour.isBefore(datePret)) && (!dateRetour.isAfter(dateLimite));

    // Mettre à jour le prêt
    pret.setDateRetour(dateRetour);
    save(pret);

    // Si en retard, appliquer pénalité
    if (!retourValide) {
        Penalite penalite = new Penalite();
        penalite.setPret(pret);
        penaliteRepository.save(penalite);
    }

    // Mettre à jour le statut de l’exemplaire
    statutExemplaireService.mettreAJourStatut(exemplaireId, "libre");

    return true;

}
}
