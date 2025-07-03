package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TypeAdherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private Integer quotaExemplaire;

    @Column(nullable = false)
    private Integer dureePret;

    @Column(nullable = false)
    private Integer dureeProlongement;

    @Column(nullable = false)
    private Integer dureePenalite;

    @Column(nullable = false)
    private Integer quotaReservation;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public Integer getQuotaExemplaire() { return quotaExemplaire; }
    public void setQuotaExemplaire(Integer quotaExemplaire) { this.quotaExemplaire = quotaExemplaire; }
    public Integer getDureePret() { return dureePret; }
    public void setDureePret(Integer dureePret) { this.dureePret = dureePret; }
    public Integer getDureeProlongement() { return dureeProlongement; }
    public void setDureeProlongement(Integer dureeProlongement) { this.dureeProlongement = dureeProlongement; }
    public Integer getDureePenalite() { return dureePenalite; }
    public void setDureePenalite(Integer dureePenalite) { this.dureePenalite = dureePenalite; }
    public Integer getQuotaReservation() { return quotaReservation; }
    public void setQuotaReservation(Integer quotaReservation) { this.quotaReservation = quotaReservation; }
}
