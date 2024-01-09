package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.UniteEnseignement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.UniteEnseignement
 * InformationPersonnelle}.
 *
 * @author Eric Cariou
 * @see formation.UniteEnseignement
 */
class TestInformationUniteEnseignement {

    private UniteEnseignement ue;

    @BeforeEach
    void setUp() throws Exception {
        ue=new UniteEnseignement("prog","richard");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testConstructeur() {
        UniteEnseignement ue2=new UniteEnseignement("prog","richard");
        assertEquals(ue2.getNomUE(),"prog");
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement("Informatique", null));
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement(null, "Informatique"));
        assertThrows(IllegalArgumentException.class, () -> new UniteEnseignement(null, null));
    }
    @Test
    void testgetOptionnel(){
        assertFalse(ue.getOptionnel());
        ue.setOptionnel(true);
        assertTrue(ue.getOptionnel());
    }
    @Test
    void testsetOptionnel(){
        assertFalse(ue.getOptionnel());
        ue.setOptionnel(true);
        assertTrue(ue.getOptionnel());
    }
    @Test
    void testgetNomUE(){
        assertEquals(ue.getNomUE(),"prog");
    }
    @Test
    void testgetNbPlacesMax(){
        ue.setNbPlacesMax(12);
        assertNotEquals(ue.getNbPlacesMax(),12);
        ue.setOptionnel(true);
        ue.setNbPlacesMax(12);
        assertEquals(ue.getNbPlacesMax(),12);
    }
    @Test
    void testgetNomEnseignant(){
        assertEquals(ue.getNomEnseignant(), "richard");
    }
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
    @Test
    void testgetnbParticipant(){
        assertEquals(ue.getnbParticipant(), 0);
    }
    @Test
    void testsetnbParticipant(){
        assertEquals(ue.getnbParticipant(), 0);
        ue.setnbParticipant();
        assertNotEquals(ue.getnbParticipant(), 1);
        ue.setOptionnel(true);
        ue.setnbParticipant();
        assertEquals(ue.getnbParticipant(), 1);
    }
    @Test
    void testhashCode(){
        UniteEnseignement ue2=new UniteEnseignement("prog", "lk");
        UniteEnseignement ue3=new UniteEnseignement("ocaml", "lk");
        assertEquals(ue.hashCode(), ue2.hashCode());
        assertNotEquals(ue.hashCode(), ue3.hashCode());
    }
    @Test
    void testequals(){
        UniteEnseignement ue2=new UniteEnseignement("prog", "lk");
        UniteEnseignement ue3=new UniteEnseignement("ocaml", "lk");
        assertTrue(ue.equals(ue2));
        assertFalse(ue.equals(ue3));
    }
}