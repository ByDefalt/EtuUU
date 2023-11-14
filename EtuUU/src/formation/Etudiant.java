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
	 * Cr�e le compte d'un �tudiant � partir de ses informations personnelles et
	 * de son mot de passe puis retourne son num�ro d'�tudiant g�n�r�
	 * automatiquement.
	 *
	 * @param infos les informations personnelles de l'�tudiant
	 * @param motDePasse le mot de passe de l'�tudiant pour se connecter (la
	 *        chaine doit �tre non vide)
	 * @return le num�ro unique de l'�tudiant ou -1 en cas de probl�me
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
	 * Connecte l'�tudiant avec son num�ro d'�tudiant et son mot de passe.
	 *
	 * @param numero le num�ro de l'�tudiant
	 * @param motDePasse le mot de passe de l'�tudiant
	 * @return <code>true</code> si le couple num�ro/mot de passe est correct
	 *         (l'�tudiant est alors consid�r� comme connect� au syst�me),
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
	 * D�connecte l'�tudiant actuellement connect� au syst�me.
	 *
	 * @throws NonConnecteException si aucun �tudiant n'�tait connect�
	 */
	public void deconnexion() throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
		
		this.etatConnexion = false;	
	}
	
	/**
	 * L'ensemble des unit�s d'enseignement obligatoires de l'ann�e de formation.
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
     * L'ensemble des unit�s d'enseignement optionnelles de l'ann�e de formation.
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
	 * Retourne le nombre d'options que l'�tudiant doit choisir au total.
	 *
	 * @return le nombre d'options que l'�tudiant doit choisir ou -1 si ce nombre
	 *         n'a pas �t� encore d�fini.
	 * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
	 *         n'est pas connect�
	 */
	public int nombreOptions() throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
	
		return this.gestionFormation.getOption().getValueOption() ;
	}
	
	/**
	 * Choix d'une UE optionnelle par l'�tudiant.
	 *
	 * @param ue l'UE que l'�tudiant veut choisir
	 * @return <code>true</code> si l'�tudiant a �t� inscrit � l'UE,
	 *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
	 *         places dans l'UE ou l'UE n'est pas une option)
	 * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
	 *         n'est pas connect�
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
     * Renvoie le num�ro de groupe de TD de l'�tudiant s'il a �t� d�fini.
     *
     * @return le num�ro de groupe de TD s'il a �t� d�fini ou -1 si �a n'est pas
     *         encore le cas
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *         n'est pas connect�
     */
    public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
    	return 0;
    }
  
    /**
     * Renvoie le num�ro de groupe de TP de l'�tudiant s'il a �t� d�fini.
     *
     * @return le num�ro de groupe de TP s'il a �t� d�fini ou -1 si �a n'est pas
     *         encore le cas
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *         n'est pas connect�
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