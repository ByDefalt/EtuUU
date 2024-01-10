package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import formation.UniteEnseignement;

/**
 * Tests JUnit de la classe {@link UniteEnseignement}.
 *
 * Cette classe contient des méthodes de test pour vérifier le bon fonctionnement
 * des fonctionnalités de la classe {@link UniteEnseignement}.
 * 
 * Le test porte sur la construction de l'objet {@link UniteEnseignement}, la gestion de l'optionnalité,
 * la récupération et modification des attributs (nom de l'UE, nombre de places maximum,
 * nom de l'enseignant, nombre de participants), et les méthodes hashCode et equals.
 * 
 * @author ROUSVAL ROMAIN
 * @see UniteEnseignement
 */
class TestInformationUniteEnseignement {

    private UniteEnseignement ue;

    /**
     * Méthode exécutée avant chaque test pour effectuer des initialisations.
     * @throws Exception si une exception survient lors de l'initialisation.
     */
    @BeforeEach
    void setUp() throws Exception {
        ue=new UniteEnseignement("prog","richard");
    }

    /**
     * Méthode exécutée après chaque test pour effectuer des nettoyages.
     */
    @AfterEach
    void tearDown() {
        // Ajoutez les nettoyages nécessaires après chaque test si nécessaire.
    }

    /**
     * Teste le constructeur de la classe {@link UniteEnseignement}.
     */
    @Test
    void testConstructeur() {
        UniteEnseignement ue2=new UniteEnseignement("prog","richard");
        assertEquals(ue2.getNomUE(),"prog");
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement("Informatique", null));
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement(null, "Informatique"));
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement(null, null));
    }

    /**
     * Teste la méthode {@link UniteEnseignement#getOptionnel()}.
     */
    @Test
    void testgetOptionnel(){
        assertFalse(ue.getOptionnel());
        ue.setOptionnel(true);
        assertTrue(ue.getOptionnel());
    }

    /**
     * Teste la méthode {@link UniteEnseignement#setOptionnel(boolean)}.
     */
    @Test
    void testsetOptionnel(){
        assertFalse(ue.getOptionnel());
        ue.setOptionnel(true);
        assertTrue(ue.getOptionnel());
    }

    /**
     * Teste la méthode {@link UniteEnseignement#getNomUE()}.
     */
    @Test
    void testgetNomUE(){
        assertEquals(ue.getNomUE(),"prog");
    }

    /**
     * Teste la méthode {@link UniteEnseignement#getNbPlacesMax()}.
     */
    @Test
    void testgetNbPlacesMax(){
        ue.setNbPlacesMax(12);
        assertNotEquals(ue.getNbPlacesMax(),12);
        ue.setOptionnel(true);
        ue.setNbPlacesMax(12);
        assertEquals(ue.getNbPlacesMax(),12);
    }

    /**
     * Teste la méthode {@link UniteEnseignement#getNomEnseignant()}.
     */
    @Test
    void testgetNomEnseignant(){
        assertEquals(ue.getNomEnseignant(), "richard");
    }

    /**
     * Teste la méthode {@link UniteEnseignement#setNomEnseignant(String)}.
     */
    @Test
    void testsetNomEnseignant(){
        assertEquals(ue.getNomEnseignant(), "richard");
        ue.setNomEnseignant("alex");
        assertEquals(ue.getNomEnseignant(), "alex");
        ue.setNomEnseignant(null);
        assertNotEquals(ue.getNomEnseignant(), null);
        ue.setNomEnseignant("");
        assertNotEquals(ue.getNomEnseignant(), "");
    }

    /**
     * Teste la méthode {@link UniteEnseignement#getnbParticipant()}.
     */
    @Test
    void testgetnbParticipant(){
        assertEquals(ue.getnbParticipant(), 0);
    }

    /**
     * Teste la méthode {@link UniteEnseignement#setnbParticipant()}.
     */
    @Test
    void testsetnbParticipant(){
        assertEquals(ue.getnbParticipant(), 0);
        ue.setnbParticipant();
        assertNotEquals(ue.getnbParticipant(), 1);
        ue.setOptionnel(true);
        ue.setnbParticipant();
        assertEquals(ue.getnbParticipant(), 1);
    }

    /**
     * Teste la méthode {@link UniteEnseignement#hashCode()}.
     */
    @Test
    void testhashCode(){
        UniteEnseignement ue2=new UniteEnseignement("prog", "lk");
        UniteEnseignement ue3=new UniteEnseignement("ocaml", "lk");
        assertEquals(ue.hashCode(), ue2.hashCode());
        assertNotEquals(ue.hashCode(), ue3.hashCode());
    }

    /**
     * Teste la méthode {@link UniteEnseignement#equals(Object)}.
     */
    @Test
    void testequals(){
        UniteEnseignement ue2=new UniteEnseignement("prog", "lk");
        UniteEnseignement ue3=new UniteEnseignement("ocaml", "lk");
        assertTrue(ue.equals(ue2));
        assertFalse(ue.equals(ue3));
    }
}
