package controller;

import model.Bibliothecaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BibliothecaireService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bibliothecaires")
public class BibliothecaireController {

    @Autowired
    private BibliothecaireService bibliothecaireService;

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
    public String showEditForm(@PathVariable Long id, Model model) {
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
}
