package model;

import java.time.LocalDate;

public class Pret {
    private Long id;
    private Adherent adherent;
    private Exemplaire exemplaire;
    private LocalDate datePret;
    private LocalDate dateRetour;
    private TypePret typePret;

    public Pret() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public LocalDate getDatePret() { return datePret; }
    public void setDatePret(LocalDate datePret) { this.datePret = datePret; }

    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }

    public TypePret getTypePret() { return typePret; }
    public void setTypePret(TypePret typePret) { this.typePret = typePret; }
}
