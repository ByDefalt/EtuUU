package formation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Les services de gestion d'une ann�e de formation.
 *
 * @author Eric Cariou
 *
 */
public class GestionFormation implements InterGestionFormation {

  // ******************************* ATTRIBUT D'INSTANCES

  private String nomFormation;
  private String nomResponsable;
  private String email;
  private final Set<UniteEnseignement> UniteEseignements = new HashSet<>();
  private final Map<Integer, Set<Etudiant>> tds = new HashMap<>();
  private final Map<Integer, Set<Etudiant>> tps = new HashMap<>();
  private final Set<Etudiant> listeEtudiants =new HashSet<>();
  private int tailleGroupeDirige = -1;
  private int tailleGroupePratique = -1;
  private int NBoption = -1;

  public GestionFormation() {

  }
  public void setnbOptionEtudiant(Etudiant etu){
    

  }
  public Map<Integer, Set<Etudiant>> getTds() {
    return tds;
  }
  public Map<Integer, Set<Etudiant>> getTps() {
    return tps;
  }
  public Set<Etudiant> getlisteEtudiants(){
    return listeEtudiants;
  }

  /**
   * Cr�e une (ann�e de) formation avec son nom et celui du responsable. Si une
   * formation existait d�j� dans le syst�me, la nouvelle la remplace et efface
   * la pr�c�dente.
   *
   * @param nomFormation   le nom de la formation (chaine non vide)
   * @param nomResponsable le nom et pr�nom du responsable (chaine non vide)
   * @param email          l'email du responsable (adresse email valide)
   */
  @Override
  public void creerFormation(String nomFormation, String nomResponsable, String email) {
    this.nomFormation = nomFormation;
    this.nomResponsable = nomResponsable;
    this.email = email;
    this.UniteEseignements.removeAll();
    this.tds.clear();
    this.tps.clear();
    this.listeEtudiants.removeAll();
    this.tailleGroupeDirige = -1;
    this.tailleGroupePratique = -1;
    this.NBoption = -1;
  }
  /**
   * Renvoie le nom du responsable de formation.
   * s
   * 
   * @return le nom du responsable de formation ou <code>null</code> s'il n'a
   *         pas �t� d�fini
   */
  @Override
  public String getNomResponsableFormation() {
    return this.nomResponsable;
  }

  /**
   * Renvoie l'adresse email du responsable de formation.
   *
   * @return l'adresse email du responsable de formation ou <code>null</code> si
   *         elle n'a pas �t� d�finie
   */
  @Override
  public String getEmailResponsableFormation() {
    return this.email;
  }

  /**
   * Renvoie le nom de la formation.
   *
   * @return le nom de la formation
   */
  @Override
  public String getNomFormation() {
    return this.nomFormation;
  }

  /**
   * Rajoute une UE obligatoire � la formation. L'UE ne doit pas d�j� �tre dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue l'UE � rajouter
   * @return <code>true</code> si l'ajout a �t� fait, <code>false</code> en cas
   *         de probl�me
   */
  @Override
  public boolean ajouterEnseignementObligatoire(UniteEnseignement ue) {
    if (!UniteEseignements.contains(ue) && ue.getNbPlaces() == 0) {
      UniteEseignements.add(ue);
      return true;
    }
    return false;
  }

  /**
   * Rajoute une UE optionnelle � la formation. L'UE ne doit pas d�j� �tre dans
   * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
   *
   * @param ue       l'UE � rajouter
   * @param nbPlaces le nombre de places maximum dans l'option (nombre sup�rieur
   *                 � 1) ou 0 pour pr�ciser qu'il n'y a pas de limite de places
   * @return <code>true</code> si l'ajout a �t� fait, <code>false</code> en cas
   *         de probl�me
   */
  @Override
  public boolean ajouterEnseignementOptionnel(UniteEnseignement ue, int nbPlaces) {
    if (!UniteEseignements.contains(ue) && ue.getNbPlaces() > 1) {
      ue.setNbPlaces(nbPlaces);
      UniteEseignements.add(ue);
      return true;
    }
    return false;
  }
  /**
   * D�finit le nombre d'options que doit choisir un �tudiant. Ne peut plus �tre
   * modifi� une fois d�fini.
   *
   * @param nombre le nombre d'options � choisir pour un �tudiant (nombre
   *               sup�rieur ou �gal � 1)
   */
  @Override
  public void definirNombreOptions(int nombre) {
    if(this.NBoption == -1){
      this.NBoption=nombre;
    }
  }

  /**
   * D�finit le nombre de places dans un groupe de TD. Ne peut plus �tre modifi�
   * une fois d�fini.
   *
   * @param taille le nombre de place dans un groupe de TD (nombre sup�rieur �
   *               1)
   */
  @Override
  public void setTailleGroupeDirige(int taille) {
    if(this.tailleGroupeDirige == -1){
      this.tailleGroupeDirige=nombre;
    }
  }

  /**
   * D�finit le nombre de places dans un groupe de TP. Ne peut plus �tre modifi�
   * une fois d�fini.
   *
   * @param taille le nombre de place dans un groupe de TP (nombre sup�rieur �
   *               1)
   */
  @Override
  public void setTailleGroupePratique(int taille) {
    if(this.tailleGroupePratique == -1){
      this.tailleGroupePratique=nombre;
    }
  }

  /**
   * Renvoie le nombre de places dans un groupe de TD.
   *
   * @return le nombre de places dans un groupe de TD ou -1 s'il n'a pas encore
   *         �t� d�fini
   */
  @Override
  public int getTailleGroupeDirige() {
    return this.tailleGroupeDirige;
  }

  /**
   * Renvoie le nombre de places dans un groupe de TP.
   *
   * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
   *         �t� d�fini
   */
  @Override
  public int getTailleGroupePratique() {
    return this.tailleGroupePratique;
  }

  /**
   * Attribue automatiquement les �tudiants non encore affect�s � des groupes de
   * TD et de TP. Au besoin, cr�e de nouveaux groupes de TD ou de TP. Pour
   * harmoniser la taille des groupes, des �tudiants d�j� plac�s peuvent �tre
   * d�plac�s. Les �tudiants concern�s par une affectation ou un changement
   * d'affectation re�oivent un message pour leur pr�ciser ce qu'il s'est pass�.
   */
  @Override
  public void attribuerAutomatiquementGroupes() {

  }

  /**
   * D�place � la main un �tudiant d'un groupe de TD/TP. L'op�ration peut
   * �chouer si les groupes sont d�j� pleins.
   *
   * @param etudiant       l'�tudiant � d�placer
   * @param groupeDirige   le nouveau groupe de TD (ou 0 si on ne change pas de
   *                       groupe de TD)
   * @param groupePratique le nouveau groupe de TP (ou 0 si on ne change de
   *                       groupe de TP)
   * @return
   *         <ul>
   *         <li>0 si le ou les d�placements ont �t� r�alis�s correctement</li>
   *         <li>-1 si le d�placement de TD n'a pas pu �tre fait</li>
   *         <li>-2 si le d�placement de TP n'a pas pu �tre fait</li>
   *         <lI>-3 si les d�placements de TD et de TP n'ont pas pu �tre
   *         faits</li>
   *         </ul>
   */
  @Override
  public int changerGroupe(Etudiant etudiant, int groupeDirige, int groupePratique) {
    int res = -3;
    return res;
  }

  /**
   * Renvoie le nombre de groupes de TD actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  @Override
  public int nombreGroupesTravauxDiriges() {
    return tds.size();
  }

  /**
   * Renvoie le nombre de groupes de TP actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  @Override
  public int nombreGroupesTravauxPratiques() {
    return tps.size();
  }

  /**
   * Les �tudiants affect�s � un certain groupe de TD.
   *
   * @param groupe le groupe de TD
   * @return l'ensemble des �tudiants affect�s au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  @Override
  public Set<Etudiant> listeEtudiantsGroupeDirige(int groupe) {
    return tds.get(groupe);
  }

  /**
   * Les �tudiants affect�s � un certain groupe de TP.
   *
   * @param groupe le groupe de TP
   * @return l'ensemble des �tudiants affect�s au groupe ou <code>null</code> si
   *         le groupe n'existe pas
   */
  @Override
  public Set<Etudiant> listeEtudiantsGroupePratique(int groupe) {
    return tps.get(groupe);
  }

  /**
   * Les �tudiants inscrits � une certaine option.
   *
   * @param option l'option
   * @return l'ensemble des �tudiants inscrits � l'UE ou <code>null</code> si
   *         l'UE n'est pas propos�e en option
   */
  @Override
  public Set<Etudiant> listeEtudiantsOption(UniteEnseignement option) {
    Set<Etudiant> listeetu=new HashSet<>();
    boolean estpassé=false;
    for( Etudiant etu : listeEtudiants){
      for( Set<UniteEnseignement> list : etu.getlisteUEsuivies()){
        for( UniteEseignements ue : list){
            if(ue.equals(option)){
              listeetu.add(etu);
              estpassé=true;
            }
        }
      }
    }
    if(estpassé){
      return listeetu;
    }else{
      return null;
    }
  }
}
