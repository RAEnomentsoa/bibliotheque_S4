package service;

public interface DashboardService {
    int getTotalLivres();
    int getTotalExemplaires();
    int getTotalAdherents();
    int getTotalPrets();           // added
    int getTotalProlongements();  // added
    int getTotalReservations();
    int getTotalPenalites();
    int getTotalJoursFeries();    // added
    int getPretToday();
    int getPretEnRetard();
}
