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
    void testConstructeur1() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == "L3IFA");
        assertTrue(ges2.getNomResponsableFormation() == "jsp");
        assertTrue(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }

    @Test
    void testConstructeur2() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }

    @Test
    void testConstructeur3() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation(null, "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }

    @Test
    void testConstructeur4() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeur5() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", null, "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeur6() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", "");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeur7() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", null);
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
        ges2.creerFormation("L3IFA", "ergr", "jhger");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
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
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        // 100 etudiant / 10 le nombre max d'étudiant par goupe et +1 si résultat non
        // entier donc 100/10=10
        assertTrue(ges.interval(10, ges.getTds()));
        assertTrue(ges.interval(10, ges.getTps()));
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        ges.attribuerAutomatiquementGroupes();
        // 101 etudiant / 10 le nombre max d'étudiant par goupe et +1 si résultat non
        // entier donc 101/10+1=9.181818 donc 9
        assertTrue(ges.interval(9, ges.getTds()));
        assertTrue(ges.interval(9, ges.getTps()));
    }

    @Test
    void testChangerGroupe() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        Etudiant etu = ges.listeEtudiantsGroupeDirige(1).iterator().next();
        assertTrue(ges.listeEtudiantsGroupeDirige(1).contains(etu));
        ges.changerGroupe(etu, 11, 0);
        assertTrue(ges.listeEtudiantsGroupeDirige(11).contains(etu));
        assertTrue(ges.listeEtudiantsGroupePratique(1).contains(etu));
        ges.changerGroupe(etu, 0, 11);
        assertTrue(ges.listeEtudiantsGroupePratique(11).contains(etu));
    }

    @Test
    void testEnvoyerMessage() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ;
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu, "bonjour");
        assertTrue(etu.getMessages().iterator().next().getContenu() == "bonjour");
    }

    @Test
    void testnombreGroupesTravaux() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        assertEquals(ges.nombreGroupesTravauxDiriges(), 11);
        assertEquals(ges.nombreGroupesTravauxPratiques(), 11);
    }

    @Test
    void testlisteEtudiantsGroupeDirige() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        assertEquals(ges.getTds().get(5), ges.listeEtudiantsGroupeDirige(5));
        assertEquals(ges.getTps().get(5), ges.listeEtudiantsGroupePratique(5));
    }

    @Test
    void testlisteEtudiantsOption() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        ges.definirNombreOptions(5);
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementOptionnel(ue, 12));
        assertTrue(ges.listeEtudiantsOption(ue).size() == 0);
        UniteEnseignement ue2 = new UniteEnseignement("m", "m");
        assertTrue(ges.ajouterEnseignementOptionnel(ue2, 10));
        assertTrue(ges.listeEtudiantsOption(ue2).size() == 0);
        for (int i = 0; i < 50; i++) {
            ges.getGestionEtudiant().connexion(i, i + "");
            try {
                ges.getGestionEtudiant().choisirOption(ue);
            } catch (NonConnecteException e) {
                e.printStackTrace();
            }
            try {
                ges.getGestionEtudiant().deconnexion();
            } catch (NonConnecteException e) {
                e.printStackTrace();
            }
        }
        ges.getGestionEtudiant().connexion(0, 0 + "");
        try {
            ges.getGestionEtudiant().choisirOption(ue2);
        } catch (NonConnecteException e) {
            e.printStackTrace();
        }
        try {
            ges.getGestionEtudiant().deconnexion();
        } catch (NonConnecteException e) {
            e.printStackTrace();
        }
        assertTrue(ges.listeEtudiantsOption(ue2).size() == 1);
        assertTrue(ges.listeEtudiantsOption(ue).size() == 12);
    }
}
