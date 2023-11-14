package formation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Les fonctionnalités offertes à un étudiant.
 *
 * @author Eric Cariou
 */
public class Etudiant implements InterEtudiant {
	// ******************************* ATTRIBUT STATIQUE
    private static final Set<Etudiant> etudiants = new HashSet<>();
	
 // ******************************* ATTRIBUT D'INSTANCES
	private InformationPersonnelle informationPersonnelle;
	private int numero;
	private String motDePasse;
	private static int nbEtudiant = 0;
	private boolean etatConnexion;
	private GestionFormation gestionFormation;
	private int numeroTp;
	private int numeroTd;

	public Etudiant(InformationPersonnelle informationPersonnelle, String motDePasse) {
		this.informationPersonnelle = informationPersonnelle;
		this.motDePasse = motDePasse;
		nbEtudiant++;
		this.numero = nbEtudiant;
		this.etatConnexion = false;
	}
	
	public Etudiant() {}
	
	/**
	 * Crée le compte d'un étudiant à partir de ses informations personnelles et
	 * de son mot de passe puis retourne son numéro d'étudiant généré
	 * automatiquement.
	 *
	 * @param infos les informations personnelles de l'étudiant
	 * @param motDePasse le mot de passe de l'étudiant pour se connecter (la
	 *        chaine doit être non vide)
	 * @return le numéro unique de l'étudiant ou -1 en cas de problème
	 */
	@Override
	public int inscription(InformationPersonnelle infos, String motDePasse) {
		if( motDePasse == null || infos == null) {
			return -1;
		}
		Etudiant etudiant = new Etudiant(infos, motDePasse);
		
		if(etudiants.contains(etudiant)) {
			etudiants.add(etudiant);
		}
		
		return this.numero;
	}
	
	/**
	 * Connecte l'étudiant avec son numéro d'étudiant et son mot de passe.
	 *
	 * @param numero le numéro de l'étudiant
	 * @param motDePasse le mot de passe de l'étudiant
	 * @return <code>true</code> si le couple numéro/mot de passe est correct
	 *         (l'étudiant est alors considéré comme connecté au système),
	 *         <code>false</code> si le couple est incorrect
	 */
	@Override
	public boolean connexion(int numero, String motDePasse) {
		if(this.numero == numero && motDePasse.equals(motDePasse)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Déconnecte l'étudiant actuellement connecté au système.
	 *
	 * @throws NonConnecteException si aucun étudiant n'était connecté
	 */
	@Override
	public void deconnexion() throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
		
		this.etatConnexion = false;	
	}
	
	/**
	 * L'ensemble des unités d'enseignement obligatoires de l'année de formation.
	 *
	 * @return l'ensemble des UE obligatoires
	 */
	@Override
	public Set<UniteEnseignement> enseignementsObligatoires() {
		Set<UniteEnseignement> uniteEnseignementsO = new HashSet<>();
		for(UniteEnseignement ue : this.gestionFormation.getUniteEnseignements())
			if(ue.getNbPlaces() == 0) {
				uniteEnseignementsO.add(ue);
			}
		
		return uniteEnseignementsO;
	}
	
	/**
     * L'ensemble des unités d'enseignement optionnelles de l'année de formation.
     *
     * @return l'ensemble des UE optionnelles
     */
	@Override
	public Set<UniteEnseignement> enseignementsOptionnels() {
		Set<UniteEnseignement> uniteEnseignementsF = new HashSet<>();
		for(UniteEnseignement ue : this.gestionFormation.getUniteEnseignements())
			if(ue.getNbPlaces() > 1) {
				uniteEnseignementsF.add(ue);
			}
		
		return uniteEnseignementsF;
	}
	
	/**
	 * Retourne le nombre d'options que l'étudiant doit choisir au total.
	 *
	 * @return le nombre d'options que l'étudiant doit choisir ou -1 si ce nombre
	 *         n'a pas été encore défini.
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public int nombreOptions() throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
	
		return this.gestionFormation.getOption().getValueOption() ;
	}
	
	/**
	 * Choix d'une UE optionnelle par l'étudiant.
	 *
	 * @param ue l'UE que l'étudiant veut choisir
	 * @return <code>true</code> si l'étudiant a été inscrit à l'UE,
	 *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
	 *         places dans l'UE ou l'UE n'est pas une option)
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public boolean choisirOption(UniteEnseignement ue) throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
		
		Set<UniteEnseignement> uniteEnseignementsF = new HashSet<>();
		boolean result = false;
		if(uniteEnseignementsF.contains(ue) && ue.getNbPlaces() > 1) {
			result = true;
			ue.setNbPlaces(ue.getNbPlaces() - 1);
		}
		
		return result;
	}
	
	/**
     * Renvoie le numéro de groupe de TD de l'étudiant s'il a été défini.
     *
     * @return le numéro de groupe de TD s'il a été défini ou -1 si ça n'est pas
     *         encore le cas
     * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
     *         n'est pas connecté
     */
	@Override
    public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
    	return this.getNumeroTd();
    }
  
    /**
     * Renvoie le numéro de groupe de TP de l'étudiant s'il a été défini.
     *
     * @return le numéro de groupe de TP s'il a été défini ou -1 si ça n'est pas
     *         encore le cas
     * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
     *         n'est pas connecté
     */
	@Override
    public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
		return this.getNumeroTp();
    }
	
	/**
	 * Renvoie l'ensemble des enseignements suivis par l'étudiant : les UE
	 * obligatoires ainsi que les UE optionnelles où il est inscrit.
	 *
	 * @return l'ensemble des UE suivies par l'étudiant
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException {
		Map<Etudiant, UniteEnseignement> gestionEtudiantUE = this.gestionFormation.getGestionEtudiantUE();
		Set<UniteEnseignement> uniteEnseignement = new HashSet<>();
		for(Etudiant etudiant : gestionEtudiantUE.keySet()) {
			if(etudiant.equals(this)) {
				uniteEnseignement.add(gestionEtudiantUE.get(etudiant));
			}
		}
		
		return uniteEnseignement;
	}
	
	/**
	 * Renvoie la liste de tous les messages reçus par l'étudiant (lus et non
	 * lus), dans l'ordre où ils ont été reçus.
	 *
	 * @return tous les messages de l'étudiant
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public List<String> listeTousMessages() throws NonConnecteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Renvoie la liste des messages non lus par l'étudiant, dans l'ordre où ils
	 * ont été reçus.
	 *
	 * @return les messages non lus de l'étudiant
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public List<String> listeMessageNonLus() throws NonConnecteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Indique si l'inscription de l'étudiant est finalisée, c'est-à-dire si
	 * l'étudiant :
	 * <ul>
	 * <li>A été affecté à un groupe de TD</li>
	 * <li>A été affecté à un groupe de TP</li>
	 * <li>A choisi autant d'options que requis</li>
	 * </ul>
	 * Si au moins une des conditions n'est pas validée, l'étudiant n'a pas
	 * finalisé son inscription.
	 *
	 * @return <code>true</code> si l'inscription de l'étudiant est finalisée,
	 *         <code>false</code> sinon
	 * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
	 *         n'est pas connecté
	 */
	@Override
	public boolean inscriptionFinalisee() throws NonConnecteException {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Override
	public String toString() {
		return "Etudiant [informationPersonnelle=" + informationPersonnelle + ", numero=" + numero + ", motDePasse="
				+ motDePasse + ", etatConnexion=" + etatConnexion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(etatConnexion, informationPersonnelle, motDePasse, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return etatConnexion == other.etatConnexion
				&& Objects.equals(informationPersonnelle, other.informationPersonnelle)
				&& Objects.equals(motDePasse, other.motDePasse) && numero == other.numero;
	}

	public InformationPersonnelle getInformationPersonnelle() {
		return informationPersonnelle;
	}

	public void setInformationPersonnelle(InformationPersonnelle informationPersonnelle) {
		this.informationPersonnelle = informationPersonnelle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public static int getNbEtudiant() {
		return nbEtudiant;
	}

	public static void setNbEtudiant(int nbEtudiant) {
		Etudiant.nbEtudiant = nbEtudiant;
	}

	public boolean isEtatConnexion() {
		return etatConnexion;
	}

	public void setEtatConnexion(boolean etatConnexion) {
		this.etatConnexion = etatConnexion;
	}

	public GestionFormation getGestionFormation() {
		return gestionFormation;
	}

	public void setGestionFormation(GestionFormation gestionFormation) {
		this.gestionFormation = gestionFormation;
	}

	public int getNumeroTp() {
		return numeroTp;
	}

	public void setNumeroTp(int numeroTp) {
		this.numeroTp = numeroTp;
	}

	public int getNumeroTd() {
		return numeroTd;
	}

	public void setNumeroTd(int numeroTd) {
		this.numeroTd = numeroTd;
	}

	public static Set<Etudiant> getEtudiants() {
		return etudiants;
	}
	
	
}