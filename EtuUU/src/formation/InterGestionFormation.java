package formation;

import java.util.Set;

/**
 * Les services de gestion d'une année de formation.
 *
 * @author Eric Cariou
 *
 */
public interface InterGestionFormation {
  
  /**
   * Crée une (année de) formation avec son nom et celui du responsable. Si une
   * formation existait déjà dans le système, la nouvelle la remplace et efface
   * la précédente.
   *
   * @param nomFormation le nom de la formation (chaine non vide)
   * @param nomResponsable le nom et prénom du responsable (chaine non vide)
   * @param email l'email du responsable (adresse email valide)
   */
  void creerFormation(String nomFormation, String nomResponsable, String email);
  
  /**
   * Renvoie le nom du responsable de formation.
   *
   * @return le nom du responsable de formation ou <code>null</code> s'il n'a
   *         pas été défini
   */
  String getNomResponsableFormation();
  
  /**
   * Renvoie l'adresse email du responsable de formation.
   *
   * @return l'adresse email du responsable de formation ou <code>null</code> si
   *         elle n'a pas été définie
   */
  String getEmailResponsableFormation();
  
  /**
   * Renvoie le nom de la formation.
   *
   * @return le nom de la formation
   */
  String getNomFormation();
  
  
  /**
   * Rajoute une UE obligatoire à la formation. L'UE ne doit pas déjà être dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE à rajouter
   * @return <code>true</code> si l'ajout a été fait, <code>false</code> en cas
   *         de problème
   */
  boolean ajouterEnseignementObligatoire(UniteEnseignement ue);
  
  /**
   * Rajoute une UE optionnelle à la formation. L'UE ne doit pas déjà être dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE à rajouter
   * @param nbPlaces le nombre de places maximum dans l'option (nombre supérieur
   *        à 1) ou 0 pour préciser qu'il n'y a pas de limite de places
   * @return <code>true</code> si l'ajout a été fait, <code>false</code> en cas
   *         de problème
   */
  boolean ajouterEnseignementOptionnel(UniteEnseignement ue, int nbPlaces);
  
  /**
   * Définit le nombre d'options que doit choisir un étudiant. Ne peut plus être
   * modifié une fois défini.
   *
   * @param nombre le nombre d'options à choisir pour un étudiant (nombre
   *        supérieur ou égal à 1)
   */
  void definirNombreOptions(int nombre);
  
  /**
   * Définit le nombre de places dans un groupe de TD. Ne peut plus être modifié
   * une fois défini.
   *
   * @param taille le nombre de place dans un groupe de TD (nombre supérieur à
   *        1)
   */
  void setTailleGroupeDirige(int taille);
  
  /**
   * Définit le nombre de places dans un groupe de TP. Ne peut plus être modifié
   * une fois défini.
   *
   * @param taille le nombre de place dans un groupe de TP (nombre supérieur à
   *        1)
   */
  void setTailleGroupePratique(int taille);
  
  /**
   * Renvoie le nombre de places dans un groupe de TD.
   *
   * @return le nombre de places dans un groupe de TD ou -1 s'il n'a pas encore
   *         été défini
   */
  int getTailleGroupeDirige();
  
  /**
   * Renvoie le nombre de places dans un groupe de TP.
   *
   * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
   *         été défini
   */
  int getTailleGroupePratique();
  
  /**
   * Attribue automatiquement les étudiants non encore affectés à des groupes de
   * TD et de TP. Au besoin, crée de nouveaux groupes de TD ou de TP. Pour
   * harmoniser la taille des groupes, des étudiants déjà placés peuvent être
   * déplacés. Les étudiants concernés par une affectation ou un changement
   * d'affectation reçoivent un message pour leur préciser ce qu'il s'est passé.
   */
  void attribuerAutomatiquementGroupes();
  
  /**
   * Déplace à la main un étudiant d'un groupe de TD/TP. L'opération peut
   * échouer si les groupes sont déjà pleins.
   *
   * @param etudiant l'étudiant à déplacer
   * @param groupeDirige le nouveau groupe de TD (ou 0 si on ne change pas de
   *        groupe de TD)
   * @param groupePratique le nouveau groupe de TP (ou 0 si on ne change de
   *        groupe de TP)
   * @return
   *         <ul>
   *         <li>0 si le ou les déplacements ont été réalisés correctement</li>
   *         <li>-1 si le déplacement de TD n'a pas pu être fait</li>
   *         <li>-2 si le déplacement de TP n'a pas pu être fait</li>
   *         <lI>-3 si les déplacements de TD et de TP n'ont pas pu être
   *         faits</li>
   *         </ul>
   */
  int changerGroupe(Etudiant etudiant, int groupeDirige, int groupePratique);
  
  /**
   * Renvoie le nombre de groupes de TD actuellement définis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  int nombreGroupesTravauxDiriges();
  
  /**
   * Renvoie le nombre de groupes de TP actuellement définis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  int nombreGroupesTravauxPratiques();
  
  
  /**
   * Les étudiants affectés à un certain groupe de TD.
   *
   * @param groupe le groupe de TD
   * @return l'ensemble des étudiants affectés au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  Set<Etudiant> listeEtudiantsGroupeDirige(int groupe);
  
  /**
   * Les étudiants affectés à un certain groupe de TP.
   *
   * @param groupe le groupe de TP
   * @return l'ensemble des étudiants affectés au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  Set<Etudiant> listeEtudiantsGroupePratique(int groupe);
  
  /**
   * Les étudiants inscrits à une certaine option.
   *
   * @param option l'option
   * @return l'ensemble des étudiants inscrits à l'UE ou <code>null</code> si
   *         l'UE n'est pas proposée en option
   */
  Set<Etudiant> listeEtudiantsOption(UniteEnseignement option);
}
