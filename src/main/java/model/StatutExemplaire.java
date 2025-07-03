package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "StatutExemplaire")
public class StatutExemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "etat_id", nullable = false)
    private EtatExemplaire etatExemplaire;

    @ManyToOne
    @JoinColumn(name = "bibliothecaire_id", nullable = false)
    private Bibliothecaire bibliothecaire;

    @Column(name = "date_changement", nullable = false)
    private LocalDate dateChangement;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
    public EtatExemplaire getEtatExemplaire() { return etatExemplaire; }
    public void setEtatExemplaire(EtatExemplaire etatExemplaire) { this.etatExemplaire = etatExemplaire; }
    public Bibliothecaire getBibliothecaire() { return bibliothecaire; }
    public void setBibliothecaire(Bibliothecaire bibliothecaire) { this.bibliothecaire = bibliothecaire; }
    public LocalDate getDateChangement() { return dateChangement; }
    public void setDateChangement(LocalDate dateChangement) { this.dateChangement = dateChangement; }
}
