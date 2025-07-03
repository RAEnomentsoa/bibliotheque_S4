package controller;

import model.Bibliothecaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.BibliothecaireService;

import java.util.List;

@Controller
public class BibliothecaireListController {

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @GetMapping("/bibliothecaires/list")
    public String listBibliothecaires(Model model) {
        List<Bibliothecaire> bibliothecaires = bibliothecaireService.findAll();
        model.addAttribute("bibliothecaires", bibliothecaires);
        return "bibliothecaires/list";
    }
}
