package Gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GestionnaireDeColis {
    private final List<Colis> colisList = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);  // Sémaphore pour gérer l'accès concurrent

    public void enregistrerColis(Colis colis) throws InterruptedException {
        semaphore.acquire();
        colisList.add(colis);
        semaphore.release();
    }

    public void livrerColis(Colis colis) throws InterruptedException {
        semaphore.acquire();
        colis.setStatut("En transit");
        // Simuler un délai de livraison
        Thread.sleep(2000);
        colis.setStatut("Livré");
        semaphore.release();
    }

    public List<Colis> getColisList() {
        return colisList;
    }
}
