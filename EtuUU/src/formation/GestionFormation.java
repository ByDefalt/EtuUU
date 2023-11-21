package formation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

/**
 * Les services de gestion d'une ann�e de formation.
 *
 * @author Eric Cariou
 *
 */
public class GestionFormation implements InterGestionFormation {

  // ******************************* ATTRIBUT STATIQUE

  private static final Map<Etudiant,UniteEnseignement> map = new HashMap<Etudiant,UniteEnseignement>();
  private static final Map<Integer, Set<Etudiant>> tds = new HashMap<>();
  private static final Map<Integer, Set<Etudiant>> tps = new HashMap<>();
  private final Set<Etudiant> etudiants = new HashSet<>();

  // ******************************* ATTRIBUT D'INSTANCES

  private final String nomFormation;
  private String nomResponsable;
  private String email;
  private TailleGroupeDirige tailleGroupeDirige = new TailleGroupeDirige();
  private TailleGroupePratique tailleGroupePratique = new TailleGroupePratique();
  private Option option = new Option();
  private final Collection<UniteEnseignement> UniteEseignements = new ArrayList<>();

  public GestionFormation(String nomFormation, String nomResponsable, String email) {
    this.nomFormation = nomFormation;
    this.nomResponsable = nomResponsable;
    this.email = email;
  }

  public GestionFormation() {
    this.nomFormation = null;
  }

  @Override
  public String toString() {
    return "GestionFormation [nomFormation=" + this.nomFormation + ", nomResponsable=" + this.nomResponsable
        + ", email=" + this.email
        + ", TailleGroupeDirige=" + this.tailleGroupeDirige + ", TailleGroupePratique=" + this.tailleGroupePratique
        + ", option="
        + this.option + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GestionFormation other = (GestionFormation) obj;
    if (this.nomFormation == null) {
      if (other.nomFormation != null)
        return false;
    } else if (!this.nomFormation.equals(other.nomFormation))
      return false;
    if (this.nomResponsable == null) {
      if (other.nomResponsable != null)
        return false;
    } else if (!this.nomResponsable.equals(other.nomResponsable))
      return false;
    if (this.email == null) {
      if (other.email != null)
        return false;
    } else if (!this.email.equals(other.email))
      return false;
    return true;
  }

  /**
   * Ce code génère un code de hachage pour l'objet GestionFormation en utilisant
   * les attributs nomFormation, nomResponsable et email.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.nomFormation, this.nomResponsable, this.email);
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
    if (this.nomFormation == null || this.nomResponsable == null || this.email == null) {
      GestionFormation form = new GestionFormation(nomFormation, nomResponsable, email);
      if (!GestionFormations.contains(form)) {
        GestionFormations.add(form);
      }
    }
  }

  public void ccc() {
    System.out.println(GestionFormations);
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

  public boolean SetContien(String nomFormation, String nomResponsable, String email) {
    GestionFormation ges = new GestionFormation(nomFormation, nomResponsable, email);
    if (GestionFormations.contains(ges)) {
      return true;
    }
    return false;
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
    if ((!UniteEseignements.contains(ue) || !UniteEseignements.contains(ue)) && ue.getNbPlaces() == 1) {
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
    if ((!UniteEseignements.contains(ue) || !UniteEseignements.contains(ue)) && ue.getNbPlaces() > 1) {
      ue.setNbPlaces(nbPlaces);
      UniteEseignements.add(ue);
      return true;
    }
    return false;
  }

  public static class Option {
    private static int option = 0;
    private static boolean ismodif = false;

    public int getOption() {
      return Option.option;
    }

    public void setOption(int option) {
      if (Option.ismodif == false) {
        Option.ismodif = true;
        Option.option = option;
      }
    }

    @Override
    public String toString() {
      return String.valueOf(Option.option);
    }
  }
public int getOption(){
  return option.getOption();
}

  public static class TailleGroupeDirige {
    private static int tailleGroupeDirige = 0;
    private static boolean ismodif = false;

    public int getTailleGroupeDirige() {
      return TailleGroupeDirige.tailleGroupeDirige;
    }

    public void setTailleGroupeDirige(int tailleGroupeDirige) {
      if (TailleGroupeDirige.ismodif == false) {
        TailleGroupeDirige.ismodif = true;
        TailleGroupeDirige.tailleGroupeDirige = tailleGroupeDirige;
      }
    }

    @Override
    public String toString() {
      return String.valueOf(TailleGroupeDirige.tailleGroupeDirige);
    }
  }

  public static class TailleGroupePratique {
    private static int tailleGroupePratique = 0;
    private static boolean ismodif = false;

    public int getTailleGroupePratique() {
      return TailleGroupePratique.tailleGroupePratique;
    }

    public void setTailleGroupePratique(int tailleGroupePratique) {
      if (TailleGroupePratique.ismodif == false) {
        TailleGroupePratique.ismodif = true;
        TailleGroupePratique.tailleGroupePratique = tailleGroupePratique;
      }
    }

    @Override
    public String toString() {
      return String.valueOf(TailleGroupePratique.tailleGroupePratique);
    }
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
    this.option.setOption(nombre);
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
    if (taille > 1) {
      this.tailleGroupeDirige.setTailleGroupeDirige(taille);
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
    if (taille > 1) {
      this.tailleGroupePratique.setTailleGroupePratique(taille);
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
    return this.tailleGroupeDirige.getTailleGroupeDirige();
  }

  /**
   * Renvoie le nombre de places dans un groupe de TP.
   *
   * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
   *         �t� d�fini
   */
  @Override
  public int getTailleGroupePratique() {
    return this.tailleGroupePratique.getTailleGroupePratique();
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
    return 1;
  }

  /**
   * Renvoie le nombre de groupes de TD actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  @Override
  public int nombreGroupesTravauxDiriges() {
    return 1;
  }

  /**
   * Renvoie le nombre de groupes de TP actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  @Override
  public int nombreGroupesTravauxPratiques() {
    return 1;
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
    return null;
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
    return null;
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
    return null;
  }
}