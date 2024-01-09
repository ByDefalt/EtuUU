package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.Message;
import formation.UniteEnseignement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.Message
 * InformationPersonnelle}.
 *
 * @author Eric Cariou
 * @see formation.Message
 */
class TestMessage {

    private Message mes;

    @BeforeEach
    void setUp() throws Exception {
        mes=new Message("titre", "contenu");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetTitre() {
        // Création d'un message pour le test
        Message monMessage = new Message("Titre de test", "Contenu de test");

        // Vérification du titre
        assertEquals("Titre de test", monMessage.getTitre());
    }

    @Test
    public void testGetContenu() {
        // Création d'un message pour le test
        Message monMessage = new Message("Titre de test", "Contenu de test");

        // Vérification du contenu
        assertEquals("Contenu de test", monMessage.getContenu());
    }

    @Test
    public void testEstLu() {
        // Création d'un message non lu pour le test
        Message monMessageNonLu = new Message("Titre de test", "Contenu de test");

        // Vérification que le message est initialisé comme non lu
        assertFalse(monMessageNonLu.estLu());

        // Marquer le message comme lu
        monMessageNonLu.setLu();

        // Vérification que le message est maintenant marqué comme lu
        assertTrue(monMessageNonLu.estLu());
    }

    @Test
    public void testSetLu() {
        // Création d'un message pour le test
        Message monMessage = new Message("Titre de test", "Contenu de test");

        // Vérification que le message est initialisé comme non lu
        assertFalse(monMessage.estLu());

        // Marquer le message comme lu
        monMessage.setLu();

        // Vérification que le message est maintenant marqué comme lu
        assertTrue(monMessage.estLu());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        // Création d'un message pour le test
        Message monMessage = new Message("Titre de test", "Contenu de test");

        // Clonage du message
        Message cloneMessage = (Message) monMessage.clone();

        // Vérification que le clone est équivalent à l'original
        assertEquals(monMessage.getTitre(), cloneMessage.getTitre());
        assertEquals(monMessage.getContenu(), cloneMessage.getContenu());
        assertEquals(monMessage.estLu(), cloneMessage.estLu());
    }
}