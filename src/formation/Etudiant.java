package formation;

import java.util.*;

/**
 * Les fonctionnalit�s offertes � un �tudiant.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Etudiant {
    // ******************************* ATTRIBUT D'INSTANCES
    private final InformationPersonnelle informationPersonnelle;
    public InformationPersonnelle getInformationPersonnelle() {
        return informationPersonnelle;
    }

    private final String motDePasse;
    private int numero;
    private int nbOption = -1;
    private int numeroTp = -1;
    private int numeroTd = -1;
    private final Set<UniteEnseignement> listeUEsuivies = new HashSet<>();
    private final List<Message> messages = new ArrayList<>();

    /**
     * Constructeur de la classe Etudiant.
     *
     * @param informationPersonnelle Les informations personnelles de l'étudiant.
     * @param motDePasse             Le mot de passe de l'étudiant pour se connecter (la chaîne doit être non vide).
     */
    public Etudiant(InformationPersonnelle informationPersonnelle, String motDePasse) {
        this.informationPersonnelle = informationPersonnelle;
        this.motDePasse = motDePasse;
    }

    /**
     * Obtient le numéro de l'étudiant.
     *
     * @return Le numéro de l'étudiant.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Définit le numéro de l'étudiant.
     *
     * @param numero Le nouveau numéro de l'étudiant.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtient le mot de passe de l'étudiant.
     *
     * @return Le mot de passe de l'étudiant.
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Obtient le nombre d'options que l'étudiant doit choisir.
     *
     * @return Le nombre d'options que l'étudiant doit choisir ou -1 si ce nombre n'a pas été défini.
     */
    public int getNbOption() {
        return nbOption;
    }

    /**
     * Définit le nombre d'options que l'étudiant doit choisir.
     *
     * @param nbOption Le nombre d'options à attribuer à l'étudiant.
     */
    public void setNbOption(int nbOption) {
        this.nbOption = nbOption;
    }

    /**
     * Obtient le numéro de TP de l'étudiant.
     *
     * @return Le numéro de TP de l'étudiant.
     */
    public int getNumeroTp() {
        return numeroTp;
    }

    /**
     * Définit le numéro de TP de l'étudiant.
     *
     * @param numeroTp Le nouveau numéro de TP de l'étudiant.
     */
    public void setNumeroTp(int numeroTp) {
        this.numeroTp = numeroTp;
    }

    /**
     * Obtient le numéro de TD de l'étudiant.
     *
     * @return Le numéro de TD de l'étudiant.
     */
    public int getNumeroTd() {
        return numeroTd;
    }

    /**
     * Définit le numéro de TD de l'étudiant.
     *
     * @param numeroTd Le nouveau numéro de TD de l'étudiant.
     */
    public void setNumeroTd(int numeroTd) {
        this.numeroTd = numeroTd;
    }

    /**
     * Obtient la liste des unités d'enseignement suivies par l'étudiant.
     *
     * @return La liste des unités d'enseignement suivies par l'étudiant.
     */
    public Set<UniteEnseignement> getListeUEsuivies() {
        return listeUEsuivies;
    }

    /**
     * Ajoute une unité d'enseignement à la liste des UE suivies par l'étudiant.
     *
     * @param ue L'unité d'enseignement à ajouter.
     */
    public void addUE(UniteEnseignement ue) {
        this.listeUEsuivies.add(ue);
    }

    /**
     * Obtient la liste des messages reçus par l'étudiant.
     *
     * @return La liste des messages reçus par l'étudiant.
     */
    public List<Message> getMessages() {
        return messages;
    }

	/**
	 * Vérifie l'égalité d'un objet.
	 *
	 * @return true si c'est l'étudiant sinon false.
	 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Etudiant other = (Etudiant) obj;
        return numero == other.numero;
    }
}