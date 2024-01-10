package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import formation.Message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link formation.Message}.
 *
 * Cette classe contient des méthodes de test pour vérifier le bon fonctionnement
 * des fonctionnalités de la classe {@link formation.Message}.
 *
 * @author ROUSVAL ROMAIN
 * @see formation.Message
 */
class TestMessage {

    /**
     * Méthode exécutée avant chaque test pour effectuer des initialisations.
     *
     * @throws Exception si une exception survient lors de l'initialisation.
     */
    @BeforeEach
    void setUp() throws Exception {
        // Ajoutez les initialisations nécessaires avant chaque test si nécessaire.
    }

    /**
     * Méthode exécutée après chaque test pour effectuer des nettoyages.
     */
    @AfterEach
    void tearDown() {
        // Ajoutez les nettoyages nécessaires après chaque test si nécessaire.
    }

    /**
     * Teste la méthode {@link formation.Message#getTitre()}.
     */
    @Test
    public void testGetTitre() {
        Message monMessage = new Message("Titre de test", "Contenu de test");
        assertEquals("Titre de test", monMessage.getTitre());
    }

    /**
     * Teste la méthode {@link formation.Message#getContenu()}.
     */
    @Test
    public void testGetContenu() {
        Message monMessage = new Message("Titre de test", "Contenu de test");
        assertEquals("Contenu de test", monMessage.getContenu());
    }

    /**
     * Teste la méthode {@link formation.Message#estLu()}.
     */
    @Test
    public void testEstLu() {
        Message monMessageNonLu = new Message("Titre de test", "Contenu de test");
        assertFalse(monMessageNonLu.estLu());
        monMessageNonLu.setLu();
        assertTrue(monMessageNonLu.estLu());
    }

    /**
     * Teste la méthode {@link formation.Message#setLu()}.
     */
    @Test
    public void testSetLu() {
        Message monMessage = new Message("Titre de test", "Contenu de test");
        assertFalse(monMessage.estLu());
        monMessage.setLu();
        assertTrue(monMessage.estLu());
    }

    /**
     * Teste la méthode {@link formation.Message#clone()}.
     *
     * @throws CloneNotSupportedException si le clonage n'est pas pris en charge.
     */
    @Test
    public void testClone() throws CloneNotSupportedException {
        Message monMessage = new Message("Titre de test", "Contenu de test");
        Message cloneMessage = (Message) monMessage.clone();
        assertEquals(monMessage.getTitre(), cloneMessage.getTitre());
        assertEquals(monMessage.getContenu(), cloneMessage.getContenu());
        assertEquals(monMessage.estLu(), cloneMessage.estLu());
    }
}
