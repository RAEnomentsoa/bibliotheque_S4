package controller;

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
public class BibliothecaireController {

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






    @GetMapping
    public String listBibliothecaires(Model model) {
        List<Bibliothecaire> bibliothecaires = bibliothecaireService.findAll();
        model.addAttribute("bibliothecaires", bibliothecaires);
        return "bibliothecaires/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bibliothecaire", new Bibliothecaire());
        return "bibliothecaires/add";
    }

    @PostMapping
    public String addBibliothecaire(@ModelAttribute Bibliothecaire bibliothecaire) {
        bibliothecaireService.save(bibliothecaire);
        return "redirect:/bibliothecaires";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Bibliothecaire> bibliothecaire = bibliothecaireService.findById(id);
        if (bibliothecaire.isPresent()) {
            model.addAttribute("bibliothecaire", bibliothecaire.get());
            return "bibliothecaires/edit";
        } else {
            return "redirect:/bibliothecaires";
        }
    }

    @PostMapping("/update")
    public String updateBibliothecaire(@ModelAttribute Bibliothecaire bibliothecaire) {
        bibliothecaireService.save(bibliothecaire);
        return "redirect:/bibliothecaires";
    }

    @GetMapping("/delete/{id}")
    public String deleteBibliothecaire(@PathVariable Long id) {
        bibliothecaireService.deleteById(id);
        return "redirect:/bibliothecaires";
    }

// @GetMapping("/exemplaires-reserves")
// public String voirExemplairesReserves(Model model) {
//     List<StatutExemplaire> statuts = statutExemplaireService.getExemplairesEnCoursDeReservation();
//     List<StatutExemplaire> exemplairesReserves = statutExemplaireService.getExemplairesReserver();

//     // Map pour les deux listes
//     Map<Integer, String> nomsAdherents = new HashMap<>();

//     // Parcours des statuts
//     for (StatutExemplaire statut : statuts) {
//         Integer exemplaireId = statut.getExemplaire().getId();
//         nomsAdherents.put(exemplaireId, reservationService.getNomAdherentParExemplaireId(exemplaireId));
//     }

//     // Parcours des exemplaires réservés
//     for (StatutExemplaire statut : exemplairesReserves) {
//         Integer exemplaireId = statut.getExemplaire().getId();
//         nomsAdherents.putIfAbsent(exemplaireId, reservationService.getNomAdherentParExemplaireId(exemplaireId));
//     }

//     model.addAttribute("statuts", statuts);
//     model.addAttribute("Exemplairereservers", exemplairesReserves);
//     model.addAttribute("nomsAdherents", nomsAdherents);
//     return "bibliothecaires/exemplaires_reserves";
// }

@GetMapping("/exemplaires-reserves")
public String voirExemplairesReserves(Model model) {
    List<StatutExemplaire> statuts = statutExemplaireService.getExemplairesEnCoursDeReservation();
    List<StatutExemplaire> exemplairesReserves = statutExemplaireService.getExemplairesReserver();

    Map<Integer, String> nomsAdherents = new HashMap<>();
    Map<Integer, Adherent> adherentsParExemplaire = new HashMap<>();

    for (StatutExemplaire statut : statuts) {
        Integer exId = statut.getExemplaire().getId();
        reservationRepository.findFirstByExemplaireIdOrderByDateReservationDesc(exId).ifPresent(res -> {
            Adherent adh = res.getAdherent();
            nomsAdherents.put(exId, adh.getNom());
            adherentsParExemplaire.put(exId, adh);
        });
    }

    for (StatutExemplaire statut : exemplairesReserves) {
        Integer exId = statut.getExemplaire().getId();
        reservationRepository.findFirstByExemplaireIdOrderByDateReservationDesc(exId).ifPresent(res -> {
            Adherent adh = res.getAdherent();
            nomsAdherents.putIfAbsent(exId, adh.getNom());
            adherentsParExemplaire.putIfAbsent(exId, adh);
        });
    }

    model.addAttribute("statuts", statuts);
    model.addAttribute("Exemplairereservers", exemplairesReserves);
    model.addAttribute("nomsAdherents", nomsAdherents);
    model.addAttribute("adherentsParExemplaire", adherentsParExemplaire);

    return "bibliothecaires/exemplaires_reserves";
}


@PostMapping("/confirmerReservation")
public String reserverExemplaire(@RequestParam("exemplaireId") Integer id) {
    statutExemplaireService.mettreAJourStatut(id, "reservé");
    return "redirect:/bibliothecaires/exemplaires-reserves";
}

@PostMapping("/preter")
public String preterExemplaire(@RequestParam("exemplaireId") Integer id) {
    statutExemplaireService.mettreAJourStatut(id, "prêté");
    return "redirect:/bibliothecaires/exemplaires-reserves";
}

// gestion de pret
@GetMapping("/prets/nouveau")
public String afficherFormulairePret(
        @RequestParam("adherentId") Integer adherentId,
        @RequestParam("exemplaireId") Integer exemplaireId,
        Model model) {
    
    model.addAttribute("adherentId", adherentId);
    model.addAttribute("exemplaireId", exemplaireId);
    model.addAttribute("typesPret", typePretRepository.findAll());
    model.addAttribute("now", LocalDate.now());
    System.out.println("Types de prêt : " + typePretRepository.findAll().size());
    
    return "bibliothecaires/formulaire_pret";
}


@PostMapping("/prets/ajouter")
@Transactional
public String enregistrerPret(
   @RequestParam("adherentId") Integer adherentId,
        @RequestParam("exemplaireId") Integer exemplaireId,
        @RequestParam("datePret") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePret,
        @RequestParam("typeId") Integer typePretId) {

    Pret pret = new Pret();
    pret.setAdherent(adherentRepository.findById(adherentId)
            .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable")));
    pret.setExemplaire(exemplaireRepository.findById(exemplaireId)
            .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable")));
    pret.setDatePret(datePret);
    pret.setTypePret(typePretRepository.findById(typePretId)
            .orElseThrow(() -> new IllegalArgumentException("Type de prêt introuvable")));

    pretRepository.save(pret);

    // Mettre à jour le statut de l'exemplaire
    statutExemplaireService.mettreAJourStatut(exemplaireId, "prêté");

    // Redirection vers la liste ou confirmation
    return "redirect:/bibliothecaires/exemplaires-reserves";
}


   
}
