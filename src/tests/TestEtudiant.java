package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


/**
 * Tests JUnit de la classe {@link formation.Etudiant
 * InformationPersonnelle}.
 *
 * @see formation.Etudiant
 */
class TestEtudiant {
  private InformationPersonnelle infoPerso1;
  private InformationPersonnelle infoPerso2;
  private Etudiant etudiant1;
  private Etudiant etudiant2;
  private Etudiant etudiant3;
  private Set<UniteEnseignement> listeUE = new HashSet<>();
  private Set<UniteEnseignement> listeUEO = new HashSet<>();
  private Set<UniteEnseignement> listeUEF = new HashSet<>();

  /**
   * Instancie une information basique et une compl�te pour les tests.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @BeforeEach
  void setUp() throws Exception {
    // instance InformationPersonnelle
    this.infoPerso1 = new InformationPersonnelle("Skywalker", "Luke");
    this.infoPerso2 = new InformationPersonnelle("Skywalker", "Luke", "Plan�te Tatooine", 20);
    // instance Etudiant
    this.etudiant1 = new Etudiant();
    this.etudiant2 = new Etudiant(infoPerso2, "Lukepassword");
    this.etudiant3 = new Etudiant(infoPerso1, "LukeJum");
    // connexion d'un Etudiant
    this.etudiant3.connexion(2, "LukeJum");
    // instance UniteEnseignement
    UniteEnseignement ue1 = new UniteEnseignement("UE1", "Enseignant1", 30);
    UniteEnseignement ue2 = new UniteEnseignement("UE2", "Enseignant2", 25);
    UniteEnseignement ue3 = new UniteEnseignement("UE3", "Enseignant3");
    // ajout liste UE
    this.listeUE.add(ue1);
    this.listeUE.add(ue2);
    this.listeUE.add(ue3);
    // ajout liste UE obligatoire
    this.listeUEO.add(ue3);
    // ajout liste UE facultatives
    this.listeUEF.add(ue1);
    this.listeUEF.add(ue2);
    this.etudiant3.setListeUE(listeUE);
  }

  /**
   * Ne fait rien apr�s les tests : � modifier au besoin.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @AfterEach
  void tearDown() throws Exception {
  }

  /**
   * V�rifie que l'on peut s'inscrire avec un mot de passe et des
   * InformationPersonnelle.
   */
  @Test
  void testInscriptionBasique() {
    int inscription = this.etudiant1.inscription(this.infoPerso1, "Sk1525mlds");
    assertEquals(inscription, 3);
  }

  /**
   * V�rifie que l'on ne peut pas s'inscrire avec des infos personnelles vides
   */
  @Test
  void testInscriptionInfoPersoVide() {
    int inscription = this.etudiant2.inscription(null, "Sk1525mlds");
    assertEquals(inscription, -1);
  }

  /**
   * V�rifie que l'on ne peut pas s'inscrire avec un mot de passe vide
   */
  @Test
  void testInscriptionMotDePasseVide() {
    int inscription = this.etudiant2.inscription(this.infoPerso1, "");
    assertEquals(inscription, -1);
  }

  /**
   * V�rifie la connexion avec un numéro et mot de passe correct
   */
  @Test
  void testConnexionBasique() {
    boolean connexion = this.etudiant2.connexion(8, "Lukepassword");
    assertEquals(connexion, true);
  }

  /**
   * V�rifie que l'on ne peut pas se connecter avec un mot de passe incorrect
   */
  @Test
  void testConnexionMotDePasseIncorrect() {
    boolean connexion = this.etudiant2.connexion(8, "Lucepassword");
    assertEquals(connexion, false);
  }

  /**
   * V�rifie que l'on ne peut pas se connecter avec un numéro incorrect
   */
  @Test
  void testConnexionNumeroIncorrect() {
    boolean connexion = this.etudiant2.connexion(15, "Lukepassword");
    assertEquals(connexion, false);
  }

  /**
   * V�rifie le bon fonctionnement de la deconnexion
   */
  @Test
  void testDeconnexion() {
    try {
      this.etudiant3.deconnexion();
      assertTrue(true);
    } catch (NonConnecteException e) {
      assertTrue(false);
    }
  }

  /**
   * V�rifie que l'on retourne une liste comportant tout les enseignements
   * obligatoires
   */
  @Test
  void testEnseignementObligatoires() {
    Set<UniteEnseignement> lsUEO = this.etudiant3.enseignementsObligatoires();
    assertEquals(lsUEO, this.listeUEO);
  }

  /**
   * V�rifie que l'on retourne une liste comportant tout les enseignements
   * optionnels
   */
  @Test
  void testEnseignementOptionnels() {
    Set<UniteEnseignement> lsUEF = this.etudiant3.enseignementsOptionnels();
    assertEquals(lsUEF, this.listeUEF);
  }

  /**
   * V�rifie que l'on retourne le nombre d'options correcte
   *
   */
  @Test
  void testNombreOptionsBasique() {
    this.etudiant3.setNbOption(2);
    try {
      int nbOptions = this.etudiant3.nombreOptions();
      assertEquals(nbOptions, 2);
    } catch (NonConnecteException e) {
      assertTrue(false);
    }
  }

  /**
   * V�rifie que l'on retourne -1 avec le nombre d'options pas d�finis
   *
   */
  @Test
  void testNombreOptionsPasDefinis() {
    try {
      int nbOptions = this.etudiant3.nombreOptions();
      assertEquals(nbOptions, -1);
    } catch (NonConnecteException e) {
      assertTrue(false);
    }
  }

  /**
   * V�rifie que les param�tres des constructeurs sont correctement g�r�s.
   */
  @Test
  void testConstructeur() {
    Etudiant etudiant = new Etudiant(this.infoPerso2, "Skypassword");
  }

}
