package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.DashboardService;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("livres", dashboardService.getTotalLivres());
        model.addAttribute("exemplaires", dashboardService.getTotalExemplaires());
        model.addAttribute("adherents", dashboardService.getTotalAdherents());
        model.addAttribute("prets", dashboardService.getTotalPrets());
        model.addAttribute("prolongements", dashboardService.getTotalProlongements());
        model.addAttribute("reservations", dashboardService.getTotalReservations());
        model.addAttribute("penalites", dashboardService.getTotalPenalites());
        model.addAttribute("joursFeries", dashboardService.getTotalJoursFeries());

        return "dashboard";
    }
}
