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
@RequestMapping("/adherent")
public class PretController {

    @Autowired

    private service.PretService pretService;

    @Autowired
    private StatutExemplaireService statutExemplaireService;

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

}
