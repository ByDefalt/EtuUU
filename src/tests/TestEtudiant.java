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

class TestEtudiant {

    private Etudiant etudiant;
    private Message message1;
    private Message message2;
    private UniteEnseignement ue1;
    private UniteEnseignement ue2;

    @BeforeEach
    void setUp() {
        InformationPersonnelle infoPerso = new InformationPersonnelle("Doe", "John");
        this.etudiant = new Etudiant(infoPerso, "password");

        this.ue1 = new UniteEnseignement("UE1", "Enseignant1");
        this.ue1.setNbPlacesMax(30);
        this.ue2 = new UniteEnseignement("UE2", "Enseignant2");
        this.ue2.setNbPlacesMax(25);
        
        String titre1 = "message1".substring(0, Math.min("message1".length(), 20)) + "...";  
        this.message1 = new Message(titre1, "message1");
        String titre2 = "message2".substring(0, Math.min("message2".length(), 20)) + "...";
        this.message2 = new Message(titre2, "message2");


        this.etudiant.addUE(ue1);
        this.etudiant.addUE(ue2);
        this.etudiant.getMessages().add(message1);
        this.etudiant.getMessages().add(message2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSetNumero() {
        etudiant.setNumero(123);
        assertEquals(123, etudiant.getNumero());
    }

    @Test
    void testSetNbOption() {
        etudiant.setNbOption(2);
        assertEquals(2, etudiant.getNbOption());
    }

    @Test
    void testSetNumeroTp() {
        etudiant.setNumeroTp(1);
        assertEquals(1, etudiant.getNumeroTp());
    }

    @Test
    void testSetNumeroTd() {
        etudiant.setNumeroTd(3);
        assertEquals(3, etudiant.getNumeroTd());
    }

    @Test
    void testAddUE() {
        UniteEnseignement newUE = new UniteEnseignement("UE3", "Enseignant3");
        etudiant.addUE(newUE);
        assertTrue(etudiant.getListeUEsuivies().contains(newUE));
    }

    @Test
    void testGetMessages() {
        List<Message> messages = etudiant.getMessages();
        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }

    @Test
    void testMessageLu() {
        assertFalse(message1.estLu());
        message1.setLu();
        assertTrue(message1.estLu());
    }
}

