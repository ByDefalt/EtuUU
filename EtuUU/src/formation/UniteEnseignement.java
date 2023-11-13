package formation;

// A DEFINIR
public class UniteEnseignement {

    private final String nomUE;
    private String nomEnseignant;
    private int nbPlaces;

    public UniteEnseignement(String nomUE, String nomEnseignant, int nbPlaces) {
        this.nomUE = nomUE;
        this.nomEnseignant = nomEnseignant;
        this.nbPlaces = nbPlaces;
    }
    public UniteEnseignement(String nomUE, String nomEnseignant) {
        this.nomUE = nomUE;
        this.nomEnseignant = nomEnseignant;
    }
    
    public String getNomUE() {
        return nomUE;
    }
    public String getNomEnseignant() {
        return nomEnseignant;
    }
    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }
    public int getNbPlaces() {
        return nbPlaces;
    }
    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

}
