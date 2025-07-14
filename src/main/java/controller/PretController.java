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
import service.BibliothecaireService;
import service.ReservationService;
import service.StatutExemplaireService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/prets")
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @PostMapping("/new")
    public String creerPret(
            @RequestParam("adherentId") int adherentId,
            @RequestParam("exemplaireId") int exemplaireId,
            @RequestParam(value = "typePretId", required = true) int typePretId,
            @RequestParam(value = "dateRetour", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour,
            @RequestParam(value = "datePret", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePret,
            Model model) {

        Optional<model.Adherent> adherentOpt = adherentRepository.findById(adherentId);
        Optional<model.Exemplaire> exemplaireOpt = exemplaireRepository.findById(exemplaireId);

        if (adherentOpt.isPresent() && exemplaireOpt.isPresent()) {
            model.Pret pret = new model.Pret();
            pret.setAdherent(adherentOpt.get());
            pret.setExemplaire(exemplaireOpt.get());
            pret.setDatePret(datePret);
            pret.setDateRetour(dateRetour);
            // Set typePret if needed, e.g. default to id=1
            pret.setTypePret(typePretRepository.findById(typePretId).orElse(null));

            pretRepository.save(pret);
            model.addAttribute("message", "Prêt enregistré avec succès !");
        } else {
            model.addAttribute("error", "Adhérent ou exemplaire introuvable.");
        }

        return "redirect:/bibliothecaires/exemplaires_reserves";
    }
}
