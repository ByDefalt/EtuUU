package formation;

import java.io.Serializable;
import java.util.*;

/**
 * Les fonctionnalités offertes à un étudiant.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Etudiant implements Serializable, Cloneable {
  // ******************************* ATTRIBUT D'INSTANCES
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -4629219965918701551L;
  
  /**
   * Les informations personnelle.
   */
  private InformationPersonnelle informationPersonnelle;
  
  /**
   * Le mot de passe.
   */
  private final String motDePasse;
  
  /**
   * Le numero.
   */
  private int numero;
  
  /**
   * Le nombre d'options
   */
  private int nbOption = -1;
  
  /**
   * Le numéro de Tp.
   */
  private int numeroTp = -1;
  
  /**
   * Le numéro de Td.
   */
  private int numeroTd = -1;
  
  /**
   * La liste des enseignements suivies.
   */
  private Set<UniteEnseignement> listeUEsuivies = new HashSet<>();
  
  /**
   * La liste des messages.
   */
  private List<Message> messages = new ArrayList<>();
  
  /**
   * Constructeur de la classe Etudiant.
   *
   * @param informationPersonnelle Les informations personnelles de l'étudiant.
   * @param motDePasse Le mot de passe de l'étudiant pour se connecter (la
   *        chaîne doit être non vide).
   */
  public Etudiant(InformationPersonnelle informationPersonnelle,
      String motDePasse) {
    this.informationPersonnelle = informationPersonnelle;
    this.motDePasse = motDePasse;
  }
  
  /**
   * Obtient les informations personnelles de l'étudiant.
   *
   * @return les informations personnelles de l'étudiant.
   */
  public InformationPersonnelle getInformationPersonnelle() {
    return informationPersonnelle;
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
   * @return Le nombre d'options que l'étudiant doit choisir, il sera -1 si ce
   *         nombre n'a pas été défini.
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
   * 
   * @return true si l'ue a été ajouté false sinon.
   */
  public boolean addUE(UniteEnseignement ue) {
    boolean res = this.listeUEsuivies.add(ue);
    return res;
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
   * Obtient nombre d'options dans la listes des ue suivies.
   *
   * @return nombre d'options dans la listes des ue suivies.
   */
  public int getNbOptionListeUeSuivies() {
    int res = 0;
    for (UniteEnseignement ue : this.listeUEsuivies) {
      if (ue.getOptionnel()) {
        res++;
      }
    }
    
    return res;
  }
  
  /**
   * Vérifie l'égalité d'un objet.
   *
   * @param obj l'objet à vérifier.
   * @return true si l'objet est l'instance courante sinon false.
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
  
  /**
   * Crée et renvoie une copie en profondeur de l'instance courante.
   * 
   * @return une copie de l'instance courante.
   */
  @Override
  public Etudiant clone() throws CloneNotSupportedException {
    Etudiant clone = (Etudiant) super.clone();
    
    clone.informationPersonnelle =
        (InformationPersonnelle) this.informationPersonnelle.clone();
    
    Set<UniteEnseignement> listecloue = new HashSet<>();
    for (UniteEnseignement ue : this.listeUEsuivies) {
      listecloue.add((UniteEnseignement) ue.clone());
    }
    clone.listeUEsuivies = listecloue;
    List<Message> listeclomess = new ArrayList<>();
    for (Message message : this.messages) {
      listeclomess.add((Message) message.clone());
    }
    clone.messages = listeclomess;
    
    return clone;
  }
  
  /**
   * Retourne un code de hachage pour l'objet basé sur le numero de L'Etudiant.
   *
   * @return Code de hachage basé sur le nom de l'UE.
   * @see Objects#hash(Object...)
   * @see #equals(Object)
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.numero);
  }
  
  /**
   * Renvoie une représentation sous forme de chaîne de caractères de l'instance
   * courante.
   * 
   * @return retourne une représentation graphique de l'instance courante.
   */
  @Override
  public String toString() {
    return "Etudiant [informationPersonnelle=" + informationPersonnelle
        + ", motDePasse=" + motDePasse + ", numero=" + numero + ", nbOption="
        + nbOption + ", numeroTp=" + numeroTp + ", numeroTd=" + numeroTd
        + ", listeUEsuivies=" + listeUEsuivies + ", messages=" + messages + "]";
  }
  
}
