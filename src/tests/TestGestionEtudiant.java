package tests;

import formation.Etudiant;
import formation.GestionEtudiant;
import formation.InformationPersonnelle;
import formation.Message;
import formation.NonConnecteException;
import formation.UniteEnseignement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit de la classe {@link formation.GestionEtudiant}.
 *
 * Cette classe contient des méthodes de test pour vérifier le bon
 * fonctionnement des fonctionnalités de la classe
 * {@link formation.GestionEtudiant}.
 *
 * @author LE BRAS Erwan
 * @see formation.GestionEtudiant
 */
class TestGestionEtudiant {
  
  /**
   * Une gestion d'étudiants.
   */
  private GestionEtudiant gestionEtudiant;
  
  /**
   * Un numéro étudiant.
   */
  private int numero;
  
  /**
   * Des informations Personnelles.
   */
  private InformationPersonnelle infoPerso1;
  
  /**
   * Une information Personnelle.
   */
  private InformationPersonnelle infoPerso2;
  
  /**
   * Une liste d'unités d'enseignements.
   */
  private final Set<UniteEnseignement> listeUE = new HashSet<>();
  
  /**
   * Une liste d'unités d'enseignements obligatoire.
   */
  private final Set<UniteEnseignement> listeUEO = new HashSet<>();
  
  /**
   * Une liste d'unités d'enseignements optionnelle.
   */
  private final Set<UniteEnseignement> listeUEF = new HashSet<>();
  
  /**
   * Une uniteEnseignement optionnelle.
   */
  private UniteEnseignement ue1;
  
  /**
   * Une uniteEnseignement optionnelle.
   */
  private UniteEnseignement ue2;
  
  /**
   * Une uniteEnseignement obligatoire.
   */
  private UniteEnseignement ue3;
  
  /**
   * Une uniteEnseignement obligatoire.
   */
  private UniteEnseignement ue4;
  
  /**
   * Instancie une gestion d'étudiants, différentes informations personnelles,
   * divers unité ensignements, la liste d'unité enseignement obligatoire et
   * optionnelle.
   *
   */
  @BeforeEach
  void setUp() throws Exception {
    this.gestionEtudiant = new GestionEtudiant();
    
    this.infoPerso1 = new InformationPersonnelle("Skywalker", "Luke");
    this.infoPerso2 =
        new InformationPersonnelle("Skywalker", "Luke", "Planète Tatooine", 20);
    
    // Initialisation des unités enseignements
    this.ue1 = new UniteEnseignement("UE1", "Enseignant1");
    this.ue1.setOptionnel(true);
    this.ue1.setNbPlacesMax(24);
    this.ue2 = new UniteEnseignement("UE2", "Enseignant2");
    this.ue2.setOptionnel(true);
    this.ue2.setNbPlacesMax(25);
    this.ue3 = new UniteEnseignement("UE3", "Enseignant3");
    this.ue3.setOptionnel(false);
    this.ue3.setNbPlacesMax(26);
    this.ue4 = new UniteEnseignement("UE4", "Enseignant4");
    this.ue4.setOptionnel(false);
    this.ue4.setNbPlacesMax(27);
    
    // Initialisation de la liste d'unité enseignement
    this.listeUE.add(this.ue1);
    this.listeUE.add(this.ue2);
    this.listeUE.add(this.ue3);
    this.listeUE.add(this.ue4);
    
    // Initialisation de la liste d'unité enseignement obligatoire et
    // optionnelle
    this.listeUEF.add(this.ue1);
    this.listeUEF.add(this.ue2);
    this.listeUEO.add(this.ue3);
    this.listeUEO.add(this.ue4);
    
    this.gestionEtudiant.setListeUE(listeUE);
    this.numero = gestionEtudiant.inscription(infoPerso1, "Sk1525mlds");
    this.gestionEtudiant.inscription(infoPerso2, "azerty");
  }
  
  /**
   * Méthode de nettoyage après chaque test.
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Test de la méthode inscription
   * {@link GestionEtudiant#inscription(InformationPersonnelle, String)} avec
   * des valeurs basiques. Vérifie que la méthode renvoie bien le numéro de
   * l'étudiant crée.
   * 
   * @see GestionEtudiant#inscription(InformationPersonnelle, String)
   */
  @Test
  void testInscriptionBasique() {
    int inscription =
        this.gestionEtudiant.inscription(infoPerso1, "motDePasseDeLuke");
    assertEquals(2, inscription);
  }
  
  /**
   * Test de la méthode inscription
   * {@link GestionEtudiant#inscription(InformationPersonnelle, String)} des
   * informations personnelles null. Vérifie que la méthode renvoie bien -1.
   * 
   * @see GestionEtudiant#inscription(InformationPersonnelle, String)
   */
  @Test
  void testInscriptionInfoPersoVide() {
    int inscription = this.gestionEtudiant.inscription(null, "Sk1525mlds");
    assertEquals(-1, inscription);
  }
  
  /**
   * Test de la méthode inscription
   * {@link GestionEtudiant#inscription(InformationPersonnelle, String)} avec un
   * mot de passe vide. Vérifie que la méthode renvoie bien -1.
   * 
   * @see GestionEtudiant#inscription(InformationPersonnelle, String)
   */
  @Test
  void testInscriptionMotDePasseVide() {
    int inscription = this.gestionEtudiant.inscription(infoPerso1, "");
    assertEquals(-1, inscription);
  }
  
  /**
   * Test de la méthode connexion {@link GestionEtudiant#connexion(int, String)}
   * avec des valeurs basiques. Vérifie que la méthode renvoie bien true.
   * 
   * @see GestionEtudiant#connexion(int, String)
   */
  @Test
  void testConnexionBasique() {
    boolean connexion = this.gestionEtudiant.connexion(0, "Sk1525mlds");
    assertTrue(connexion);
  }
  
  /**
   * Test de la méthode connexion {@link GestionEtudiant#connexion(int, String)}
   * avec un mot de passe incorrect. Vérifie que la méthode renvoie bien false.
   * 
   * @see GestionEtudiant#connexion(int, String)
   */
  @Test
  void testConnexionMotDePasseIncorrect() {
    boolean connexion = this.gestionEtudiant.connexion(0, "Lukepassword");
    assertFalse(connexion);
  }
  
  /**
   * Test de la méthode connexion {@link GestionEtudiant#connexion(int, String)}
   * avec un numero incorrect. Vérifie que la méthode renvoie bien false.
   * 
   * @see GestionEtudiant#connexion(int, String)
   */
  @Test
  void testConnexionNumeroIncorrect() {
    boolean connexion = this.gestionEtudiant.connexion(15, "Sk1525mlds");
    assertFalse(connexion);
  }
  
  /**
   * Test de la méthode deconnexion {@link GestionEtudiant#deconnexion()} avec
   * un utilisateur connecté. Vérifie que la méthode déconnecte bien
   * l'utilisateur.
   * 
   * @see GestionEtudiant#deconnexion()
   */
  @Test
  void testDeconnexionBasique() {
    try {
      this.gestionEtudiant.connexion(0, "Sk1525mlds");
      this.gestionEtudiant.deconnexion();
      assertTrue(true);
    } catch (NonConnecteException e) {
      fail("La déconnexion ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test de la méthode deconnexion {@link GestionEtudiant#deconnexion()} sans
   * utilisateur connecté. Vérifie que la méthode renvoie bien
   * NonConnecteException.
   * 
   * @see GestionEtudiant#deconnexion()
   */
  @Test
  void testDeconnexionSansUtilisateurConnecte() {
    try {
      this.gestionEtudiant.deconnexion();
      assertTrue(false);
    } catch (NonConnecteException e) {
      assertTrue(true);
    }
  }
  
  /**
   * Test de la méthode getEtudiantConnecte
   * {@link GestionEtudiant#getEtudiantConnecte()} avec un utilisateur connecté.
   * Vérifie que la méthode renvoie bien l'etudiant.
   * 
   * @see GestionEtudiant#getEtudiantConnecte()
   */
  @Test
  void testEtudiantConnecteBasique() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      Etudiant etudiant = this.gestionEtudiant.getEtudiantConnecte();
      assertTrue(etudiant.getNumero() == this.numero
          && etudiant.getInformationPersonnelle().equals(this.infoPerso1));
    } catch (NonConnecteException e) {
      fail("La déconnexion ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test de la méthode getEtudiantConnecte
   * {@link GestionEtudiant#getEtudiantConnecte()} avec un utilisateur pas
   * connecté. Vérifie que la méthode renvoie NonConnecteException.
   * 
   * @see GestionEtudiant#getEtudiantConnecte()
   */
  @Test
  void testEtudiantConnecteSansUtilisateurConnecte() {
    try {
      this.gestionEtudiant.getEtudiantConnecte();
      assertTrue(false);
    } catch (NonConnecteException e) {
      assertTrue(true);
    }
  }
  
  /**
   * Test de la méthode getListeEtudiants
   * {@link GestionEtudiant#getListeEtudiants()} avec deux étudiants dans la
   * liste. Vérifie que la méthode renvoie bien la liste des étudiants.
   * 
   * @see GestionEtudiant#getListeEtudiants()
   */
  @Test
  void testListeEtudiants() {
    Set<Etudiant> lsEtudiants = this.gestionEtudiant.getListeEtudiants();
    assertEquals(lsEtudiants.size(), 2);
  }
  
  /**
   * Test de la méthode enseignementsObligatoires
   * {@link GestionEtudiant#enseignementsObligatoires()} avec 2 ue obligatoires
   * dans la liste. Vérifie que la méthode renvoie bien la liste des ue
   * obligatoires.
   * 
   * @see GestionEtudiant#enseignementsObligatoires()
   */
  @Test
  void testEnseignementObligatoires() {
    Set<UniteEnseignement> lsUEO =
        this.gestionEtudiant.enseignementsObligatoires();
    assertEquals(this.listeUEO, lsUEO);
  }
  
  /**
   * Test de la méthode enseignementsOptionnels
   * {@link GestionEtudiant#enseignementsOptionnels()} avec 2 ue optionnels dans
   * la liste. Vérifie que la méthode renvoie bien la liste des ue optionnels.
   * 
   * @see GestionEtudiant#enseignementsOptionnels()
   */
  @Test
  void testEnseignementOptionnels() {
    Set<UniteEnseignement> lsUEF =
        this.gestionEtudiant.enseignementsOptionnels();
    assertEquals(this.listeUEF, lsUEF);
  }
  
  /**
   * Test de la méthode getListeUE {@link GestionEtudiant#getListeUE()} avec 4
   * ue dans la liste. Vérifie que la méthode renvoie bien la liste des ue.
   * 
   * @see GestionEtudiant#getListeUE()
   */
  @Test
  void testGetListeUE() {
    Set<UniteEnseignement> lsUE = this.gestionEtudiant.getListeUE();
    assertEquals(this.listeUE, lsUE);
  }
  
  /**
   * Test de la méthode setListeUE {@link GestionEtudiant#setListeUE()} avec 3
   * ue dans la liste. Vérifie que la méthode ajoute bien les 3 ue dans la
   * liste.
   * 
   * @see GestionEtudiant#setListeUE()
   */
  @Test
  void testSetListeUE() {
    Set<UniteEnseignement> listeUE = new HashSet<>();
    listeUE.add(this.ue1);
    listeUE.add(this.ue2);
    listeUE.add(this.ue3);
    this.gestionEtudiant.setListeUE(listeUE);
    assertEquals(listeUE, this.gestionEtudiant.getListeUE());
  }
  
  /**
   * Test de la méthode setNbOption {@link GestionEtudiant#setNbOption()} avec
   * une valeur de 2 et la méthode nombreOptions
   * {@link GestionEtudiant#nombreOptions()}. Vérifie que la méthode setNbOption
   * définis bien 2 et que nombreOptions renvoie 2.
   * 
   * @see GestionEtudiant#setNbOption()
   * @see GestionEtudiant#nombreOptions()
   */
  @Test
  void testNombreOptionsBasique() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      this.gestionEtudiant.setNbOption(2);
      int nbOptions = this.gestionEtudiant.nombreOptions();
      assertEquals(2, nbOptions);
    } catch (NonConnecteException e) {
      fail("La méthode nombreOptions ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test la méthode nombreOptions {@link GestionEtudiant#nombreOptions()} avec
   * une valeur non définis. Vérifie que la méthode renvoie bien -1.
   * 
   * @see GestionEtudiant#nombreOptions()
   */
  @Test
  void testNombreOptionsPasDefinis() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      int nbOptions = this.gestionEtudiant.nombreOptions();
      assertEquals(-1, nbOptions);
    } catch (NonConnecteException e) {
      fail("La méthode nombreOptions ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test la méthode choisirOption {@link GestionEtudiant#choisirOption()} avec
   * ue basique optionnel. Vérifie que la méthode renvoie true.
   * 
   * @see GestionEtudiant#choisirOption()
   */
  @Test
  void testChoisirOptionBasique() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      boolean res = this.gestionEtudiant.choisirOption(this.ue1);
      assertTrue(res);
    } catch (NonConnecteException e) {
      fail("La méthode choisirOption ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test la méthode choisirOption {@link GestionEtudiant#choisirOption()} avec
   * une ue obligatoire. Vérifie que la méthode renvoie false.
   * 
   * @see GestionEtudiant#choisirOption()
   */
  @Test
  void testChoisirOptionUeObligatoire() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      boolean res = this.gestionEtudiant.choisirOption(this.ue4);
      assertFalse(res);
    } catch (NonConnecteException e) {
      fail("La méthode choisirOption ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test la méthode choisirOption {@link GestionEtudiant#choisirOption()} avec
   * une ue optionnel déja présente. Vérifie que la méthode renvoie false.
   * 
   * @see GestionEtudiant#choisirOption()
   */
  @Test
  void testChoisirOptionDejaPresente() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      this.gestionEtudiant.choisirOption(this.ue1);
      boolean res = this.gestionEtudiant.choisirOption(this.ue1);
      assertFalse(res);
    } catch (NonConnecteException e) {
      fail("La méthode choisirOption ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique de la méthode getNumeroGroupeTravauxDiriges
   * {@link GestionEtudiant#getNumeroGroupeTravauxDiriges()} et de la méthode
   * setNumeroTd {@link GestionEtudiant#setNumeroTd()}. Vérifie que la méthode
   * renvoie le bon numéro de td.
   * 
   * @see GestionEtudiant#getNumeroGroupeTravauxDiriges()
   * @see GestionEtudiant#setNumeroTd()
   */
  @Test
  void testNumeroGroupeTdBasique() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      this.gestionEtudiant.setNumeroTd(2);
      int res = this.gestionEtudiant.getNumeroGroupeTravauxDiriges();
      assertEquals(2, res);
    } catch (NonConnecteException e) {
      fail(
          "La méthode setNumeroTd et getNumeroGroupeTravauxDiriges ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique de la méthode getNumeroGroupeTravauxPratiques
   * {@link GestionEtudiant#getNumeroGroupeTravauxPratiques()} et de la méthode
   * setNumeroTp {@link GestionEtudiant#setNumeroTp()}. Vérifie que la méthode
   * renvoie le bon numéro de tp.
   * 
   * @see GestionEtudiant#getNumeroGroupeTravauxPratiques()
   * @see GestionEtudiant#setNumeroTp()
   */
  @Test
  void testNumeroGroupeTpBasique() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.setNumeroTp(3);
      int res = this.gestionEtudiant.getNumeroGroupeTravauxPratiques();
      assertEquals(3, res);
    } catch (NonConnecteException e) {
      fail(
          "La méthode setNumeroTp et getNumeroGroupeTravauxPratiques ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test de la méthode getNumeroGroupeTravauxDiriges
   * {@link GestionEtudiant#getNumeroGroupeTravauxDiriges()} avec le numéro non
   * défini. Vérifie que la méthode renvoie -1.
   * 
   * @see GestionEtudiant#getNumeroGroupeTravauxDiriges()
   */
  @Test
  void testNumeroGroupeTdNonDefini() {
    try {
      this.gestionEtudiant.connexion(this.numero, "Sk1525mlds");
      int res = this.gestionEtudiant.getNumeroGroupeTravauxDiriges();
      assertEquals(-1, res);
    } catch (NonConnecteException e) {
      fail(
          "La méthode setNumeroTd et getNumeroGroupeTravauxDiriges ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique de la méthode getNumeroGroupeTravauxPratiques
   * {@link GestionEtudiant#getNumeroGroupeTravauxPratiques()} avec le numéro
   * non défini. Vérifie que la méthode renvoie -1.
   * 
   * @see GestionEtudiant#getNumeroGroupeTravauxPratiques()
   */
  @Test
  void testNumeroGroupeTpNonDefini() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      int res = this.gestionEtudiant.getNumeroGroupeTravauxPratiques();
      assertEquals(-1, res);
    } catch (NonConnecteException e) {
      fail(
          "La méthode setNumeroTp et getNumeroGroupeTravauxPratiques ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique de la méthode enseignementsSuivis
   * {@link GestionEtudiant#enseignementsSuivis()}. Vérifie que la méthode
   * renvoie les enseignements suivies.
   * 
   * @see GestionEtudiant#enseignementsSuivis()
   */
  @Test
  void testEnseignementsSuivisBasique() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.choisirOption(this.ue1);
      this.gestionEtudiant.choisirOption(this.ue2);
      this.gestionEtudiant.getEtudiantConnecte().addUE(this.ue3);
      this.gestionEtudiant.getEtudiantConnecte().addUE(this.ue4);
      assertEquals(this.listeUE, this.gestionEtudiant.enseignementsSuivis());
    } catch (NonConnecteException e) {
      fail(
          "La méthode enseignementsSuivis ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test liste vide de la méthode enseignementsSuivis
   * {@link GestionEtudiant#enseignementsSuivis()}. Vérifie que la méthode
   * renvoie bien une liste vide.
   * 
   * @see GestionEtudiant#enseignementsSuivis()
   */
  @Test
  void testEnseignementsSuivisVide() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      Set<UniteEnseignement> ue = this.gestionEtudiant.enseignementsSuivis();
      assertEquals(new HashSet<UniteEnseignement>(), ue);
    } catch (NonConnecteException e) {
      fail(
          "La méthode enseignementsSuivis ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique méthode listeTousMessages
   * {@link GestionEtudiant#listeTousMessages()}. Vérifie que la méthode renvoie
   * bien la liste des messages.
   * 
   * @see GestionEtudiant#listeTousMessages()
   */
  @Test
  void testlisteTousMessages() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      List<Message> messages = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
        Message message = new Message("Titre" + i, "Contenu");
        this.gestionEtudiant.getEtudiantConnecte().getMessages().add(message);
        messages.add(message);
      }
      
      List<String> titres = new ArrayList<>();
      for (Message message : messages) {
        titres.add(message.getTitre());
      }
      
      assertEquals(titres, this.gestionEtudiant.listeTousMessages());
    } catch (NonConnecteException e) {
      fail(
          "La méthode listeTousMessages ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test méthode listeTousMessages {@link GestionEtudiant#listeTousMessages()},
   * suppression d'un element. Vérifie que la méthode renvoie une liste
   * différente de celle qui n'as pas été modifié.
   * 
   * @see GestionEtudiant#listeTousMessages()
   */
  @Test
  void testlisteTousMessagesDifferent() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      List<Message> messages = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
        Message message = new Message("Titre" + i, "Contenu");
        this.gestionEtudiant.getEtudiantConnecte().getMessages().add(message);
        messages.add(message);
      }
      
      List<String> titres = new ArrayList<>();
      for (Message message : messages) {
        titres.add(message.getTitre());
      }
      
      this.gestionEtudiant.getEtudiantConnecte().getMessages().remove(5);
      assertNotEquals(titres, this.gestionEtudiant.listeTousMessages());
      assertNotEquals(titres.size(), this.gestionEtudiant.listeTousMessages());
    } catch (NonConnecteException e) {
      fail(
          "La méthode listeTousMessages ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test basique de la méthode listeMessageNonLus
   * {@link GestionEtudiant#listeMessageNonLus()}. Vérifie que la méthode
   * renvoie bien la liste des messages non lus.
   * 
   * @see GestionEtudiant#listeMessageNonLus()
   */
  @Test
  void testlisteMessageNonLus() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      List<Message> messages = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
        Message message = new Message("Titre" + i, "Contenu");
        this.gestionEtudiant.getEtudiantConnecte().getMessages().add(message);
        if (i % 2 == 0) {
          message.setLu();
        } else {
          messages.add(message);
        }
        
      }
      
      List<String> titres = new ArrayList<>();
      for (Message message : messages) {
        titres.add(message.getTitre());
      }
      
      assertEquals(titres, this.gestionEtudiant.listeMessageNonLus());
    } catch (NonConnecteException e) {
      fail(
          "La méthode listeMessageNonLus ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test de la méthode listeMessageNonLus
   * {@link GestionEtudiant#listeMessageNonLus()}. Vérifie que la méthode
   * renvoie bien une liste de messages différente de l'autre liste.
   * 
   * @see GestionEtudiant#listeMessageNonLus()
   */
  @Test
  void testlisteMessageNonLusDifferent() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      List<Message> messages = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
        Message message = new Message("Titre" + i, "Contenu");
        this.gestionEtudiant.getEtudiantConnecte().getMessages().add(message);
        if (i % 2 == 0) {
          message.setLu();
        } else {
          messages.add(message);
        }
        
      }
      
      List<String> titres = new ArrayList<>();
      for (Message message : messages) {
        titres.add(message.getTitre());
      }
      
      titres.remove(2);
      
      assertNotEquals(titres, this.gestionEtudiant.listeMessageNonLus());
    } catch (NonConnecteException e) {
      fail(
          "La méthode listeMessageNonLus ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test d'inscriptionFinalisee {@link GestionEtudiant#inscriptionFinalisee()},
   * finalisation d'un etudiant basique. Vérifie que la méthode renvoie true.
   * 
   * @see GestionEtudiant#inscriptionFinalisee()
   */
  @Test
  void testInscriptionFinaliseeBasique() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.getEtudiantConnecte().setNbOption(2);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTd(1);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTp(3);
      this.gestionEtudiant.choisirOption(ue1);
      this.gestionEtudiant.choisirOption(ue2);
      assertTrue(this.gestionEtudiant.inscriptionFinalisee());
    } catch (NonConnecteException e) {
      fail(
          "La méthode inscriptionFinalisee ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test d'inscriptionFinalisee {@link GestionEtudiant#inscriptionFinalisee()},
   * finalisation d'un etudiant sans le nombre d'options. Vérifie que la méthode
   * renvoie false.
   * 
   * @see GestionEtudiant#inscriptionFinalisee()
   */
  @Test
  void testInscriptionFinaliseeSansNbOptions() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTd(1);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTp(3);
      this.gestionEtudiant.choisirOption(ue1);
      this.gestionEtudiant.choisirOption(ue2);
      assertFalse(this.gestionEtudiant.inscriptionFinalisee());
    } catch (NonConnecteException e) {
      fail(
          "La méthode inscriptionFinalisee ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test d'inscriptionFinalisee {@link GestionEtudiant#inscriptionFinalisee()},
   * finalisation d'un etudiant avec le numéro de td pas défini. Vérifie que la
   * méthode renvoie false.
   * 
   * @see GestionEtudiant#inscriptionFinalisee()
   */
  @Test
  void testInscriptionFinaliseeSansNumeroTd() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.getEtudiantConnecte().setNbOption(2);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTp(3);
      this.gestionEtudiant.choisirOption(ue1);
      this.gestionEtudiant.choisirOption(ue2);
      assertFalse(this.gestionEtudiant.inscriptionFinalisee());
    } catch (NonConnecteException e) {
      fail(
          "La méthode inscriptionFinalisee ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test d'inscriptionFinalisee {@link GestionEtudiant#inscriptionFinalisee()},
   * finalisation d'un etudiant avec le numéro de tp pas défini. Vérifie que la
   * méthode renvoie false.
   * 
   * @see GestionEtudiant#inscriptionFinalisee()
   */
  @Test
  void testInscriptionFinaliseeSansNumeroTp() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.getEtudiantConnecte().setNbOption(2);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTd(1);
      this.gestionEtudiant.choisirOption(ue1);
      this.gestionEtudiant.choisirOption(ue2);
      assertFalse(this.gestionEtudiant.inscriptionFinalisee());
    } catch (NonConnecteException e) {
      fail(
          "La méthode inscriptionFinalisee ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test d'inscriptionFinalisee {@link GestionEtudiant#inscriptionFinalisee()},
   * finalisation d'un etudiant qui n'as pas choisie toutes ses options. Vérifie
   * que la méthode renvoie false.
   * 
   * @see GestionEtudiant#inscriptionFinalisee()
   */
  @Test
  void testInscriptionFinaliseeManqueOption() {
    try {
      this.gestionEtudiant.connexion(1, "azerty");
      this.gestionEtudiant.getEtudiantConnecte().setNbOption(2);
      this.gestionEtudiant.getEtudiantConnecte().setNumeroTd(1);
      this.gestionEtudiant.choisirOption(ue1);
      assertFalse(this.gestionEtudiant.inscriptionFinalisee());
    } catch (NonConnecteException e) {
      fail(
          "La méthode inscriptionFinalisee ne devrait pas lever d'exception ici.");
    }
  }
  
  /**
   * Test de la méthode clone {@link GestionEtudiant#clone()} vérifie que la
   * méthode clone copie bien l'instance courante.
   * 
   * @see GestionEtudian#clone()
   */
  @Test
  void testClone() throws CloneNotSupportedException {
    GestionEtudiant clone = this.gestionEtudiant.clone();
    assertTrue(this.gestionEtudiant.equals(clone));
  }
}

