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

    /**
     * Retoune l'intance optionel
     * 
     * @return
     *         <ul>
     *         <li>True si le l'UE est optionel</li>
     *         <li>False sinon</li>
     *         </ul>
     */
    public boolean getOptionnel() {
        return Optionnel;
    }

    /**
     * Permet de definir si une UE est optionel ou non
     * 
     * @param Optionnel variable boolean qui permet de mettre l'UE en optionel
     */
    public void setOptionnel(boolean Optionnel) {
        this.Optionnel = Optionnel;
    }

    /**
     * Retoune le nom de l'UE
     * 
     * @return Le nom de l'UE
     */
    public String getNomUE() {
        return nomUE;
    }

    /**
     * Retourne le nombre de place maximum de l'UE
     * 
     * @return Le nombre de place maximum de l'UE
     */
    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    /**
     * Set le nombre de place maximum si l'UE est Optionel
     * 
     * @param nbPlacesMax Le nombre de place maximum de l'UE
     */
    public void setNbPlacesMax(int nbPlacesMax) {
        if (this.Optionnel) {
            this.nbPlacesMax = nbPlacesMax;
        }
    }

    /**
     * Retoune le nom de l'enseignant
     * 
     * @return Le nom de l'enseignant
     */
    public String getNomEnseignant() {
        return nomEnseignant;
    }

    /**
     * Set le nom de l'enseignant
     * 
     * @param nomEnseignant Le nom de l'enseignant
     */
    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    /**
     * Retoune le nombre de Etudiant inscrit à l'UE
     * 
     * @return Le nombre de Etudiant inscrit à l'UE
     */
    public int getnbParticipant() {
        return nbParticipant;
    }

    /**
     * Incrémente le nombre de participants si l'optionnel est activé.
     * Si l'optionnel n'est pas activé, le nombre de participants reste inchangé.
     * 
     * <p>
     * Exemple d'utilisation :
     * 
     * <pre>{@code
     * // Création d'une instance de la classe
     * UniteEnseignement instance = new UniteEnseignement("Java","Richard");
     * 
     * // Activation de l'optionnel
     * instance.setOptionnel(true);
     * 
     * // Incrémentation du nombre de participants
     * instance.setnbParticipant();
     * }</pre>
     * </p>
     * 
     * @see #setOptionnel(boolean)
     * @see #getnbParticipant()
     */
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
