public class Porte {
    private boolean ouverte;

    public Porte() {
        this.ouverte = false;
    }

    public boolean isOuverte() {
        return ouverte;
    }

    public void ouvrir() {
        this.ouverte = true;
    }

    public void fermer() {
        this.ouverte = false;
    }
}
