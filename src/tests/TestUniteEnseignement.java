package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
