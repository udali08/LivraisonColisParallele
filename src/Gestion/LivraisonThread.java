package Gestion;

public class LivraisonThread extends Thread {
    private final GestionnaireDeColis gestionnaire;
    private final Colis colis;

    public LivraisonThread(GestionnaireDeColis gestionnaire, Colis colis) {
        this.gestionnaire = gestionnaire;
        this.colis = colis;
    }

    @Override
    public void run() {
        try {
            gestionnaire.enregistrerColis(colis);
            gestionnaire.livrerColis(colis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
