package model;

public class JourOuvrable {
    private Long id;
    private String jourSemaine;

    public JourOuvrable() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJourSemaine() { return jourSemaine; }
    public void setJourSemaine(String jourSemaine) { this.jourSemaine = jourSemaine; }
}
