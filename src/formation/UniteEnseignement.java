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

    public int hashCode() {
        return Objects.hash(this.nomUE, this.nomEnseignant, this.nbParticipant);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UniteEnseignement other = (UniteEnseignement) obj;
        if (nomUE == null) {
            if (other.nomUE != null)
                return false;
        } else if (!nomUE.equals(other.nomUE))
            return false;
        return true;
    }
}
