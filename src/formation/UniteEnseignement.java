package formation;

import java.util.Objects;

public class UniteEnseignement {

    private final String nomUE;
    private String nomEnseignant;
    private int nbParticipant = 0;
    private int nbPlacesMax = 0;
    private boolean Optionnel;

    public UniteEnseignement(String nomUE, String nomEnseignant) {
        if(nomUE==null || nomEnseignant==null){
            throw new IllegalArgumentException("argument non valide");
        }
        this.nomUE = nomUE;
        this.nomEnseignant = nomEnseignant;
    }

    public boolean getOptionnel() {
        return Optionnel;
    }
    public void setOptionnel(boolean Optionnel) {
        this.Optionnel=Optionnel;
    }

    public String getNomUE() {
        return nomUE;
    }

    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    public void setNbPlacesMax(int nbPlacesMax) {
        if (this.Optionnel) {
            this.nbPlacesMax = nbPlacesMax;
        }
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public int getnbParticipant() {
        return nbParticipant;
    }

    public void setnbParticipant() {
        if (this.Optionnel) {
            this.nbParticipant += 1;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomUE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteEnseignement that = (UniteEnseignement) o;
        return Objects.equals(nomUE, that.nomUE);
    }

}
