package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.*;
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
    private GestionFormation ges;

    @BeforeEach
    void setUp() throws Exception {
        ges=new GestionFormation();
        ges.creerFormation("L3IFA","jsp","jsp@gmail.com");
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    @Test
    void ajoutUEObligatoire(){
        UniteEnseignement ue=new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementObligatoire(ue));
    }
    @Test
    void ajoutUEOptionel(){
        UniteEnseignement ue=new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementOptionnel(ue,12));
    }
    @Test
    void definirNombreOptions(){
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }
    @Test
    void setTailleGroupeDirige(){
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }
    @Test
    void setTailleGroupePratique(){
        ges.setTailleGroupePratique(5);
        assertEquals(ges.getTailleGroupePratique(), 5);
    }
    
}
