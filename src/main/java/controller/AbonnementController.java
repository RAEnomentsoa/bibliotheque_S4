package controller;

import model.Abonnement;
import model.Adherent;
import model.Bibliothecaire;
import model.Pret;
import model.StatutExemplaire;
import repository.AdherentRepository;
import repository.ExemplaireRepository;
import repository.PretRepository;
import repository.ReservationRepository;
import repository.TypePretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import service.AbonnementService;
import service.BibliothecaireService;
import service.ReservationService;
import service.StatutExemplaireService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/bibliothecaires")
public class AbonnementController {

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @Autowired
    private StatutExemplaireService statutExemplaireService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AbonnementService abonnementService;





@GetMapping("/abonnement/new")
public String afficherFormulaireAbonnement(Model model) {
    List<Adherent> adherents = adherentRepository.findAll();
    model.addAttribute("adherents", adherents);
    model.addAttribute("abonnement", new Abonnement());
    return "bibliothecaires/Abonnement";
}

@PostMapping("/abonnement/create")
public String creerAbonnement(@ModelAttribute Abonnement abonnement) {
    abonnementService.save(abonnement);
    return "redirect:/dashboard";
}

}