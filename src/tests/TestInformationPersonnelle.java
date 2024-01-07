package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.InformationPersonnelle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.InformationPersonnelle
 * InformationPersonnelle}.
 *
 * @author Eric Cariou
 * @see formation.InformationPersonnelle
 */
class TestInformationPersonnelle {
  
  /**
   * Une information basique : pr�nom et nom.
   */
  private InformationPersonnelle infoBasique;
  /**
   * Une information compl�te : pr�nom, nom, adresse et age.
   */
  private InformationPersonnelle infoComplete;
  
  /**
   * Instancie une information basique et une compl�te pour les tests.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoBasique = new InformationPersonnelle("Skywalker", "Luke");
    infoComplete =
        new InformationPersonnelle("Skywalker", "Luke", "Plan�te Tatooine", 20);
  }
  
  /**
   * Ne fait rien apr�s les tests : � modifier au besoin.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * V�rifie que l'on peut positionner un age de 25 ans.
   */
  @Test
  void testAge25Basique() {
    infoBasique.setAge(25);
    assertEquals(infoBasique.getAge(), 25);
  }
  
  /**
   * V�rifie qu'on ne peut pas positionner un age n�gatif sur une information
   * basique.
   */
  @Test
  void testAgeNegatifBasique() {
    infoBasique.setAge(-20);
    assertTrue(infoBasique.getAge() != -20);
  }
  
  /**
   * V�rifie qu'on ne peut pas positionner un age n�gatif sur une information
   * compl�te : l'�ge reste le m�me qu'avant.
   */
  @Test
  void testAgeNegatifComplet() {
    int age = infoComplete.getAge();
    infoComplete.setAge(-20);
    assertEquals(infoComplete.getAge(), age);
  }
  
  
  /**
   * V�rifie qu'une adresse n'est pas null quand on cr�e une information
   * personnelle.
   */
  @Test
  void testAdresseNonNull() {
    assertTrue(infoBasique.getAdresse() != null);
    assertTrue(infoComplete.getAdresse() != null);
  }
  
  /**
   * V�rifie qu'on ne peut pas positionner une adresse null sur une information
   * existante.
   */
  @Test
  void testSetterAdresseNull() {
    infoComplete.setAdresse(null);
    assertTrue(infoComplete.getAdresse() != null);
  }
  
  /**
   * V�rifie que les param�tres des constructeurs sont correctement g�r�s.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle inf =new InformationPersonnelle("Vador", "Dark", "", 30);
    assertEquals(inf.getNom(), "Vador");
    assertEquals(inf.getPrenom(), "Dark");
    assertTrue(inf.getAdresse() != null);
    assertTrue(inf.getAge() >= 0);
  }
  
}
