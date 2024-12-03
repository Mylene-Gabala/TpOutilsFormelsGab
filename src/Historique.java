import java.util.ArrayList;
import java.util.List;

public class Historique {
    private List<String> evenements;

    public Historique() {
        evenements = new ArrayList<>();
    }

    public void ajouterEvenements(String evenement) {
        evenements.add(evenement);
    }

    public List<String> getEvenements() {
        return evenements;
    }
}

