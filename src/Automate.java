import java.util.ArrayList;
import java.util.List;

public class Automate {
    private List<Transition> transitions;
    private Etat etatCourant;

    public Automate() {
        transitions = new ArrayList<>();
        etatCourant = Etat.IDENTITE_VERIFIEE;
    }

    public void ajouterTransition(Etat etatInitial, String condition, Etat etatFinal) {
        transitions.add(new Transition(etatInitial, condition, etatFinal));
    }

    public void appliquerCondition(String condition) {
        for (Transition transition : transitions) {
            if (transition.getEtatInitial() == etatCourant && transition.getCondition().equals(condition)) {
                etatCourant = transition.getEtatFinal();
                break;
            }
        }
    }

    public Etat getEtatCourant() {
        return etatCourant;
    }
}
