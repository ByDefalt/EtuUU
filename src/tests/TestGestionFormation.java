package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.GestionFormation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.GestionFormation
 * GestionFormation}.
 *
 * @author Rousval Romain
 * @see formation.GestionFormation
 */
class TestGestionFormation {
    private GestionFormation gesform1;
    private GestionFormation gesform2;

    @BeforeEach
    void setUp() throws Exception {
        gesform1=new GestionFormation("ki","lo","ol");
        gesform2=new GestionFormation();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testajoutformation() {
        gesform2.creerFormation("ki", "lo", "ju");
        assertTrue(gesform2.SetContien("ki", "lo", "ju"));
    }
    @Test
    void setoption() {
        gesform1.definirNombreOptions(9);
        String str="GestionFormation [nomFormation=ki, nomResponsable=lo, email=ol, TailleGroupeDirige=16, TailleGroupePratique=15, option=9]";
        assertEquals(gesform1.toString(), str);
    }
    @Test
    void setTailleGroupeDirige() {
        gesform1.setTailleGroupeDirige(16);
        assertEquals(gesform1.getTailleGroupeDirige(), 16);
    }
    @Test
    void setTailleGroupePratique() {
        gesform1.setTailleGroupePratique(15);
        assertEquals(gesform1.getTailleGroupePratique(), 15);
    }
}
