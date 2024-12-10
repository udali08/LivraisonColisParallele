package Gestion;

public class Colis {
    private String id;
    private String statut;  // "En attente", "En transit", "Livré"

    public Colis(String id) {
        this.id = id;
        this.statut = "En attente";
    }

    public String getId() {
        return id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
