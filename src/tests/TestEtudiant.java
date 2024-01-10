package tests;

import formation.Etudiant;
import formation.InformationPersonnelle;
import formation.Message;
import formation.UniteEnseignement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

/**
 * Tests JUnit de la classe {@link formation.Etudiant
 * Etudiant}.
 * 
 * Cette classe contient des méthodes de test pour vérifier le bon fonctionnement
 * des fonctionnalités de la classe {@link formation.Etudiant}.
 *
 * @author LE BRAS Erwan
 * @see formation.Etudiant
 */
class TestEtudiant {

	/**
	 * Un étudiant.
	 */
    private Etudiant etudiant;
    
    /**
	 * Un message.
	 */
    private Message message1;
    
    /**
	 * Un message.
	 */
    private Message message2;
    
    /**
	 * Une uniteEnseignement optionnelle.
	 */
    private UniteEnseignement ue1;
    
    /**
	 * Une uniteEnseignement optionnelle.
	 */
    private UniteEnseignement ue2;
    
    /**
	 * Une uniteEnseignement obligatoire.
	 */
    private UniteEnseignement ue3;
    
    /**
	 * Une uniteEnseignement obligatoire.
	 */
    private UniteEnseignement ue4;
    
    /**
     * Instancie un etudiant basique, différentes UE et des messages.
     *
     */
    @BeforeEach
    void setUp() {
        InformationPersonnelle infoPerso = new InformationPersonnelle("Doe", "John");
        this.etudiant = new Etudiant(infoPerso, "password");

        this.ue1 = new UniteEnseignement("UE1", "Enseignant1");
        this.ue1.setNbPlacesMax(30);
        this.ue1.setOptionnel(true);
        this.ue2 = new UniteEnseignement("UE2", "Enseignant2");
        this.ue2.setNbPlacesMax(25);
        this.ue2.setOptionnel(true);
        this.ue3 = new UniteEnseignement("UE3", "Enseignant3");
        this.ue3.setNbPlacesMax(26);
        this.ue3.setOptionnel(false);
        this.ue4 = new UniteEnseignement("UE4", "Enseignant4");
        this.ue4.setNbPlacesMax(27);
        this.ue4.setOptionnel(false);
        
        this.message1 = new Message("msg1", "message1");
        this.message2 = new Message("msg2", "message2");


        this.etudiant.addUE(ue1);
        this.etudiant.addUE(ue3);
        this.etudiant.getMessages().add(message1);
        this.etudiant.getMessages().add(message2);
    }

    /**
     * Méthode de nettoyage après chaque test.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Test de la méthode {@link Etudiant#setNumero(int)} et {@link Etudiant#getNumero()}.
     * Vérifie que l'on peut positionner le numero 123.
     */
    @Test
    void testNumero() {
        this.etudiant.setNumero(123);
        assertEquals(123, this.etudiant.getNumero());
    }

    /**
     * Test de la méthode {@link Etudiant#setNbOption(int)} et {@link Etudiant#getNbOption()}.
     * Vérifie que l'on peut positionner le nombre d'options à 2.
     */
    @Test
    void testNbOption() {
    	this.etudiant.setNbOption(2);
        assertEquals(2, this.etudiant.getNbOption());
    }

    /**
     * Test de la méthode {@link Etudiant#setNumeroTp(int)} et {@link Etudiant#getNumeroTp()}.
     * Vérifie que l'on peut positionner le numero de Tp à 1.
     */
    @Test
    void testNumeroTp() {
    	this.etudiant.setNumeroTp(1);
        assertEquals(1, this.etudiant.getNumeroTp());
    }

    /**
     * Test de la méthode {@link Etudiant#setNumeroTd(int)} et {@link Etudiant#getNumeroTd()}.
     * Vérifie que l'on peut positionner le numero de Td à 3.
     */
    @Test
    void testNumeroTd() {
    	this.etudiant.setNumeroTd(3);
        assertEquals(3, this.etudiant.getNumeroTd());
    }

    /**
     * Test de la méthode {@link Etudiant#addUE(UniteEnseignement)}.
     * Vérifie que l'on peut ajouter une ue optionnelle.
     */
    @Test
    void testAddUEOptionnelle() {
    	this.etudiant.addUE(this.ue2);
        assertTrue(this.etudiant.getListeUEsuivies().contains(this.ue2));
    }
    
    /**
     * Test de la méthode {@link Etudiant#addUE(UniteEnseignement)}.
     * Vérifie que l'on peut ajouter une ue obligatoire.
     */
    @Test
    void testAddUEObligatoire() {
    	this.etudiant.addUE(this.ue4);
        assertTrue(this.etudiant.getListeUEsuivies().contains(this.ue4));
    }
    
    /**
     * Test de la méthode {@link Etudiant#getListeUEsuivies()}.
     * Vérifie que l'on récupère bien toutes les ue suivies d'un etudiant.
     */
    @Test
    void testListeUEsuivies() {
        Set<UniteEnseignement> ue = this.etudiant.getListeUEsuivies();
        assertEquals(2, ue.size());
        assertTrue(ue.contains(this.ue1));
        assertTrue(ue.contains(this.ue3));
    }

    /**
     * Test de la méthode {@link Etudiant#getMessages()}.
     * Vérifie que l'on récupère bien tout les messages d'un etudiant.
     */
    @Test
    void testGetMessages() {
        List<Message> messages = etudiant.getMessages();
        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }
    
    /**
     * Test de la méthode {@link Etudiant#clone()}.
     * Vérifie que l'on clone bien l'objet etudiant et que la méthode equals fonctionne.
     * 
     * @throws CloneNotSupportedException si le clone n'est pas pris en charge.
     */
    @Test
    void testClone() throws CloneNotSupportedException {
    	Etudiant clone = this.etudiant.clone();
    	assertEquals(this.etudiant, clone);
    	assertTrue(this.etudiant.equals(clone));
    }
}

