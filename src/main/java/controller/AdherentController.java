package controller;

import model.Adherent;
import service.AbonnementService;
import service.AdherentService;
import service.ReservationService;
import service.TypeAdherentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adherent")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private TypeAdherentService typeAdherentService;

     @Autowired
    private ReservationService reservationService;

    @Autowired
    private AbonnementService abonnementService;

@GetMapping("/ShowInscription")
public String showInscriptionForm(Model model) {
    model.addAttribute("adherent", new Adherent());
    model.addAttribute("types", typeAdherentService.findAll());
    return "adherent/inscription";
}
    @PostMapping("/inscription")
    public String register(@ModelAttribute("adherent") Adherent adherent, Model model) {
        adherentService.save(adherent);
        model.addAttribute("message", "Inscription réussie !");
        return "redirect:/adherent/login";
    }

    @GetMapping("/ShowloginAdherent")
    public String showLoginForm(Model model) {
        model.addAttribute("adherent", new Adherent());
        return "adherent/login";
    }

    @PostMapping("/loginAdherent")
    public String login(@ModelAttribute("adherent") Adherent adherent,
                        HttpSession session,
                        Model model) {

        Adherent a = adherentService.findByEmailAndPassword(adherent.getEmail(), adherent.getMotDePasse());

        if (a != null) {
            session.setAttribute("adherentConnecte", a);
            return "redirect:/adherent/livres";
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect !");
            return "adherent/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/adherent/login";
    }
}
