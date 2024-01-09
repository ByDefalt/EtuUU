package formation;

import java.util.Objects;

public class UniteEnseignement {

    private final String nomUE;
    private String nomEnseignant;
    private int nbParticipant = 0;
    private int nbPlacesMax = 0;
    private boolean Optionnel;

    /**
     * Constructeur de la classe Message.
     * 
     * @param nomUE         Le nom de l'Unité d'Enseignement
     * @param nomEnseignant Le nom de l'enseignat de l'Unité d'Enseignement
     */
    public UniteEnseignement(String nomUE, String nomEnseignant) {
        if (nomUE == null || nomEnseignant == null) {
            throw new IllegalArgumentException("argument non valide");
        }
        this.nomUE = nomUE;
        this.nomEnseignant = nomEnseignant;
    }

    /** Retoune l'intance optionel
     * @return
     *         <ul>
     *         <li>True si le l'UE est optionel</li>
     *         <li>False sinon</li>
     *         </ul>
     */
    public boolean getOptionnel() {
        return Optionnel;
    }

    /** Permet de definir si une UE est optionel ou non
     * @param Optionnel variable boolean qui permet de mettre l'UE en optionel
     */
    public void setOptionnel(boolean Optionnel) {
        this.Optionnel = Optionnel;
    }

    /** Retoune le nom de l'UE
     * @return Le nom de l'UE
     */
    public String getNomUE() {
        return nomUE;
    }

    /** Retourne le nombre de place maximum de l'UE
     * @return Le nombre de place maximum de l'UE
     */
    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    /** Set le nombre de place maximum si l'UE est Optionel
     * @param nbPlacesMax Le nombre de place maximum de l'UE
     */
    public void setNbPlacesMax(int nbPlacesMax) {
        if (this.Optionnel) {
            this.nbPlacesMax = nbPlacesMax;
        }
    }

    /**
     * @return
     */
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UniteEnseignement that = (UniteEnseignement) o;
        return Objects.equals(nomUE, that.nomUE);
    }

}
