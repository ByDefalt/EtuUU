package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

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
    void setUp(){
        ges = new GestionFormation();
        ges.creerFormation("L3IFA", "jsp", "jsp@gmail.com");
    }

    @AfterEach
    void tearDown(){
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
    void testConstructeurNomNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation(null, "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurNomVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurNomResponsableVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurNomResponsableNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", null, "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurEmailVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", "");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "fds");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurEmailNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", null);
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "fds");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    @Test
    void testConstructeurEmailNotValide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "ergr", "jhger");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "ergr");
        assertFalse(ges2.getEmailResponsableFormation() == "jhger");
    }
    @Test
    void TestisValidEmail() {
        assertFalse(ges.isValidEmail("hgf@"));
        assertTrue(ges.isValidEmail("hgf@gfegf.vge"));
    }
    @Test
    void TestisValidEmailNull() {
        assertFalse(ges.isValidEmail(null));
    }
    @Test
    void TestisValidEmailVide() {
        assertFalse(ges.isValidEmail(""));
    }
    @Test
    void testgetGestionEtudiant(){
        Etudiant etu=new Etudiant(new InformationPersonnelle("ri", "ro"), "mdp");
        ges.getGestionEtudiant().getListeEtudiants().add(etu);
        assertEquals(ges.getGestionEtudiant().getListeEtudiants().iterator().next(), etu);
    }
    @Test
    void testgetNBoption() {
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }
    @Test
    void testsetNbOptionEtudiant() {
        Etudiant etu=new Etudiant(new InformationPersonnelle("ri", "ro"), "mdp");
        ges.setNbOptionEtudiant(etu);
        assertEquals(etu.getMotDePasse(), "mdp");
    }
    @Test
    void testgetTds() {
        ges.getTds().put(1, new HashSet<>());
        assertTrue(ges.getTds().containsKey(1));
    }
    @Test
    void testgetTps() {
        ges.getTps().put(1, new HashSet<>());
        assertTrue(ges.getTps().containsKey(1));
    }
    @Test
    void testgetgetEmailResponsableFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getEmailResponsableFormation(),"jhger@mo.mop");
    }
    @Test
    void testgetNomResponsableFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getNomResponsableFormation(),"ergr");
    }
    @Test
    void testgetNomFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getNomFormation(),"L3IFA");
    }
    @Test
    void testajoutUEObligatoire() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementObligatoire(ue));
    }
    @Test
    void testajoutUEObligatoireNull() {
        assertFalse(ges.ajouterEnseignementObligatoire(null));
    }
    @Test
    void testajoutUEOptionel() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementOptionnel(ue, 12));
    }
    @Test
    void testajoutUEOptionelNull() {
        assertFalse(ges.ajouterEnseignementOptionnel(null, 12));
    }
    @Test
    void testajoutUEOptionelNBplace() {
        assertFalse(ges.ajouterEnseignementOptionnel(new UniteEnseignement("kjh", "lkj"), -10));
    }

    @Test
    void testdefinirNombreOptions() {
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }
    @Test
    void testdefinirNombreOptionsNegatif() {
        ges.definirNombreOptions(-10);
        assertNotEquals(ges.getNBoption(), -10);
    }

    @Test
    void testsetTailleGroupeDirige() {
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }
    @Test
    void testsetTailleGroupeDirigeNegatif() {
        ges.setTailleGroupeDirige(-10);
        assertNotEquals(ges.getTailleGroupeDirige(), -10);
    }

    @Test
    void testsetTailleGroupePratique() {
        ges.setTailleGroupePratique(5);
        assertEquals(ges.getTailleGroupePratique(), 5);
    }
    @Test
    void testsetTailleGroupePratiqueNegatif() {
        ges.setTailleGroupePratique(-10);
        assertNotEquals(ges.getTailleGroupePratique(), -10);
    }
    @Test
    void testgetTailleGroupeDirige() {
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }
    @Test
    void testgetTailleGroupePratique() {
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
        // entier donc 100/10=10 , 10 étudiant par groupe
        assertTrue(ges.interval(10, ges.getTds()));
        assertTrue(ges.interval(10, ges.getTps()));
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        ges.attribuerAutomatiquementGroupes();
        // 101 etudiant / 10 le nombre max d'étudiant par goupe et +1 si résultat non
        // entier donc 101/10+1=9.181818 étudiant par groupe donc 9 ou 10 par groupe
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
    void testChangerGroupePlein() {
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
        ges.changerGroupe(etu, 2, 0);
        assertFalse(ges.listeEtudiantsGroupeDirige(11).contains(etu));
        assertTrue(ges.listeEtudiantsGroupePratique(1).contains(etu));
        ges.changerGroupe(etu, 0, 2);
        assertFalse(ges.listeEtudiantsGroupePratique(11).contains(etu));
    }
    @Test
    void testChangerGroupeNonExistant() {
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
        ges.changerGroupe(etu, 50, 0);
        assertFalse(ges.listeEtudiantsGroupeDirige(11).contains(etu));
        assertTrue(ges.listeEtudiantsGroupePratique(1).contains(etu));
        ges.changerGroupe(etu, 0, 50);
        assertFalse(ges.listeEtudiantsGroupePratique(11).contains(etu));
    }

    @Test
    void testEnvoyerMessage() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"titre", "bonjour");
        assertTrue(etu.getMessages().iterator().next().getContenu() == "bonjour");
    }
    @Test
    void testEnvoyerMessageTitreVide() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"", "mlk");
        assertTrue(etu.getMessages().size() == 0);
    }
    @Test
    void testEnvoyerMessageContenuVide() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"mlk", "");
        assertTrue(etu.getMessages().size() == 0);
    }
    @Test
    void testEnvoyerMessageContenuNull() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"hrt", null);
        assertTrue(etu.getMessages().size() == 0);
    }
    @Test
    void testEnvoyerMessageTitreNull() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,null, "hgf");
        assertTrue(etu.getMessages().size() == 0);
    }

    @Test
    void testnombreGroupesTravauxDiriges() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
        assertEquals(ges.nombreGroupesTravauxDiriges(), 11);
    }
    @Test
    void testnombreGroupesTravauxPratiques() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
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
    }
    @Test
    void testlisteEtudiantsGroupePratique() {
        for (int i = 0; i < 101; i++) {
            InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
            ;
            ges.getGestionEtudiant().inscription(inf, i + "");
        }
        ges.setTailleGroupeDirige(10);
        ges.setTailleGroupePratique(10);
        ges.attribuerAutomatiquementGroupes();
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
        ges.ajouterEnseignementOptionnel(ue, 12);
        UniteEnseignement ue2 = new UniteEnseignement("m", "m");
        ges.ajouterEnseignementOptionnel(ue2, 10);
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
    @Test
    void testlisteEtudiantsOptionNull() {
        assertTrue(ges.listeEtudiantsOption(null) == null);
    }
    @Test
    void testlisteEtudiantsOptionNotInList() {
        assertTrue(ges.listeEtudiantsOption(new UniteEnseignement("gfde", "tre")) == null);
    }
}
