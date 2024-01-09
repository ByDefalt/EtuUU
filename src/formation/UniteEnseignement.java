package formation;

import java.util.Objects;

/**
 * Représente une Unité d'Enseignement (UE) dans le contexte d'une formation.
 * Chaque UE a un nom, un enseignant associé, un nombre de participants, un nombre de places maximales
 * et une optionnalité déterminant si l'UE est facultative ou non.
 */
public class UniteEnseignement {

    /**
     * Le nom de l'Unité d'Enseignement (UE).
     */
    private final String nomUE;

    /**
     * Le nom de l'enseignant de l'Unité d'Enseignement.
     */
    private String nomEnseignant;

    /**
     * Le nombre d'étudiants actuellement inscrits à l'UE.
     */
    private int nbParticipant = 0;

    /**
     * Le nombre maximum de places disponibles pour l'UE.
     * Cette valeur est utilisée uniquement si l'UE est facultative (optionnel est true).
     */
    private int nbPlacesMax = 0;

    /**
     * Indique si l'UE est facultative ou non.
     * Si true, l'UE est facultative, sinon elle est obligatoire.
     */
    private boolean Optionnel;

    /**
     * Constructeur de la classe UniteEnseignement.
     *
     * @param nomUE         Le nom de l'Unité d'Enseignement.
     * @param nomEnseignant Le nom de l'enseignant de l'Unité d'Enseignement.
     */
    public UniteEnseignement(String nomUE, String nomEnseignant) {
        if (nomUE == null || nomEnseignant == null) {
            throw new IllegalArgumentException("Les arguments ne peuvent pas être nuls");
        }
        this.nomUE = nomUE;
        this.nomEnseignant = nomEnseignant;
    }

    // Getters et Setters

    /**
     * Retourne l'optionnalité de l'UE.
     *
     * @return True si l'UE est facultative, False sinon.
     */
    public boolean getOptionnel() {
        return Optionnel;
    }

    /**
     * Permet de définir si une UE est facultative ou non.
     *
     * @param optionnel Variable booléenne indiquant si l'UE est facultative.
     */
    public void setOptionnel(boolean optionnel) {
        this.Optionnel = optionnel;
    }

    /**
     * Retourne le nom de l'UE.
     *
     * @return Le nom de l'UE.
     */
    public String getNomUE() {
        return nomUE;
    }

    /**
     * Retourne le nombre de places maximales pour l'UE.
     *
     * @return Le nombre de places maximales pour l'UE.
     */
    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    /**
     * Définit le nombre de places maximales pour l'UE, uniquement si l'UE est facultative.
     *
     * @param nbPlacesMax Le nombre de places maximales pour l'UE.
     */
    public void setNbPlacesMax(int nbPlacesMax) {
        if (this.Optionnel) {
            this.nbPlacesMax = nbPlacesMax;
        }
    }

    /**
     * Retourne le nom de l'enseignant.
     *
     * @return Le nom de l'enseignant.
     */
    public String getNomEnseignant() {
        return nomEnseignant;
    }

    /**
     * Définit le nom de l'enseignant.
     *
     * @param nomEnseignant Le nom de l'enseignant.
     */
    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    /**
     * Retourne le nombre d'étudiants inscrits à l'UE.
     *
     * @return Le nombre d'étudiants inscrits à l'UE.
     */
    public int getnbParticipant() {
        return nbParticipant;
    }

    /**
     * Incrémente le nombre de participants si l'UE est facultative.
     * Si l'UE n'est pas facultative, le nombre de participants reste inchangé.
     *
     * @see #setOptionnel(boolean)
     * @see #getnbParticipant()
     */
    public void setnbParticipant() {
        if (this.Optionnel) {
            this.nbParticipant += 1;
        }
    }

    // Méthodes de l'objet

    /**
     * Retourne un code de hachage pour l'objet basé sur le nom de l'Unité d'Enseignement (UE).
     *
     * @return Code de hachage basé sur le nom de l'UE.
     * @see Objects#hash(Object...)
     * @see #equals(Object)
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomUE);
    }

    /**
     * Compare cette instance d'UniteEnseignement avec un autre objet pour déterminer leur égalité.
     * Deux instances d'UniteEnseignement sont considérées comme égales si elles ont le même nom d'unité d'enseignement (nomUE).
     *
     * @param o L'objet avec lequel comparer cette instance d'UniteEnseignement.
     * @return True si les deux objets sont égaux, False sinon.
     * @see Objects#equals(Object, Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteEnseignement that = (UniteEnseignement) o;
        return Objects.equals(nomUE, that.nomUE);
    }
}
