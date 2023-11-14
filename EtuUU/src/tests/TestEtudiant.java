package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import formation.Etudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;

public class TestEtudiant {
	private Etudiant etudiant1;
	private Etudiant etudiant2 = new Etudiant();
	private 
	
	@BeforeEach
    void setUp() throws Exception {
		etudiant1 = new Etudiant(new InformationPersonnelle("Skywalker", "Luke"), "password");
		etudiant2 = new Etudiant();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testInscription() {
    	int res2 = etudiant2.inscription(new InformationPersonnelle("Skywalker", "Luke", "Planète Tatooine", 20), "mmsdqdFf55");
        assertTrue(etudiant1.);
    }

}
