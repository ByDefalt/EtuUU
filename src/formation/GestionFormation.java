package formation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import io.InterSauvegarde;

import java.util.regex.Matcher;

/**
 * Les services de gestion d'une ann�e de formation.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 * 
 */

public class GestionFormation implements InterGestionFormation, InterSauvegarde, Serializable, Cloneable {

  /**
   * Identifiant de s�rialisation.
   */
  private static final long serialVersionUID = 2231338258512306498L;
  /**
   * Le pattern d'un email
   */
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  /**
   * Le pattern compilé d'un email
   */
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
  /**
   * Le nom de la formation
   */
  private String nomFormation;
  /**
   * Le nom du responssable de la formation
   */
  private String nomResponsable;
  /**
   * L'email du responsable de formation
   */
  private String email;
  /**
   * Les groupes de TD
   */
  private Map<Integer, Set<Etudiant>> tds = new HashMap<>();
  /**
   * Les groupes de TP
   */
  private Map<Integer, Set<Etudiant>> tps = new HashMap<>();
  /**
   * La gestion des étudiants
   */
  private GestionEtudiant gestionEtudiant = new GestionEtudiant();
  /**
   * La taille des groupes de TD
   */
  private int tailleGroupeDirige = -1;
  /**
   * La taille des groupes de TP
   */
  private int tailleGroupePratique = -1;
  /**
   * Le nombre d'option que les étusiants doivent avoir
   */
  private int NBoption = -1;

  /**
   * Instancie la Formation
   */
  public GestionFormation() {

  }

  /**
   * Permet de vérifier si une chaîne de caractères est une adresse e-mail valide.
   * 
   * @param email Chaîne de caractères représentant une adresse e-mail.
   * @return
   *         <ul>
   *         <li>true si la chaîne est une adresse e-mail valide.</li>
   *         <li>false sinon.</li>
   *         </ul>
   */
  public boolean isValidEmail(String email) {
    if (email != null) {
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
    } else {
      return false;
    }
  }

  /**
   * Renvoi la gestion des étudiants
   * 
   * @return la gestion des étudiants
   */
  public GestionEtudiant getGestionEtudiant() {
    return this.gestionEtudiant;
  }

  /**
   * Renvoi le nombre d'options
   * 
   * @return le nombre d'options
   */
  public int getNBoption() {
    return this.NBoption;
  }

  /**
   * Permet de définir le nombre d'option d'un étudiant
   * 
   * @param etu
   */
  public void setNbOptionEtudiant(Etudiant etu) {
    if (etu != null) {
      etu.setNbOption(this.NBoption);
    }
  }

  /**
   * Renvoi la map des groupe de TD
   * 
   * @return map des groupe de TD
   */
  public Map<Integer, Set<Etudiant>> getTds() {
    return this.tds;
  }

  /**
   * Renvoi la map des groupe de TP
   * 
   * @return map des groupe de TP
   */
  public Map<Integer, Set<Etudiant>> getTps() {
    return this.tps;
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
  public void creerFormation(String nomFormation, String nomResponsable,
      String email) {
    if (nomFormation != null && !nomFormation.isEmpty() && nomResponsable != null && !nomResponsable.isEmpty()
        && email != null && this.isValidEmail(email)) {
      this.nomFormation = nomFormation;
      this.nomResponsable = nomResponsable;
      this.email = email;
      this.tds.clear();
      this.tps.clear();
      this.gestionEtudiant = new GestionEtudiant();
      this.tailleGroupeDirige = -1;
      this.tailleGroupePratique = -1;
      this.NBoption = -1;
    }
  }

  /**
   * Renvoie le nom du responsable de formation. s
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
    if (ue != null) {
      if (!this.gestionEtudiant.getListeUE().contains(ue)) {
        ue.setOptionnel(false);
        this.gestionEtudiant.getListeUE().add(ue);
        return true;
      }
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
    if (ue != null && nbPlaces > 1) {
      if (!this.gestionEtudiant.getListeUE().contains(ue)) {
        ue.setOptionnel(true);
        ue.setNbPlacesMax(nbPlaces);
        this.gestionEtudiant.getListeUE().add(ue);
        return true;
      }
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
    if (this.NBoption == -1 && nombre >= 1) {
      this.NBoption = nombre;
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
    if (this.tailleGroupeDirige == -1 && taille > 1) {
      this.tailleGroupeDirige = taille;
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
    if (this.tailleGroupePratique == -1 && taille > 1) {
      this.tailleGroupePratique = taille;
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
    int a = this.gestionEtudiant.getListeEtudiants().size() / this.tailleGroupeDirige;
    int nombreGroupesTravauxDiriges = ((a * this.tailleGroupeDirige == this.gestionEtudiant.getListeEtudiants().size())
        ? a
        : a + 1);
    while (nombreGroupesTravauxDiriges != this.tds.size()) {
      this.tds.put(this.nombreGroupesTravauxDiriges() + 1, new HashSet<>());
    }
    a = this.gestionEtudiant.getListeEtudiants().size() / this.tailleGroupePratique;
    int nombreGroupesTravauxPratiques = ((a * this.tailleGroupePratique == this.gestionEtudiant.getListeEtudiants()
        .size()) ? a
            : a + 1);
    while (nombreGroupesTravauxPratiques != this.tps.size()) {
      this.tps.put(this.nombreGroupesTravauxPratiques() + 1, new HashSet<>());
    }
    int numeroGroupeTailleMin = 1;
    for (Etudiant etu : this.gestionEtudiant.getListeEtudiants()) {
      numeroGroupeTailleMin = 1;
      if (etu.getNumeroTd() == -1) {
        for (Map.Entry<Integer, Set<Etudiant>> entry : this.tds.entrySet()) {
          int key = entry.getKey();
          Set<Etudiant> value = entry.getValue();
          if (value.size() < this.tds.get(numeroGroupeTailleMin).size()) {
            numeroGroupeTailleMin = key;
          }
        }
        this.changerGroupe(etu, numeroGroupeTailleMin, 0);
      }
      numeroGroupeTailleMin = 1;
      if (etu.getNumeroTp() == -1) {
        for (Map.Entry<Integer, Set<Etudiant>> entry : this.tps.entrySet()) {
          int key = entry.getKey();
          Set<Etudiant> value = entry.getValue();
          if (value.size() < this.tps.get(numeroGroupeTailleMin).size()) {
            numeroGroupeTailleMin = key;
          }
        }
        changerGroupe(etu, 0, numeroGroupeTailleMin);
      }
    }
    this.homogenisation();
  }

  /**
   * Permet d'harmoniser les groupe en fonction du nombre d'élève dans les
   * groupes
   */
  public void homogenisation() {
    double nombreEtudiantParGroupeTd = (double) this.gestionEtudiant.getListeEtudiants().size()
        / this.nombreGroupesTravauxDiriges();
    double nombreEtudiantParGroupeTp = (double) this.gestionEtudiant.getListeEtudiants().size()
        / this.nombreGroupesTravauxPratiques();
    int numeroGroupeTailleMin = 1;
    int numeroGroupeTailleMax = 1;
    while (!interval(nombreEtudiantParGroupeTd, this.tds)) {
      for (Map.Entry<Integer, Set<Etudiant>> entry : this.tds.entrySet()) {
        int key = entry.getKey();
        Set<Etudiant> value = entry.getValue();
        if (value.size() <= this.tds.get(numeroGroupeTailleMin).size()) {
          numeroGroupeTailleMin = key;
        }
        if (value.size() >= this.tds.get(numeroGroupeTailleMax).size()) {
          numeroGroupeTailleMax = key;
        }
      }
      changerGroupe(this.tds.get(numeroGroupeTailleMax).stream().findFirst().orElse(null), numeroGroupeTailleMin, 0);
    }
    while (!interval(nombreEtudiantParGroupeTp, this.tps)) {
      for (Map.Entry<Integer, Set<Etudiant>> entry : this.tps.entrySet()) {
        int key = entry.getKey();
        Set<Etudiant> value = entry.getValue();
        if (value.size() <= this.tps.get(numeroGroupeTailleMin).size()) {
          numeroGroupeTailleMin = key;
        }
        if (value.size() >= this.tps.get(numeroGroupeTailleMax).size()) {
          numeroGroupeTailleMax = key;
        }
      }
      changerGroupe(this.tps.get(numeroGroupeTailleMax).stream().findFirst().orElse(null), 0, numeroGroupeTailleMin);
    }
  }

  /**
   * Permet de savoir si les groupe sont harmoniser
   * 
   * @param valeur Le nombre de perssone par groupe pour avoir un equilibre
   * @param mamap  la map de TD ou TP
   * @return
   *         <ul>
   *         <li>True si le nombre d'étudiant de chaque groupe est compris dans
   *         un interval de +1/-1 du parametre valeur</li>
   *         <li>False sinon</li>
   *         </ul>
   */
  public boolean interval(double valeur, Map<Integer, Set<Etudiant>> mamap) {
    if (mamap != null) {
      boolean res = true;
      for (Map.Entry<Integer, Set<Etudiant>> entry : mamap.entrySet()) {
        Set<Etudiant> value = entry.getValue();
        if (value.size() > valeur + 1 || value.size() < valeur - 1) {
          res = false;
          break;
        }
      }
      return res;
    } else {
      return false;
    }
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
    boolean td = true;
    boolean tp = true;
    if (etudiant != null) {
      int numgroupetp = etudiant.getNumeroTp();
      int numgroupetd = etudiant.getNumeroTd();
      if (groupeDirige != 0) {
        if (groupeDirige > 0 && groupeDirige <= this.getTds().size()) {
          if (groupeDirige != numgroupetd) {
            if (this.listeEtudiantsGroupeDirige(groupeDirige).size() < this.tailleGroupeDirige) {
              if (numgroupetd != -1) {
                this.listeEtudiantsGroupeDirige(numgroupetd).remove(etudiant);
                this.listeEtudiantsGroupeDirige(groupeDirige).add(etudiant);
                etudiant.setNumeroTd(groupeDirige);
                this.envoyermessage(etudiant, "changement de groupe", "changement de groupe :" + numgroupetd
                    + " ----> " + etudiant.getNumeroTd());
              } else {
                this.listeEtudiantsGroupeDirige(groupeDirige).add(etudiant);
                etudiant.setNumeroTd(groupeDirige);
                this.envoyermessage(etudiant, "nouveaux groupe", "nouveaux groupe :" + groupeDirige);
              }
            } else {
              td = false;
            }
          }
        } else {
          td = false;
        }
      }

      if (groupePratique != 0) {
        if (groupePratique > 0 && groupePratique <= this.getTps().size()) {
          if (groupePratique != numgroupetp) {
            if (this.listeEtudiantsGroupePratique(groupePratique).size() < this.tailleGroupePratique) {
              if (numgroupetp != -1) {
                this.tps.get(numgroupetp).remove(etudiant);
                this.tps.get(groupePratique).add(etudiant);
                etudiant.setNumeroTp(groupePratique);
                this.envoyermessage(etudiant, "changement de groupe", "changement de groupe :" + numgroupetp
                    + " ----> " + etudiant.getNumeroTp());
              } else {
                this.tps.get(groupePratique).add(etudiant);
                etudiant.setNumeroTp(groupePratique);
                this.envoyermessage(etudiant, "changement de groupe", "nouveaux groupe :" + groupePratique);
              }
            } else {
              tp = false;
            }
          }
        } else {
          tp = false;
        }
      }
    }
    return (td && tp) ? 0 : (!td && tp) ? -1 : (td && !tp) ? -2 : -3;
  }

  /**
   * Envoie un message à un etudiant
   * 
   * @param etu     L'Étudiant à qui envoyer le message
   * @param message Le message à envoyer
   */
  public void envoyermessage(Etudiant etu, String titre, String message) {
    if (etu != null && message != null && !message.isEmpty() && titre != null && !titre.isEmpty()) {
      Message mes = new Message(titre, message);
      etu.getMessages().add(mes);
    }
  }

  /**
   * Renvoie le nombre de groupes de TD actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TD
   */
  @Override
  public int nombreGroupesTravauxDiriges() {
    return this.tds.size();
  }

  /**
   * Renvoie le nombre de groupes de TP actuellement d�finis dans la formation.
   *
   * @return nombre de groupes de TP
   */
  @Override
  public int nombreGroupesTravauxPratiques() {
    return this.tps.size();
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
    return this.tds.get(groupe);
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
    return this.tps.get(groupe);
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
    if (option == null || !this.gestionEtudiant.getListeUE().contains(option) || !option.getOptionnel()) {
      return null;
    }
    Set<Etudiant> listeetu = new HashSet<>();
    for (Etudiant etu : this.gestionEtudiant.getListeEtudiants()) {
      for (UniteEnseignement ue : etu.getListeUEsuivies()) {
        if (ue.equals(option)) {
          listeetu.add(etu);
        }
      }
    }
    return listeetu;
  }

  /**
   * Sauvegarde toutes les donn�es de la formation : liste des UEs, des
   * �tudiants et des groupes.
   *
   * @param nomFichier le fichier dans lequel sauvegarder les donn�es
   * @throws IOException en cas de probl�me de sauvegarde
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    try (FileOutputStream fileOut = new FileOutputStream("save" + File.separator + nomFichier);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(this);
    } catch (IOException e) {
      System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
      throw e;
    }
  }

  /**
   * Charge les donn�es de la formation (UEs, �tudiants, groupes) � partir d'un
   * fichier.
   *
   * @param nomFichier le fichier dans lequel les donn�es ont �t� sauvegard�es
   * @throws IOException en cas de probl�me de chargement
   */
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save" + File.separator + nomFichier))) {
      try {
        Object objet = ois.readObject();
        if (objet instanceof GestionFormation) {
          GestionFormation objetCharge = (GestionFormation) objet;
          // Copier les propriétés de l'objet chargé dans l'instance courante
          try {
            this.copierDepuis(objetCharge);
          } catch (CloneNotSupportedException e) {
            e.printStackTrace();
          }
        } else {
          System.err.println("Le fichier ne contient pas une instance de VotreClasse");
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Copie un autre formation et la remplace dans this (clonage inverse)
   * 
   * @param autreFormation Une autre Formation
   * @throws CloneNotSupportedException en cas de problème de clonage
   */
  public void copierDepuis(GestionFormation autreFormation) throws CloneNotSupportedException {
    this.nomFormation = autreFormation.getNomFormation();
    this.nomResponsable = autreFormation.getNomResponsableFormation();
    this.email = autreFormation.getEmailResponsableFormation();
    this.tailleGroupeDirige = autreFormation.getTailleGroupeDirige();
    this.tailleGroupePratique = autreFormation.getTailleGroupePratique();
    this.NBoption = autreFormation.getNBoption();
    this.gestionEtudiant = autreFormation.getGestionEtudiant().clone();
    Map<Integer, Set<Etudiant>> clonedTds = new HashMap<>();
    for (Etudiant etu : this.getGestionEtudiant().getListeEtudiants()) {
      if (etu.getNumeroTd() != -1) {
        if (clonedTds.containsKey(etu.getNumeroTd())) {
          clonedTds.get(etu.getNumeroTd()).add(etu);
        } else {
          clonedTds.put(etu.getNumeroTd(), new HashSet<>());
          clonedTds.get(etu.getNumeroTd()).add(etu);
        }
      }
    }
    this.tds = clonedTds;
    Map<Integer, Set<Etudiant>> clonedTps = new HashMap<>();
    for (Etudiant etu : this.getGestionEtudiant().getListeEtudiants()) {
      if (etu.getNumeroTp() != -1) {
        if (clonedTps.containsKey(etu.getNumeroTp())) {
          clonedTps.get(etu.getNumeroTp()).add(etu);
        } else {
          clonedTps.put(etu.getNumeroTp(), new HashSet<>());
          clonedTps.get(etu.getNumeroTp()).add(etu);
        }
      }
    }
    this.tps = clonedTps;
  }

  /**
   * Retourne une représentation textuelle de l'objet GestionFormation.
   * Cette représentation inclut les détails tels que le nom de la formation, le
   * nom du responsable,
   * l'adresse e-mail, les travaux dirigés (tds), les travaux pratiques (tps),
   * la gestion des étudiants, la taille du groupe dirigé, la taille du groupe
   * pratique
   * et le nombre d'options disponibles.
   *
   * @return Une chaîne de caractères représentant l'objet GestionFormation.
   */
  @Override
  public String toString() {
    return "GestionFormation [nomFormation=" + nomFormation + ", nomResponsable=" + nomResponsable + ", email=" + email
        + ", tds=" + tds + ", tps=" + tps + ", gestionEtudiant=" + gestionEtudiant + ", tailleGroupeDirige="
        + tailleGroupeDirige + ", tailleGroupePratique=" + tailleGroupePratique + ", NBoption=" + NBoption + "]";
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
    if (nomFormation == null) {
      if (other.nomFormation != null)
        return false;
    } else if (!nomFormation.equals(other.nomFormation))
      return false;
    if (nomResponsable == null) {
      if (other.nomResponsable != null)
        return false;
    } else if (!nomResponsable.equals(other.nomResponsable))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (tds == null) {
      if (other.tds != null)
        return false;
    } else if (!tds.equals(other.tds))
      return false;
    if (tps == null) {
      if (other.tps != null)
        return false;
    } else if (!tps.equals(other.tps))
      return false;
    if (gestionEtudiant == null) {
      if (other.gestionEtudiant != null)
        return false;
    } else if (!gestionEtudiant.equals(other.gestionEtudiant))
      return false;
    if (tailleGroupeDirige != other.tailleGroupeDirige)
      return false;
    if (tailleGroupePratique != other.tailleGroupePratique)
      return false;
    if (NBoption != other.NBoption)
      return false;
    return true;
  }

}
