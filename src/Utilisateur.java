public class Utilisateur {
    private String nom;
    private Carte carte;

    public Utilisateur(String nom, Carte carte) {
        this.nom = nom;
        this.carte = carte;
    }

    public String getNom() {
        return nom;
    }

    public Carte getCarte() {
        return carte;
    }
}
