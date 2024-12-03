public class Carte {
    private String id;
    private boolean valide;

    public Carte(String id, boolean valide) {
        this.id = id;
        this.valide = valide;
    }

    public String getId() {
        return id;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public void desactiver() {
        this.valide = false;
    }

    public void reactiver() {
        this.valide = true;
    }
}


