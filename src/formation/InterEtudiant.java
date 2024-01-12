package formation;

import java.util.List;
import java.util.Set;

/**
 * Les fonctionnalit�s offertes � un �tudiant.
 *
 * @author Eric Cariou
 */
public interface InterEtudiant {
  
  /**
   * Cr�e le compte d'un �tudiant � partir de ses informations
   * personnelles et de son mot de passe puis retourne son num�ro d'�tudiant
   * g�n�r� automatiquement.
   *
   * @param infos les informations personnelles de l'�tudiant
   * @param motDePasse le mot de passe de l'�tudiant pour se connecter (la
   *        chaine doit �tre non vide)
   * @return le num�ro unique de l'�tudiant ou -1 en cas de probl�me
   */
  int inscription(InformationPersonnelle infos, String motDePasse);
  
  /**
   * Connecte l'�tudiant avec son num�ro d'�tudiant et son mot de passe.
   *
   * @param numero le num�ro de l'�tudiant
   * @param motDePasse le mot de passe de l'�tudiant
   * @return <code>true</code> si le couple num�ro/mot de passe est correct
   *         (l'�tudiant est alors consid�r� comme connect� au
   *         syst�me), <code>false</code> si le couple est incorrect
   */
  boolean connexion(int numero, String motDePasse);
  
  /**
   * D�connecte l'�tudiant actuellement connect� au syst�me.
   *
   * @throws NonConnecteException si aucun �tudiant n'�tait connect�
   */
  void deconnexion() throws NonConnecteException;
  
  /**
   * L'ensemble des unit�s d'enseignement obligatoires de l'ann�e de
   * formation.
   *
   * @return l'ensemble des UE obligatoires
   */
  Set<UniteEnseignement> enseignementsObligatoires();
  
  /**
   * L'ensemble des unit�s d'enseignement optionnelles de l'ann�e de
   * formation.
   *
   * @return l'ensemble des UE optionnelles
   */
  Set<UniteEnseignement> enseignementsOptionnels();
  
  /**
   * Retourne le nombre d'options que l'�tudiant doit choisir au total.
   *
   * @return le nombre d'options que l'�tudiant doit choisir ou -1 si ce
   *         nombre n'a pas �t� encore d�fini.
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  int nombreOptions() throws NonConnecteException;
  
  /**
   * Choix d'une UE optionnelle par l'�tudiant.
   *
   * @param ue l'UE que l'�tudiant veut choisir
   * @return <code>true</code> si l'�tudiant a �t� inscrit � l'UE,
   *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
   *         places dans l'UE ou l'UE n'est pas une option)
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  boolean choisirOption(UniteEnseignement ue) throws NonConnecteException;
  
  
  /**
   * Renvoie le num�ro de groupe de TD de l'�tudiant s'il a �t�
   * d�fini.
   *
   * @return le num�ro de groupe de TD s'il a �t� d�fini ou -1 si �a
   *         n'est pas encore le cas
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  int getNumeroGroupeTravauxDiriges() throws NonConnecteException;
  
  /**
   * Renvoie le num�ro de groupe de TP de l'�tudiant s'il a �t�
   * d�fini.
   *
   * @return le num�ro de groupe de TP s'il a �t� d�fini ou -1 si �a
   *         n'est pas encore le cas
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  int getNumeroGroupeTravauxPratiques() throws NonConnecteException;
  
  /**
   * Renvoie l'ensemble des enseignements suivis par l'�tudiant : les UE
   * obligatoires ainsi que les UE optionnelles o� il est inscrit.
   *
   * @return l'ensemble des UE suivies par l'�tudiant
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException;
  
  /**
   * Renvoie la liste de tous les messages re�us par l'�tudiant (lus et non
   * lus), dans l'ordre o� ils ont �t� re�us.
   *
   * @return tous les messages de l'�tudiant
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  List<String> listeTousMessages() throws NonConnecteException;
  
  /**
   * Renvoie la liste des messages non lus par l'�tudiant, dans l'ordre o�
   * ils ont �t� re�us.
   *
   * @return les messages non lus de l'�tudiant
   * @throws NonConnecteException si la m�thode est appel�e alors que
   *         l'�tudiant n'est pas connect�
   */
  List<String> listeMessageNonLus() throws NonConnecteException;
  
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
   *         l'�tudiant n'est pas connect�
   */
  boolean inscriptionFinalisee() throws NonConnecteException;
  
}

