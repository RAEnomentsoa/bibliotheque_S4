package model;

import java.time.LocalDate;

public class JourFerie {
    private Long id;
    private LocalDate dateFerie;
    private String evenement;

    public JourFerie() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateFerie() { return dateFerie; }
    public void setDateFerie(LocalDate dateFerie) { this.dateFerie = dateFerie; }

    public String getEvenement() { return evenement; }
    public void setEvenement(String evenement) { this.evenement = evenement; }
}
