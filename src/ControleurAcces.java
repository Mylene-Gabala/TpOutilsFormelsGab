import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleurAcces {
    private static Scanner scanner = new Scanner(System.in);
    private static Automate automate = new Automate();
    private static Historique historique = new Historique();
    private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static Porte porte = new Porte();

    public static void main(String[] args) {
        boolean continuer = true;
        ajouterTransitions();

        while (continuer) {
            System.out.println("\n=== Système de Contrôle d'Accès ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Vérifier une carte");
            System.out.println("3. Consulter l'historique");
            System.out.println("4. État du système");
            System.out.println("5. Désactiver une carte");
            System.out.println("6. Réactiver une carte");
            System.out.println("7. Afficher les utilisateurs");
            System.out.println("8. Afficher l'état de la porte");
            System.out.println("9. Quitter");

            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterUtilisateur();
                    break;
                case 2:
                    verifierCarte();
                    break;
                case 3:
                    consulterHistorique();
                    break;
                case 4:
                    afficherEtatSysteme();
                    break;
                case 5:
                    desactiverCarte();
                    break;
                case 6:
                    reactiverCarte();
                    break;
                case 7:
                    afficherUtilisateurs();
                    break;
                case 8:
                    afficherEtatPorte();
                    break;
                case 9:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void ajouterTransitions() {
        automate.ajouterTransition(Etat.IDENTITE_VERIFIEE, "Carte Valide", Etat.CLE_VERIFIEE);
        automate.ajouterTransition(Etat.CLE_VERIFIEE, "Cle Correcte", Etat.ACCES_PERMIS);
        automate.ajouterTransition(Etat.CLE_VERIFIEE, "Cle Incorrecte", Etat.ACCES_INTERDIT);
        automate.ajouterTransition(Etat.IDENTITE_VERIFIEE, "Carte Invalide", Etat.ACCES_INTERDIT);
        automate.ajouterTransition(Etat.IDENTITE_VERIFIEE, "Tentative d'intrusion", Etat.ALARME_ACTIVE);
    }

    private static void verifierCarte() {
        System.out.println("Entrez l'ID de la carte : ");
        String idCarte = scanner.nextLine();

        boolean carteValide = false;
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getCarte().getId().equals(idCarte) && utilisateur.getCarte().isValide()) {
                carteValide = true;
                break;
            }
        }
        if (carteValide) {
            automate.appliquerCondition("CarteValide");
            historique.ajouterEvenements("Carte valide vérifiée.");
            porte.ouvrir();
        } else {
            automate.appliquerCondition("CarteInvalide");
            historique.ajouterEvenements("Carte invalide vérifiée.");
            porte.fermer();
        }
        System.out.println("État courant : " + automate.getEtatCourant());
        System.out.println("État de la porte : " + (porte.isOuverte() ? "ouverte":"fermée "));
    }

    private static void ajouterUtilisateur() {
        System.out.println("Entrez le nom de l'utilisateur : ");
        String nom = scanner.nextLine();
        System.out.println("Entrez l'ID de la carte : ");
        String idCarte = scanner.nextLine();
        Carte carte = new Carte(idCarte, true);
        Utilisateur utilisateur = new Utilisateur(nom, carte);
        utilisateurs.add(utilisateur);
        historique.ajouterEvenements("Utilisateur ajouté : " + nom);
        System.out.println("Utilisateur " + nom + " ajouté avec la carte ID " + idCarte);
    }

    private static void consulterHistorique() {
        System.out.println("=== Historique ===");
        for (String evenement : historique.getEvenements()) {
            System.out.println(evenement);
        }
    }

    private static void afficherEtatSysteme() {
        System.out.println("État courant du système : " + automate.getEtatCourant());
    }

    private static void desactiverCarte() {
        System.out.print("Entrez l'ID de la carte à désactiver : ");
        String idCarte = scanner.nextLine();
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getCarte().getId().equals(idCarte)) {
                utilisateur.getCarte().desactiver();
                historique.ajouterEvenements("Carte désactivée : " + idCarte);
                System.out.println("Carte " + idCarte + " désactivée.");
                return;
            }
        }
        System.out.println("Carte non trouvée.");
    }

    private static void reactiverCarte() {
        System.out.print("Entrez l'ID de la carte à réactiver : ");
        String idCarte = scanner.nextLine();
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getCarte().getId().equals(idCarte)) {
                utilisateur.getCarte().reactiver();
                historique.ajouterEvenements("Carte réactivée : " + idCarte);
                System.out.println("Carte " + idCarte + " réactivée.");
                return;
            }
        }
        System.out.println("Carte non trouvée.");
    }

    private static void afficherUtilisateurs() {
        System.out.println("=== Utilisateurs ===");
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("Nom : " + utilisateur.getNom() + ", Carte ID : " + utilisateur.getCarte().getId() + ", Valide : " + utilisateur.getCarte().isValide());
        }
    }

    private static void afficherEtatPorte() {
        System.out.println("État de la porte : " + (porte.isOuverte() ? "Ouverte" : "Fermée"));
    }
}

