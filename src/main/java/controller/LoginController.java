package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import service.BibliothecaireService;
import model.Bibliothecaire; // Add this import, adjust the package if needed

@Controller
public class LoginController {
    @Autowired
    private BibliothecaireService bibliothecaireService;

    @PostMapping("/login")
    public String login(@RequestParam("nom") String nom,
                        @RequestParam("mot_de_passe") String mot_de_passe,
                        HttpSession session,
                        Model model) {
        Bibliothecaire b = bibliothecaireService.login(nom, mot_de_passe);
        if (b != null) {
            session.setAttribute("bibliothecaire", b);
            return "redirect:/dashboard";
        } else {
            
            return "index";
        }
    }
}