package tests;

import static org.junit.Assert.assertFalse;
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
        ges = new GestionFormation();
        ges.creerFormation("L3IFA", "jsp", "jsp@gmail.com");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testConstructeur() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == "L3IFA");
        assertTrue(ges2.getNomResponsableFormation() == "jsp");
        assertTrue(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }

    @Test
    void TestisValidEmail() {
        assertFalse(ges.isValidEmail("hgf@"));
        assertTrue(ges.isValidEmail("hgf@gfegf.vge"));
    }

    @Test
    void testajoutUEObligatoire() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementObligatoire(ue));
    }

    @Test
    void testajoutUEOptionel() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementOptionnel(ue, 12));
    }

    @Test
    void testdefinirNombreOptions() {
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }

    @Test
    void testsetTailleGroupeDirige() {
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }

    @Test
    void testsetTailleGroupePratique() {
        ges.setTailleGroupePratique(5);
        assertEquals(ges.getTailleGroupePratique(), 5);
    }

    @Test
    void testAtribuerAtaumatiquement() {
        for (int i = 0; i < 100; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");;
            ges.getGestionEtudiant().inscription(inf, i+"");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        // 100 etudiant / 10 le nombre max d'étudiant par goupe et +1 si résultat non entier donc 100/10=10
        assertTrue(ges.interval(10, ges.getTds()));
        assertTrue(ges.interval(10, ges.getTps()));
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        ges.attribuerAutomatiquementGroupes();
        // 101 etudiant / 10 le nombre max d'étudiant par goupe et +1 si résultat non entier donc 101/10+1=9.181818 donc 9
        assertTrue(ges.interval(9, ges.getTds()));
        assertTrue(ges.interval(9, ges.getTps()));
    }
    @Test
    void testChangerGroupe(){
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");;
            ges.getGestionEtudiant().inscription(inf, i+"");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        Etudiant etu=ges.listeEtudiantsGroupeDirige(1).iterator().next();
        assertTrue(ges.listeEtudiantsGroupeDirige(1).contains(etu));
        ges.changerGroupe(etu, 11, 0);
        assertTrue(ges.listeEtudiantsGroupeDirige(11).contains(etu));
        assertTrue(ges.listeEtudiantsGroupePratique(1).contains(etu));
        ges.changerGroupe(etu, 0, 11);
        assertTrue(ges.listeEtudiantsGroupePratique(11).contains(etu));
    }
    @Test
    void testEnvoyerMessage(){
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");;
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu=ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu, "bonjour");
        assertTrue(etu.getMessages().iterator().next().getContenu()=="bonjour");
    }
}
