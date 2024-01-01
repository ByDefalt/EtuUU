package formation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Les fonctionnalit�s offertes � un �tudiant.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Etudiant implements InterEtudiant {

	// ******************************* ATTRIBUT D'INSTANCES
    private InformationPersonnelle informationPersonnelle;
	private int numero;
	private String motDePasse;
	private static int nbEtudiant = 0;
	private boolean etatConnexion;
	private int numeroTp = -1;
	private int numeroTd = -1;
	private int nbOption = -1;
	private Map<Boolean, String> message = new HashMap<>();
	private Set<UniteEnseignement> listeUE = new HashSet<>();
	private Set<UniteEnseignement> listeUEsuivies = new HashSet<>();

	/**
	 * Constructeur de la classe Etudiant.
	 *
	 * @param informationPersonnelle Les informations personnelles de l'étudiant.
	 * @param motDePasse             Le mot de passe de l'étudiant pour se
	 *                               connecter (la chaine doit être non vide).
	 */
	public Etudiant(InformationPersonnelle informationPersonnelle, String motDePasse) {
    this.informationPersonnelle = informationPersonnelle;
		this.motDePasse = motDePasse;
		nbEtudiant++;
		this.numero = nbEtudiant;
		this.etatConnexion = false;
	}

	/**
	 * Constructeur vide de la classe Etudiant.
	 */
	public Etudiant() {
	}

	/**
	 * Définit le numéro de TP de l'étudiant.
	 *
	 * @param numeroTp Le numéro de TP à attribuer à l'étudiant.
	 */
	public void setNumeroTp(int numeroTp) {
		this.numeroTp = numeroTp;
	}

	/**
	 * Définit le numéro de TD de l'étudiant.
	 *
	 * @param numeroTd Le numéro de TD à attribuer à l'étudiant.
	 */
	public void setNumeroTd(int numeroTd) {
		this.numeroTd = numeroTd;
	}

	/**
	 * Définit le nombre d'options de l'étudiant.
	 *
	 * @param nbOption Le nombre d'options à attribuer à l'étudiant.
	 */
	public void setNbOption(int nbOption) {
		this.nbOption = nbOption;
	}

	/**
	 * Définit la liste des unités d'enseignement de l'étudiant.
	 *
	 * @param listeUE La liste des unités d'enseignement à attribuer à
	 *                l'étudiant.
	 */
	public void setListeUE(Set<UniteEnseignement> listeUE) {
		this.listeUE = listeUE;
	}

	/**
	 * Renvoie la liste des unités d'enseignement suivies par l'étudiant.
	 *
	 * @return La liste des unités d'enseignement suivies par l'étudiant.
	 */
	public Set<UniteEnseignement> getListeUEsuivies() {
		return this.listeUEsuivies;
	}

	/**
	 * Renvoie le tableau associatif des messages reçus par l'étudiant.
	 *
	 * @return le tableau associatif des messages reçus par l'étudiant.
	 */
	public Map<Boolean, String> getMessage() {
		return this.message;
	}

	/**
	 * Définit le tableau associatif des messages reçus par l'étudiant.
	 * 
	 * @param message
	 * @return le tableau associatif des messages reçus par l'étudiant.
	 */
	public Map<Boolean, String> setMessage(Map<Boolean, String> message) {
		return this.message = message;
	}

	/**
	 * Cr�e le compte d'un �tudiant � partir de ses informations personnelles
	 * et de son mot de passe puis retourne son num�ro d'�tudiant g�n�r�
	 * automatiquement.
	 *
	 * @param infos      les informations personnelles de l'�tudiant
	 * @param motDePasse le mot de passe de l'�tudiant pour se connecter (la
	 *                   chaine doit �tre non vide)
	 * @return le num�ro unique de l'�tudiant ou -1 en cas de probl�me
	 */
	@Override
	public int inscription(InformationPersonnelle informationPersonnelle, String motDePasse) {
		if (motDePasse == "" || informationPersonnelle == null) {
			return -1;
		}
		this.informationPersonnelle = informationPersonnelle;
		this.motDePasse = motDePasse;
		nbEtudiant++;
		this.numero = nbEtudiant;
		this.etatConnexion = false;

		return this.numero;
	}

	/**
	 * Connecte l'�tudiant avec son num�ro d'�tudiant et son mot de passe.
	 *
	 * @param numero     le num�ro de l'�tudiant
	 * @param motDePasse le mot de passe de l'�tudiant
	 * @return <code>true</code> si le couple num�ro/mot de passe est correct
	 *         (l'�tudiant est alors consid�r� comme connect� au syst�me),
	 *         <code>false</code> si le couple est incorrect
	 */
	@Override
	public boolean connexion(int numero, String motDePasse) {
		if (this.numero == numero && motDePasse.equals(motDePasse)) {
			return true;
		}

		return false;
	}

	/**
	 * D�connecte l'�tudiant actuellement connect� au syst�me.
	 *
	 * @throws NonConnecteException si aucun �tudiant n'�tait connect�
	 */
	@Override
	public void deconnexion() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		this.etatConnexion = false;
	}

	/**
	 * L'ensemble des unit�s d'enseignement obligatoires de l'ann�e de
	 * formation.
	 *
	 * @return l'ensemble des UE obligatoires
	 */
	@Override
	public Set<UniteEnseignement> enseignementsObligatoires() {
		Set<UniteEnseignement> uniteEnseignementsO = new HashSet<>();
		for (UniteEnseignement ue : this.listeUE)
			if (ue.getNbPlacesMax() == 0) {
				uniteEnseignementsO.add(ue);
			}

		return uniteEnseignementsO;
	}

	/**
	 * L'ensemble des unit�s d'enseignement optionnelles de l'ann�e de
	 * formation.
	 *
	 * @return l'ensemble des UE optionnelles
	 */
	@Override
	public Set<UniteEnseignement> enseignementsOptionnels() {
		Set<UniteEnseignement> uniteEnseignementsF = new HashSet<>();
		for (UniteEnseignement ue : this.listeUE)
			if (ue.getNbPlacesMax() > 1) {
				uniteEnseignementsF.add(ue);
			}

		return uniteEnseignementsF;
	}

	/**
	 * Retourne le nombre d'options que l'�tudiant doit choisir au total.
	 *
	 * @return le nombre d'options que l'�tudiant doit choisir ou -1 si ce nombre
	 *         n'a pas �t� encore d�fini.
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public int nombreOptions() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return this.nbOption;
	}

	/**
	 * Choix d'une UE optionnelle par l'�tudiant.
	 *
	 * @param ue l'UE que l'�tudiant veut choisir
	 * @return <code>true</code> si l'�tudiant a �t� inscrit � l'UE,
	 *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
	 *         places dans l'UE ou l'UE n'est pas une option)
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public boolean choisirOption(UniteEnseignement ue) throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		if (ue.getNbPlaces() < ue.getNbPlacesMax()) {
			this.listeUEsuivies.add(ue);
			ue.setNbPlaces();
			return true;
		}
		return false;
	}

	/**
	 * Renvoie le num�ro de groupe de TD de l'�tudiant s'il a �t� d�fini.
	 *
	 * @return le num�ro de groupe de TD s'il a �t� d�fini ou -1 si �a
	 *         n'est pas encore le cas
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return this.numeroTd;
	}

	/**
	 * Renvoie le num�ro de groupe de TP de l'�tudiant s'il a �t� d�fini.
	 *
	 * @return le num�ro de groupe de TP s'il a �t� d�fini ou -1 si �a
	 *         n'est pas encore le cas
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return this.numeroTp;
	}

	/**
	 * Renvoie l'ensemble des enseignements suivis par l'�tudiant : les UE
	 * obligatoires ainsi que les UE optionnelles o� il est inscrit.
	 *
	 * @return l'ensemble des UE suivies par l'�tudiant
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return this.listeUEsuivies;
	}

	/**
	 * Renvoie la liste de tous les messages re�us par l'�tudiant (lus et non
	 * lus), dans l'ordre o� ils ont �t� re�us.
	 *
	 * @return tous les messages de l'�tudiant
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public List<String> listeTousMessages() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return new ArrayList<>(this.message.values());
	}

	/**
	 * Renvoie la liste des messages non lus par l'�tudiant, dans l'ordre o� ils
	 * ont �t� re�us.
	 *
	 * @return les messages non lus de l'�tudiant
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public List<String> listeMessageNonLus() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		List<String> messageNonLus = new ArrayList<String>();
		for (Boolean key : message.keySet()) {
			if (key == false)
				messageNonLus.add(message.get(key));
		}
		return messageNonLus;
	}

	/**
	 * Indique si l'inscription de l'�tudiant est finalis�e, c'est-�-dire si
	 * l'�tudiant :
	 * <ul>
	 * <li>A �t� affect� � un groupe de TD</li>
	 * <li>A �t� affect� � un groupe de TP</li>
	 * <li>A choisi autant d'options que requis</li>
	 * </ul>
	 * Si au moins une des conditions n'est pas valid�e, l'�tudiant n'a pas
	 * finalis� son inscription.
	 *
	 * @return <code>true</code> si l'inscription de l'�tudiant est finalis�e,
	 *         <code>false</code> sinon
	 * @throws NonConnecteException si la m�thode est appel�e alors que
	 *                              l'�tudiant n'est pas connect�
	 */
	@Override
	public boolean inscriptionFinalisee() throws NonConnecteException {
		if (!this.etatConnexion) {
			throw new NonConnecteException();
		}

		return this.numeroTp != -1 && this.numeroTd != -1 && nbOption == enseignementsOptionnels().size();
	}

	/**
	 * Renvoie une représentation textuelle de l'objet.
	 *
	 * @return Une chaîne représentant l'objet.
	 */
	@Override
	public String toString() {
		return "Etudiant [informationPersonnelle=" + informationPersonnelle + ", numero=" + numero + ", motDePasse="
				+ motDePasse + ", etatConnexion=" + etatConnexion + "]";
	}

	/**
	 * Indique si un autre objet est égal à cet étudiant.
	 *
	 * @param obj L'objet à comparer.
	 * @return true si les objets sont égaux, false sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else {
			return false;
		}
	}
}