package formation;

import java.util.List;
import java.util.Set;

/**
 * Les fonctionnalités offertes à un étudiant.
 *
 * @author Eric Cariou
 */
public interface InterEtudiant {
  
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
  int inscription(InformationPersonnelle infos, String motDePasse);
  
  /**
   * Connecte l'étudiant avec son numéro d'étudiant et son mot de passe.
   *
   * @param numero le numéro de l'étudiant
   * @param motDePasse le mot de passe de l'étudiant
   * @return <code>true</code> si le couple numéro/mot de passe est correct
   *         (l'étudiant est alors considéré comme connecté au système),
   *         <code>false</code> si le couple est incorrect
   */
  boolean connexion(int numero, String motDePasse);
  
  /**
   * Déconnecte l'étudiant actuellement connecté au système.
   *
   * @throws NonConnecteException si aucun étudiant n'était connecté
   */
  void deconnexion() throws NonConnecteException;
  
  /**
   * L'ensemble des unités d'enseignement obligatoires de l'année de formation.
   *
   * @return l'ensemble des UE obligatoires
   */
  Set<UniteEnseignement> enseignementsObligatoires();
  
  /**
   * L'ensemble des unités d'enseignement optionnelles de l'année de formation.
   *
   * @return l'ensemble des UE optionnelles
   */
  Set<UniteEnseignement> enseignementsOptionnels();
  
  /**
   * Retourne le nombre d'options que l'étudiant doit choisir au total.
   *
   * @return le nombre d'options que l'étudiant doit choisir ou -1 si ce nombre
   *         n'a pas été encore défini.
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  int nombreOptions() throws NonConnecteException;
  
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
  boolean choisirOption(UniteEnseignement ue) throws NonConnecteException;
  
  
  /**
   * Renvoie le numéro de groupe de TD de l'étudiant s'il a été défini.
   *
   * @return le numéro de groupe de TD s'il a été défini ou -1 si ça n'est pas
   *         encore le cas
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  int getNumeroGroupeTravauxDiriges() throws NonConnecteException;
  
  /**
   * Renvoie le numéro de groupe de TP de l'étudiant s'il a été défini.
   *
   * @return le numéro de groupe de TP s'il a été défini ou -1 si ça n'est pas
   *         encore le cas
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  int getNumeroGroupeTravauxPratiques() throws NonConnecteException;
  
  /**
   * Renvoie l'ensemble des enseignements suivis par l'étudiant : les UE
   * obligatoires ainsi que les UE optionnelles où il est inscrit.
   *
   * @return l'ensemble des UE suivies par l'étudiant
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException;
  
  /**
   * Renvoie la liste de tous les messages reçus par l'étudiant (lus et non
   * lus), dans l'ordre où ils ont été reçus.
   *
   * @return tous les messages de l'étudiant
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  List<String> listeTousMessages() throws NonConnecteException;
  
  /**
   * Renvoie la liste des messages non lus par l'étudiant, dans l'ordre où ils
   * ont été reçus.
   *
   * @return les messages non lus de l'étudiant
   * @throws NonConnecteException si la méthode est appelée alors que l'étudiant
   *         n'est pas connecté
   */
  List<String> listeMessageNonLus() throws NonConnecteException;
  
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
  boolean inscriptionFinalisee() throws NonConnecteException;
  
}

