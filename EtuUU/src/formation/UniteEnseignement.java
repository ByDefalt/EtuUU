package formation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// A DEFINIR
public class UniteEnseignement {

    private static final Set<UniteEnseignement> UniteEseignement = new HashSet<>();

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

    @Override
    public int hashCode() {
        return Objects.hash(this.nomUE, this.nomEnseignant, this.nbPlaces);
    }

    @Override
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
        if (nomEnseignant == null) {
            if (other.nomEnseignant != null)
                return false;
        } else if (!nomEnseignant.equals(other.nomEnseignant))
            return false;
        if (nbPlaces != other.nbPlaces)
            return false;
        return true;
    }

}
