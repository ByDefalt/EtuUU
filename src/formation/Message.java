package formation;

/**
 * Les fonctionnalités offertes à un message.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Message {
    // ******************************* ATTRIBUT D'INSTANCES
    private final String contenu;
    private boolean lu;

    /**
     * Constructeur de la classe Message.
     *
     * @param contenu Le contenu du message.
     */
    public Message(String contenu) {
        this.contenu = contenu;
        this.lu = false;
    }

    /**
     * Obtient le contenu du message.
     *
     * @return Le contenu du message.
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Vérifie si le message a été lu.
     *
     * @return true si le message a été lu, false sinon.
     */
    public boolean estLu() {
        return lu;
    }

    /**
     * Marque le message comme lu.
     */
    public void setLu() {
        this.lu = true;
    }
}
