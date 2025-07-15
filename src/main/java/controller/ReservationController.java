package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Exemplaire;
import model.Livre;
import service.AbonnementService;
import service.AdherentService;
import service.ExemplaireService;
import service.LivreService;
import service.ReservationService;

@Controller
@RequestMapping("/adherent")
public class ReservationController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AbonnementService abonnementService;

     @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("/livres")
public String afficherLivresDisponibles(Model model) {
    //List<Livre> livres = livreService.livresAvecExemplairesLibres();
    List<Livre>  livres= livreService.findAll();
    model.addAttribute("livres", livres);
    return "adherent/livre";
}

// @GetMapping("/reserver")
// public String reserverLivre(@RequestParam("livreId") Integer livreId,
//                             HttpSession session,
//                             RedirectAttributes redirectAttributes) {
//    Adherent adherent = (Adherent) session.getAttribute("adherentConnecte");

//     if (adherent == null) {
//         redirectAttributes.addFlashAttribute("error", "Veuillez vous connecter.");
//         return "redirect:/login"; // Adapté selon votre système
//     }

//     boolean success = reservationService.reserverLivrePourAdherent(livreId, adherent);
//     if (success) {
//         redirectAttributes.addFlashAttribute("message", "Réservation effectuée avec succès.");
//     } else {
//         redirectAttributes.addFlashAttribute("error", "Réservation impossible. Conditions non remplies.");
//     }

//     return "redirect:/adherent/livres";
// }
@GetMapping("/livre/{id}/exemplaires")
public String voirExemplaires(@PathVariable("id") Integer livreId, Model model) {
    List<Exemplaire> exemplaires = exemplaireService.findExemplairesDisponiblesByLivreId(livreId);
    model.addAttribute("exemplaires", exemplaires);
    Livre livre = livreService.findById(livreId).orElse(null);
    model.addAttribute("livre", livre);
    return "adherent/exemplaires"; // JSP suivante
}

@PostMapping("/reserver")
public String reserverExemplaire(@RequestParam("exemplaireId") Integer exemplaireId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
    Adherent adherent = (Adherent) session.getAttribute("adherentConnecte");
    if (adherent == null) {
        redirectAttributes.addFlashAttribute("error", "Veuillez vous connecter.");
        return "redirect:/adherent/ShowloginAdherent";
    }

    boolean success = reservationService.reserverExemplairePourAdherent(exemplaireId, adherent);
    if (success) {
        redirectAttributes.addFlashAttribute("message", "Réservation réussie !");
    } else {
        redirectAttributes.addFlashAttribute("error", "Impossible de réserver cet exemplaire.");
    }

    return "redirect:/adherent/livres";
}

}
