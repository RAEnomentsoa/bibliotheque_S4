package controller;

import model.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AdherentService;

@Controller
@RequestMapping("/Adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping("/showInscription")
    public String showInscriptionForm() {
        return "inscriptionAdherents";
                
    }

    @PostMapping("/inscription")
    public String inscrireAdherent(@RequestParam("nom") String nom,
                                   @RequestParam("email") String email,
                                   @RequestParam("mot_de_passe") String motDePasse,
                                   @RequestParam("date_naissance") String dateNaissance,
                                   @RequestParam("type_id") int typeId,
                                   Model model) {
        boolean success = adherentService.inscrire(nom, email, motDePasse, dateNaissance, typeId);
        if (success) {
            model.addAttribute("message", "Inscription réussie !");
            return "redirect:/"; // or wherever you want
        } else {
            model.addAttribute("error", "Erreur lors de l'inscription (email déjà utilisé ?)");
            return "inscription";
        }
    }
}