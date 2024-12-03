public class Transition {
    private Etat etatInitial;
    private String condition;
    private Etat etatFinal;

    public Transition(Etat etatInitial, String condition, Etat etatFinal) {
        this.etatInitial = etatInitial;
        this.condition = condition;
        this.etatFinal = etatFinal;
    }

    public Etat getEtatInitial() {
        return etatInitial;
    }

    public String getCondition() {
        return condition;
    }

    public Etat getEtatFinal() {
        return etatFinal;
    }
}
