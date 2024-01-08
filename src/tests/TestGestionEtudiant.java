package tests;

import formation.GestionEtudiant;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UniteEnseignement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestGestionEtudiant {

    private GestionEtudiant gestionEtudiant;
    private InformationPersonnelle infoPerso1;
    private InformationPersonnelle infoPerso2;
    private final Set<UniteEnseignement> listeUE = new HashSet<>();
    private final Set<UniteEnseignement> listeUEO = new HashSet<>();
    private final Set<UniteEnseignement> listeUEF = new HashSet<>();
    private UniteEnseignement ue1;
    private UniteEnseignement ue2;
    private UniteEnseignement ue3;

    @BeforeEach
    void setUp() throws Exception {
        this.gestionEtudiant = new GestionEtudiant();
        this.infoPerso1 = new InformationPersonnelle("Skywalker", "Luke");
        this.infoPerso2 = new InformationPersonnelle("Skywalker", "Luke", "Planète Tatooine", 20);

        this.ue1 = new UniteEnseignement("UE1", "Enseignant1");
        this.ue1.setOptionnel(true);
        this.ue1.setNbPlacesMax(30);
        this.ue2 = new UniteEnseignement("UE2", "Enseignant2");
        this.ue2.setOptionnel(true);
        this.ue2.setNbPlacesMax(25);
        this.ue3 = new UniteEnseignement("UE3", "Enseignant3"); 
        this.ue3.setOptionnel(false);

        this.listeUE.add(this.ue1);
        this.listeUE.add(this.ue2);
        this.listeUE.add(this.ue3);

        this.listeUEO.add(this.ue3);
        this.listeUEF.add(this.ue1);
        this.listeUEF.add(this.ue2);

        this.gestionEtudiant.setListeUE(listeUE);
        this.gestionEtudiant.inscription(infoPerso1, "Sk1525mlds");
        this.gestionEtudiant.inscription(infoPerso2, "azerty");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testInscriptionBasique() {
        int inscription = this.gestionEtudiant.inscription(infoPerso1, "Sk1525mlds");
        assertEquals(2, inscription);
    }

    @Test
    void testInscriptionInfoPersoVide() {
        int inscription = this.gestionEtudiant.inscription(null, "Sk1525mlds");
        assertEquals(-1, inscription);
    }

    @Test
    void testInscriptionMotDePasseVide() {
        int inscription = this.gestionEtudiant.inscription(infoPerso1, "");
        assertEquals(-1, inscription);
    }

    @Test
    void testConnexionBasique() {
        boolean connexion = this.gestionEtudiant.connexion(0, "Sk1525mlds");
        assertTrue(connexion);
    }

    @Test
    void testConnexionMotDePasseIncorrect() {
        boolean connexion = this.gestionEtudiant.connexion(0, "Lucepassword");
        assertFalse(connexion);
    }

    @Test
    void testConnexionNumeroIncorrect() {
        boolean connexion = this.gestionEtudiant.connexion(15, "Sk1525mlds");
        assertFalse(connexion);
    }

    @Test
    void testDeconnexionBasique() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            this.gestionEtudiant.deconnexion();
            assertTrue(true);
        } catch (NonConnecteException e) {
            fail("La déconnexion ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testDeconnexionSansUtilisateurConnecte() {
        try {
            this.gestionEtudiant.deconnexion();
            assertTrue(false);
        } catch (NonConnecteException e) {
            assertTrue(true);
        }
    }

    @Test
    void testEnseignementObligatoires() {
        Set<UniteEnseignement> lsUEO = this.gestionEtudiant.enseignementsObligatoires();
        assertEquals(this.listeUEO, lsUEO);
    }

    @Test
    void testEnseignementOptionnels() {
        Set<UniteEnseignement> lsUEF = this.gestionEtudiant.enseignementsOptionnels();
        assertEquals(this.listeUEF, lsUEF);
    }

    @Test
    void testNombreOptionsBasique() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            this.gestionEtudiant.setNbOption(2);
            int nbOptions = this.gestionEtudiant.nombreOptions();
            assertEquals(2, nbOptions);
        } catch (NonConnecteException e) {
            fail("La méthode nombreOptions ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testNombreOptionsPasDefinis() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            int nbOptions = this.gestionEtudiant.nombreOptions();
            assertEquals(-1, nbOptions);
        } catch (NonConnecteException e) {
            fail("La méthode nombreOptions ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testChoisirOptionBasique() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            boolean res = this.gestionEtudiant.choisirOption(this.ue1);
            assertTrue(res);
        } catch (NonConnecteException e) {
            fail("La méthode choisirOption ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testChoisirOptionUeObligatoire() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            boolean res = this.gestionEtudiant.choisirOption(this.ue3);
            assertFalse(res);
        } catch (NonConnecteException e) {
            fail("La méthode choisirOption ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testNumeroGroupeTd() {
        try {
            this.gestionEtudiant.connexion(0, "Sk1525mlds");
            this.gestionEtudiant.setNumeroTd(2);
            int res = this.gestionEtudiant.getNumeroGroupeTravauxDiriges();
            assertEquals(2, res);
        } catch (NonConnecteException e) {
            fail("La méthode setNumeroTd et getNumeroGroupeTravauxDiriges ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testNumeroGroupeTp() {
        try {
            this.gestionEtudiant.connexion(1, "azerty");
            this.gestionEtudiant.setNumeroTp(3);
            int res = this.gestionEtudiant.getNumeroGroupeTravauxPratiques();
            assertEquals(3, res);
        } catch (NonConnecteException e) {
            fail("La méthode setNumeroTp et getNumeroGroupeTravauxPratiques ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testEnseignementsSuivisBasique() {
        try {
            this.gestionEtudiant.connexion(1, "azerty");
            Set<UniteEnseignement> ueRes = new HashSet<>();
            ueRes.add(this.ue1);
            ueRes.add(this.ue2);

            this.gestionEtudiant.choisirOption(this.ue1);
            this.gestionEtudiant.choisirOption(this.ue2);
            assertEquals(ueRes, this.gestionEtudiant.enseignementsSuivis());
        } catch (NonConnecteException e) {
            fail("La méthode enseignementsSuivis ne devrait pas lever d'exception ici.");
        }
    }

    @Test
    void testEnseignementsSuivisVide() {
        try {
            this.gestionEtudiant.connexion(1, "azerty");
            Set<UniteEnseignement> ue = this.gestionEtudiant.enseignementsSuivis();
            assertEquals(new HashSet<UniteEnseignement>(), ue);
        } catch (NonConnecteException e) {
            fail("La méthode enseignementsSuivis ne devrait pas lever d'exception ici.");
        }
    }
}

