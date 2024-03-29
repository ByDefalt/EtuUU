package formation;

import java.io.Serializable;

/**
 * Cette classe représente un message avec un titre, un contenu, et un statut
 * de lecture. Les fonctionnalités offertes pour un message incluent la
 * récupération du titre et du contenu, ainsi que la vérification et la
 * modification du statut de lecture.
 *
 * <p>
 * Exemple d'utilisation :
 * 
 * <pre>
 * {
 *   &#64;code
 *   // Création d'un message
 *   Message monMessage =
 *       new Message("Nouvelle notification", "Vous avez un nouveau message.");
 *   
 *   // Obtention du titre et du contenu
 *   String titre = monMessage.getTitre();
 *   String contenu = monMessage.getContenu();
 *   
 *   // Vérification du statut de lecture
 *   boolean estLu = monMessage.estLu();
 *   
 *   // Marquer le message comme lu
 *   monMessage.setLu();
 * }
 * </pre>
 * </p>
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 */
public class Message implements Serializable, Cloneable {
  
  /**
   * Identifiant de s�rialisation.
   */
  private static final long serialVersionUID = 6196868605030678324L;
  
  /**
   * Le titre du message.
   */
  private final String titre;
  
  /**
   * Le contenu du message.
   */
  private final String contenu;
  
  /**
   * Indique si le message a été lu.
   */
  private boolean lu;
  
  /**
   * Constructeur de la classe Message.
   *
   * @param titre Le titre du message.
   * @param contenu Le contenu du message.
   */
  public Message(String titre, String contenu) {
    if (titre != null && !titre.isEmpty() && contenu != null
        && !contenu.isEmpty()) {
      this.titre = titre;
      this.contenu = contenu;
      this.lu = false;
    } else {
      throw new IllegalArgumentException(
          "Les arguments ne peuvent pas être nuls ou vide");
    }
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
  
  /**
   * Crée et renvoie une copie en profondeur de l'instance courante.
   * 
   * @return une copie de l'instance courante.
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
  
  /**
   * Compare cet objet {@code Message} avec un autre objet pour déterminer
   * l'égalité.
   * 
   * Deux objets {@code Message} sont considérés comme égaux si ils ont la
   * même valeur pour les attributs : titre, contenu et lu.
   * 
   * @param obj L'objet à comparer pour déterminer l'égalité.
   * @return {@code true} si les objets sont égaux, {@code false} sinon.
   * @see Object#equals(Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Message other = (Message) obj;
    if (titre == null) {
      if (other.titre != null)
        return false;
    } else if (!titre.equals(other.titre))
      return false;
    if (contenu == null) {
      if (other.contenu != null)
        return false;
    } else if (!contenu.equals(other.contenu))
      return false;
    if (lu != other.lu)
      return false;
    return true;
  }
  
  /**
   * Renvoie une représentation textuelle de cet objet {@code Message}.
   * 
   * La représentation inclut les valeurs des attributs : titre, contenu et lu.
   * 
   * @return Une chaîne de caractères représentant cet objet avec ses
   *         attributs.
   * @see Object#toString()
   */
  @Override
  public String toString() {
    return "Message [titre=" + titre + ", contenu=" + contenu + ", lu=" + lu
        + "]";
  }
}
