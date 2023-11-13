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
	boolean etatConnexion;

	private Etudiant(InformationPersonnelle informationPersonnelle, String motDePasse) {
		this.informationPersonnelle = informationPersonnelle;
		this.motDePasse = motDePasse;
		nbEtudiant++;
		this.numero = nbEtudiant;
		this.etatConnexion = false;
	}
	
	private Etudiant() {}
	
	public InformationPersonnelle getInformationPersonnelle() {
		return this.informationPersonnelle;
	}

	public void setInformationPersonnelle(InformationPersonnelle informationPersonnelle) {
		this.informationPersonnelle = informationPersonnelle;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public static int getNbEtudiant() {
		return nbEtudiant;
	}
	public boolean isEtatConnexion() {
		return etatConnexion;
	}

	public void setEtatConnexion(boolean etatConnexion) {
		this.etatConnexion = etatConnexion;
	}
	
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
	
	public void deconnexion() throws NonConnecteException {
		if(!this.etatConnexion) {
			throw new NonConnecteException();
		}
		
		this.etatConnexion = false;	
	}
	
	public Set<UniteEnseignement> enseignementsObligatoires() {
		GestionFormation form = new GestionFormation();
		Set<UniteEnseignement> ue =  new HashSet<>();
		
		return;
	}
	
	/**
     * L'ensemble des unit�s d'enseignement optionnelles de l'ann�e de formation.
     *
     * @return l'ensemble des UE optionnelles
     */
	public Set<UniteEnseignement> enseignementsOptionnels();
	
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








