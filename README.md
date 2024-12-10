---

## Synchronisation et choix de conception

### Gestion de la concurrence
1. **Accès concurrent à la liste des colis**  
   Un **sémaphore** garantit qu'une seule opération (ajout ou modification) peut accéder à la liste des colis à la fois. Cela prévient les conflits d'accès lorsque plusieurs threads tentent de modifier la liste simultanément.

2. **Thread-safe delivery**  
   Les livraisons sont effectuées par des threads distincts (`LivraisonThread`). Chaque thread utilise les mécanismes de synchronisation pour modifier en toute sécurité le statut des colis.

### Sémaphores
Le `Semaphore` de Java est utilisé pour assurer la synchronisation.  
- Lorsqu'un thread accède à la liste des colis, il doit acquérir un **jeton**. Cela bloque les autres threads jusqu'à ce que le jeton soit libéré.
- Ce choix est justifié par sa simplicité et son efficacité dans des scénarios où plusieurs threads accèdent simultanément à une ressource critique.

### Interface utilisateur : Swing avec JTable
- La bibliothèque `Swing` est utilisée pour son extensibilité et sa compatibilité avec les projets éducatifs.
- Les données sont affichées dans un `JTable`, avec un modèle personnalisé (`ColisTableModel`) pour un affichage dynamique.
- **Filtrage des données** : Une liste déroulante permet de filtrer les colis par statut, offrant une expérience utilisateur améliorée.

---

## Fonctionnement

### Étapes

1. Lancez l'application via la classe `Main`.
2. Utilisez le bouton **"Ajouter Colis"** pour ajouter un colis en saisissant son ID.
3. Chaque colis passe automatiquement par les étapes : **En attente**, **En transit**, puis **Livré**.
4. Utilisez le filtre pour visualiser uniquement les colis d'un statut particulier.

---

## Limites connues et améliorations possibles

### Limites

1. Les colis ne disposent pas d'une traçabilité détaillée.
2. La gestion des erreurs (ex. : thread interrompu) peut être enrichie.

### Améliorations possibles

1. Implémenter un système de suivi plus détaillé des étapes de livraison.
2. Ajouter une option pour annuler une livraison en cours.
3. Remplacer `Swing` par **JavaFX** pour une interface plus moderne.

---

## Exécution

### Prérequis

- **JDK 8 ou supérieur**.
- Un IDE (Eclipse, IntelliJ IDEA, etc.).

### Instructions

1. Compilez les fichiers Java.
2. Exécutez la classe `main.Main`.

---
