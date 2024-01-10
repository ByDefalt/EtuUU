package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Map;

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
    
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec des valeurs valides.
     * Vérifie que les attributs de la formation sont correctement initialisés.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeur() {
        GestionFormation ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == "L3IFA");
        assertTrue(ges2.getNomResponsableFormation() == "jsp");
        assertTrue(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un nom nul.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurNomNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation(null, "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un nom vide.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurNomVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("", "jsp", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un nom de responsable vide.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurNomResponsableVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "", "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un nom de responsable nul.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurNomResponsableNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", null, "jsp@gmail.com");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "jsp");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un email vide.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurEmailVide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", "");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "fds");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un email nul.
     * Vérifie que le nom de la formation est correctement initialisé à null, et que les autres attributs ne le sont pas.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurEmailNull(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "fds", null);
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "fds");
        assertFalse(ges2.getEmailResponsableFormation() == "jsp@gmail.com");
    }
    /**
     * Teste le constructeur de la classe {@link GestionFormation} en créant une formation avec un email non valide.
     * Vérifie que tous les attributs de la formation sont correctement initialisés, sauf l'email qui doit être incorrect.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     */
    @Test
    void testConstructeurEmailNotValide(){
        GestionFormation ges2 = new GestionFormation();
        ges2 = new GestionFormation();
        ges2.creerFormation("L3IFA", "ergr", "jhger");
        assertTrue(ges2.getNomFormation() == null);
        assertFalse(ges2.getNomResponsableFormation() == "ergr");
        assertFalse(ges2.getEmailResponsableFormation() == "jhger");
    }
    /**
     * Teste la méthode {@link GestionFormation#isValidEmail(String)} en fournissant une adresse e-mail non valide.
     * Vérifie que la méthode renvoie correctement false.
     * 
     * @see GestionFormation#isValidEmail(String)
     */
    @Test
    void TestisValidEmail() {
        assertFalse(ges.isValidEmail("hgf@"));
        assertTrue(ges.isValidEmail("hgf@gfegf.vge"));
    }
    /**
     * Teste la méthode {@link GestionFormation#isValidEmail(String)} en fournissant une adresse e-mail nulle.
     * Vérifie que la méthode renvoie correctement false.
     * 
     * @see GestionFormation#isValidEmail(String)
     */
    @Test
    void TestisValidEmailNull() {
        assertFalse(ges.isValidEmail(null));
    }
    /**
     * Teste la méthode {@link GestionFormation#isValidEmail(String)} en fournissant une adresse e-mail vide.
     * Vérifie que la méthode renvoie correctement false.
     * 
     * @see GestionFormation#isValidEmail(String)
     */
    @Test
    void TestisValidEmailVide() {
        assertFalse(ges.isValidEmail(""));
    }
    /**
     * Teste la méthode {@link GestionFormation#getNBoption()} après avoir défini le nombre d'options.
     * Vérifie que le nombre d'options est correctement récupéré.
     * 
     * @see GestionFormation#definirNombreOptions(int)
     * @see GestionFormation#getNBoption()
     */
    @Test
    void testgetNBoption() {
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#setNbOptionEtudiant(Etudiant)} en s'assurant que le mot de passe de l'étudiant
     * est correctement défini.
     * 
     * @see GestionFormation#setNbOptionEtudiant(Etudiant)
     */
    @Test
    void testsetNbOptionEtudiant() {
        Etudiant etu=new Etudiant(new InformationPersonnelle("ri", "ro"), "mdp");
        ges.definirNombreOptions(5);
        ges.setNbOptionEtudiant(etu);
        assertEquals(etu.getNbOption(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#getTds()} en vérifiant si la map des groupes de travaux dirigés (TD)
     * est correctement initialisée et contient une clé spécifique.
     * 
     * @see GestionFormation#getTds()
     */
    @Test
    void testgetTds() {
        ges.getTds().put(1, new HashSet<>());
        assertTrue(ges.getTds().containsKey(1));
    }
    /**
     * Teste la méthode {@link GestionFormation#getTps()} en vérifiant si la map des groupes de travaux pratiques (TP)
     * est correctement initialisée et contient une clé spécifique.
     * 
     * @see GestionFormation#getTps()
     */
    @Test
    void testgetTps() {
        ges.getTps().put(1, new HashSet<>());
        assertTrue(ges.getTps().containsKey(1));
    }
    /**
     * Teste la méthode {@link GestionFormation#getEmailResponsableFormation()} en s'assurant que l'email du responsable de formation
     * est correctement récupéré après la création d'une formation.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     * @see GestionFormation#getEmailResponsableFormation()
     */
    @Test
    void testgetgetEmailResponsableFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getEmailResponsableFormation(),"jhger@mo.mop");
    }
    /**
     * Teste la méthode {@link GestionFormation#getNomResponsableFormation()} en s'assurant que le nom du responsable de formation
     * est correctement récupéré après la création d'une formation.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     * @see GestionFormation#getNomResponsableFormation()
     */
    @Test
    void testgetNomResponsableFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getNomResponsableFormation(),"ergr");
    }
    /**
     * Teste la méthode {@link GestionFormation#getNomFormation()} en s'assurant que le nom de la formation
     * est correctement récupéré après la création d'une formation.
     * 
     * @see GestionFormation#creerFormation(String, String, String)
     * @see GestionFormation#getNomFormation()
     */
    @Test
    void testgetNomFormation(){
        ges.creerFormation("L3IFA", "ergr", "jhger@mo.mop");
        assertEquals(ges.getNomFormation(),"L3IFA");
    }
    /**
     * Teste la méthode {@link GestionFormation#ajouterEnseignementObligatoire(UniteEnseignement)} en ajoutant une UE obligatoire.
     * Vérifie que l'opération réussit et que l'UE est correctement ajoutée à la liste des UE obligatoires.
     *
     * @see GestionFormation#ajouterEnseignementObligatoire(UniteEnseignement)
     */
    @Test
    void testajoutUEObligatoire() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementObligatoire(ue));
    }
    /**
     * Teste la méthode {@link GestionFormation#ajouterEnseignementObligatoire(UniteEnseignement)} en fournissant une UE nulle.
     * Vérifie que l'opération échoue et qu'aucune UE n'est ajoutée à la liste des UE obligatoires.
     *
     * @see GestionFormation#ajouterEnseignementObligatoire(UniteEnseignement)
     */
    @Test
    void testajoutUEObligatoireNull() {
        assertFalse(ges.ajouterEnseignementObligatoire(null));
    }
    /**
     * Teste la méthode {@link GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)} en ajoutant une UE optionnelle.
     * Vérifie que l'opération réussit et que l'UE est correctement ajoutée à la liste des UE optionnelles avec le nombre de places spécifié.
     *
     * @see GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)
     */
    @Test
    void testajoutUEOptionel() {
        UniteEnseignement ue = new UniteEnseignement("l", "l");
        assertTrue(ges.ajouterEnseignementOptionnel(ue, 12));
    }
    /**
     * Teste la méthode {@link GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)} en fournissant une UE nulle.
     * Vérifie que l'opération échoue et qu'aucune UE n'est ajoutée à la liste des UE optionnelles.
     *
     * @see GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)
     */
    @Test
    void testajoutUEOptionelNull() {
        assertFalse(ges.ajouterEnseignementOptionnel(null, 12));
    }
    /**
     * Teste la méthode {@link GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)} en fournissant un nombre de places négatif.
     * Vérifie que l'opération échoue et qu'aucune UE n'est ajoutée à la liste des UE optionnelles.
     *
     * @see GestionFormation#ajouterEnseignementOptionnel(UniteEnseignement, int)
     */
    @Test
    void testajoutUEOptionelNBplace() {
        assertFalse(ges.ajouterEnseignementOptionnel(new UniteEnseignement("kjh", "lkj"), -10));
    }
    /**
     * Teste la méthode {@link GestionFormation#definirNombreOptions(int)} en définissant un nombre d'options positif.
     * Vérifie que l'opération réussit et que le nombre d'options est correctement défini.
     *
     * @see GestionFormation#definirNombreOptions(int)
     */
    @Test
    void testdefinirNombreOptions() {
        ges.definirNombreOptions(5);
        assertEquals(ges.getNBoption(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#definirNombreOptions(int)} en définissant un nombre d'options négatif.
     * Vérifie que l'opération échoue et que le nombre d'options n'est pas défini à une valeur négative.
     *
     * @see GestionFormation#definirNombreOptions(int)
     */
    @Test
    void testdefinirNombreOptionsNegatif() {
        ges.definirNombreOptions(-10);
        assertNotEquals(ges.getNBoption(), -10);
    }
    /**
     * Teste la méthode {@link GestionFormation#setTailleGroupeDirige(int)} en définissant une taille de groupe dirigé positive.
     * Vérifie que l'opération réussit et que la taille de groupe dirigé est correctement définie.
     *
     * @see GestionFormation#setTailleGroupeDirige(int)
     */
    @Test
    void testsetTailleGroupeDirige() {
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#setTailleGroupeDirige(int)} en définissant une taille de groupe dirigé négative.
     * Vérifie que l'opération échoue et que la taille de groupe dirigé n'est pas définie à une valeur négative.
     *
     * @see GestionFormation#setTailleGroupeDirige(int)
     */
    @Test
    void testsetTailleGroupeDirigeNegatif() {
        ges.setTailleGroupeDirige(-10);
        assertNotEquals(ges.getTailleGroupeDirige(), -10);
    }
     /**
     * Teste la méthode {@link GestionFormation#setTailleGroupePratique(int)} en définissant une taille de groupe pratique positive.
     * Vérifie que l'opération réussit et que la taille de groupe pratique est correctement définie.
     *
     * @see GestionFormation#setTailleGroupePratique(int)
     */
    @Test
    void testsetTailleGroupePratique() {
        ges.setTailleGroupePratique(5);
        assertEquals(ges.getTailleGroupePratique(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#setTailleGroupePratique(int)} en définissant une taille de groupe pratique négative.
     * Vérifie que l'opération échoue et que la taille de groupe pratique n'est pas définie à une valeur négative.
     *
     * @see GestionFormation#setTailleGroupePratique(int)
     */
    @Test
    void testsetTailleGroupePratiqueNegatif() {
        ges.setTailleGroupePratique(-10);
        assertNotEquals(ges.getTailleGroupePratique(), -10);
    }
    /**
     * Teste la méthode {@link GestionFormation#getTailleGroupeDirige()}
     * en s'assurant que la taille de groupe dirigé est correctement récupérées depuis la gestion de formation.
     *
     * @see GestionFormation#getTailleGroupeDirige()
     */
    @Test
    void testgetTailleGroupeDirige() {
        ges.setTailleGroupeDirige(5);
        assertEquals(ges.getTailleGroupeDirige(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#getTailleGroupePratique()}
     * en s'assurant que la taille de groupe pratique est correctement récupérées depuis la gestion de formation.
     *
     * @see GestionFormation#getTailleGroupePratique()
     */
    @Test
    void testgetTailleGroupePratique() {
        ges.setTailleGroupePratique(5);
        assertEquals(ges.getTailleGroupePratique(), 5);
    }
    /**
     * Teste la méthode {@link GestionFormation#attribuerAutomatiquementGroupes()}.
     * Vérifie si les groupes sont attribués correctement en fonction des tailles spécifiées pour les groupes.
     * Pour cela, des étudiants sont inscrits automatiquement dans la gestion des étudiants,
     * puis les groupes sont attribués automatiquement en respectant les tailles spécifiées.
     * Enfin, les groupes sont vérifiés pour s'assurer qu'ils respectent bien les tailles spécifiées.
     * 
     * @see GestionFormation#attribuerAutomatiquementGroupes()
     * @see GestionFormation#interval(int, Map)
     */
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
    /**
     * Teste la méthode {@link GestionFormation#changerGroupe(Etudiant, int, int)}.
     * Vérifie si un étudiant peut changer de groupe avec des arguments valides.
     */
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
    /**
     * Teste la méthode {@link GestionFormation#changerGroupe(Etudiant, int, int)}.
     * Vérifie si un étudiant ne peut pas changer de groupe vers un groupe plein.
     */
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
    /**
     * Teste la méthode {@link GestionFormation#changerGroupe(Etudiant, int, int)}.
     * Vérifie si un étudiant ne peut pas changer de groupe vers un groupe inexistant.
     */
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
    /**
     * Teste la méthode {@link GestionFormation#envoyermessage(Etudiant, String, String)}.
     * Vérifie si un message est correctement envoyé à un étudiant.
     */
    @Test
    void testEnvoyerMessage() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"titre", "bonjour");
        assertTrue(etu.getMessages().iterator().next().getContenu() == "bonjour");
    }
    /**
     * Teste la méthode {@link GestionFormation#envoyermessage(Etudiant, String, String)}.
     * Vérifie si un message n'est pas envoyé lorsque le titre est vide.
     */
    @Test
    void testEnvoyerMessageTitreVide() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"", "mlk");
        assertTrue(etu.getMessages().size() == 0);
    }
    /**
     * Teste la méthode {@link GestionFormation#envoyermessage(Etudiant, String, String)}.
     * Vérifie si un message n'est pas envoyé lorsque le contenu est vide.
     */
    @Test
    void testEnvoyerMessageContenuVide() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"mlk", "");
        assertTrue(etu.getMessages().size() == 0);
    }
    /**
     * Teste la méthode {@link GestionFormation#envoyermessage(Etudiant, String, String)}.
     * Vérifie si un message n'est pas envoyé lorsque le contenu est null.
     */
    @Test
    void testEnvoyerMessageContenuNull() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,"hrt", null);
        assertTrue(etu.getMessages().size() == 0);
    }
    /**
     * Teste la méthode {@link GestionFormation#envoyermessage(Etudiant, String, String)}.
     * Vérifie si un message n'est pas envoyé lorsque le titre est null.
     */
    @Test
    void testEnvoyerMessageTitreNull() {
        InformationPersonnelle inf = new InformationPersonnelle("l", "l");
        ges.getGestionEtudiant().inscription(inf, "l");
        Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().iterator().next();
        ges.envoyermessage(etu,null, "hgf");
        assertTrue(etu.getMessages().size() == 0);
    }
    /**
     * Teste la méthode {@link GestionGroupes#nombreGroupesTravauxDiriges()}.
     * Vérifie si le nombre de groupes de travaux dirigés attribués est correct.
     */
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
    /**
     * Teste la méthode {@link GestionGroupes#nombreGroupesTravauxPratiques()}.
     * Vérifie si le nombre de groupes de travaux pratiques attribués est correct.
     */
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
    /**
     * Teste la méthode {@link GestionGroupes#listeEtudiantsGroupeDirige(int)}.
     * Vérifie si la liste d'étudiants pour un groupe dirigé donné est correcte.
     */
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
    /**
     * Teste la méthode {@link GestionGroupes#listeEtudiantsGroupePratique(int)}.
     * Vérifie si la liste d'étudiants pour un groupe pratique donné est correcte.
     */
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
    /**
     * Teste la méthode {@link GestionGroupes#listeEtudiantsOption(UniteEnseignement)}.
     * Vérifie si la liste d'étudiants pour une option donnée est correcte.
     */
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
    /**
     * Teste la méthode {@link GestionGroupes#listeEtudiantsOption(UniteEnseignement)}.
     * Vérifie le comportement lorsque l'UE optionnelle spécifiée est nulle.
     */
    @Test
    void testlisteEtudiantsOptionNull() {
        assertTrue(ges.listeEtudiantsOption(null) == null);
    }
    /**
     * Teste la méthode {@link GestionGroupes#listeEtudiantsOption(UniteEnseignement)}.
     * Vérifie le comportement lorsque l'UE optionnelle spécifiée n'est pas présente dans la liste des options.
     */
    @Test
    void testlisteEtudiantsOptionNotInList() {
        assertTrue(ges.listeEtudiantsOption(new UniteEnseignement("gfde", "tre")) == null);
    }
}
