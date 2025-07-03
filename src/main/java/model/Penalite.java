package model;

public class Penalite {
    private Long id;
    private Pret pret;

    public Penalite() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }
}
