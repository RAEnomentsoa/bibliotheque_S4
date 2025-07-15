package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pret_id")
    private Pret pret;


    public Prolongement() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }
}
