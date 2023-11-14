package formation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// A DEFINIR
public class Etudiant implements InterEtudiant {
	private static final Set<Etudiant> etudiants = new HashSet<>();
	
	private InformationPersonnelle informationPersonnelle;
	private int numero;
	private String motDePasse;
	private static int nbEtudiant = 0;
	private boolean etatConnexion;
	private GestionFormation gestionFormation;

	private Etudiant(InformationPersonnelle informationPersonnelle, String motDePasse) {
		this.informationPersonnelle = informationPersonnelle;
		this.motDePasse = motDePasse;
		nbEtudiant++;
		this.numero = nbEtudiant;
		this.etatConnexion = false;
	}
	
	private Etudiant() {}
	
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
    public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
    	return 0;
    }
  
    /**
     * Renvoie le numéro de groupe de TP de l'étudiant s'il a été défini.
     *
     * @return le numéro de groupe de TP s'il a été défini ou -1 si ça n'est pas
     *         encore le cas
     * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
     *         n'est pas connecté
     */
    public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
    	return 0;
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
}