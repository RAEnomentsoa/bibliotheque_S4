package controller;

import model.Adherent;
import model.Bibliothecaire;
import model.Pret;
import model.Prolongement;
import model.StatutExemplaire;
import repository.AdherentRepository;
import repository.ExemplaireRepository;
import repository.PretRepository;
import repository.ProlongementRepository;
import repository.ReservationRepository;
import repository.TypePretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import service.BibliothecaireService;
import service.ReservationService;
import service.StatutExemplaireService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/adherent")
public class PretController {

    @Autowired

    private service.PretService pretService;

    @Autowired
    private StatutExemplaireService statutExemplaireService;

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private service.AbonnementService abonnementService;

@GetMapping("/mes-prets")
public String afficherLivresEmpruntes(HttpSession session, Model model) {
    Adherent adherent = (Adherent) session.getAttribute("adherentConnecte");
    if (adherent == null) {
        return "redirect:/adherent/ShowloginAdherent";
    }
    List<model.Pret> prets = pretService.findPretsByAdherentId(adherent.getId());
    model.addAttribute("prets", prets);
    return "adherent/mes_prets"; // Crée ce JSP pour afficher la liste
}

@PostMapping("/prets/retourner")
@Transactional
public String retournerPret(
        @RequestParam("pretId") Integer pretId,
        @RequestParam("exemplaireId") Integer exemplaireId,
        @RequestParam("adherentId") Integer adherentId,
        @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour) {

    
       boolean retour= pretService.retournerPret(pretId, adherentId, exemplaireId, dateRetour);
    if (retour == true) {
        //test if retour valide
        System.out.println("retour acheived");
    return "redirect:/adherent/mes_prets" + adherentId;
    
    }
   return "redirect:/adherent/mes_prets" + adherentId;
}

 @PostMapping("/ajouter/prolongement")
    public String enregistrerProlongement(
            @RequestParam("pretId") Integer pretId,
             @RequestParam("ProlongementDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate Dateprolongement,
             org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {

        Optional<Pret> pretOpt = pretRepository.findById(pretId);
        if (pretOpt.isPresent()) {
            Pret pret = pretOpt.get();

                 // 1. Vérifier si l'exemplaire n'est pas réservé après ce prêt
        boolean isReserved = reservationRepository.existsByExemplaireIdAndDateReservationAfter(
                pret.getExemplaire().getId(), pret.getDatePret());
        if (isReserved) {
            redirectAttributes.addFlashAttribute("error", "Impossible de prolonger : le livre est réservé après votre prêt.");
            return "redirect:/adherent/mes-prets";
        }

        // 2. Vérifier si l'abonnement est actif et payé
        Adherent adherent = pret.getAdherent();
        boolean abonnementActif = abonnementService.aUnAbonnementActif(adherent.getId());
        if (!abonnementActif) {
            redirectAttributes.addFlashAttribute("error", "Votre abonnement n'est pas actif ou n'est pas payé.");
            return "redirect:/adherent/mes-prets";
        }

            Prolongement prolongement = new Prolongement();
            pret.setDateRetour(null);
            prolongement.setPret(pret);
            prolongementRepository.save(prolongement);
            pretRepository.save(pret);
        }
        return "redirect:/adherent/mes-prets";
    }

}
