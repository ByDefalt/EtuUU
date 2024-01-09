package formation;

/**
 * Les fonctionnalités offertes pour un message.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Message {

	/**
	 * Le titre du message
	 */
	private final String titre;
    /**
     * Le contenu du message
     */
    private final String contenu;
    /**
     * Savoir si le message à été lu
     */
    private boolean lu;

    /**
     * Constructeur de la classe Message.
     *
     * @param contenu Le contenu du message.
     */
    public Message(String titre, String contenu) {
    	this.titre = titre;
        this.contenu = contenu;
        this.lu = false;
    }
    
    /**
     * Obtient le titre du message.
     *
     * @return Le titre du message.
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Obtient le contenu du message.
     *
     * @return Le contenu du message.
     */
    public String getContenu() {
        return this.contenu;
    }

    /**
     * Vérifie si le message a été lu.
     *
     * @return true si le message a été lu, false sinon.
     */
    public boolean estLu() {
        return this.lu;
    }

    /**
     * Marque le message comme lu.
     */
    public void setLu() {
        this.lu = true;
    }
}
