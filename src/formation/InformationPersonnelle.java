package formation;

import java.util.Objects;

/**
 * Cette classe représente les informations personnelles d'un étudiant, incluant
 * son identité (nom et prénom), son âge et son adresse.
 *
 * <p>
 * La classe est immuable, ce qui signifie que les propriétés nom, prénom, âge et
 * adresse ne peuvent pas être modifiées après la création de l'objet.
 * </p>
 *
 * <p>
 * Exemple d'utilisation :
 * <pre>{@code
 * // Création d'une instance avec les informations obligatoires
 * InformationPersonnelle personne1 = new InformationPersonnelle("Doe", "John");
 * 
 * // Création d'une instance avec toutes les informations
 * InformationPersonnelle personne2 = new InformationPersonnelle("Smith", "Alice", "123 Main St", 25);
 * }</pre>
 * </p>
 *
 * @author Eric Cariou
 */
public final class InformationPersonnelle implements java.io.Serializable {

  /**
   * Identifiant de s�rialisation.
   */
  private static final long serialVersionUID = 4026408353251835506L;

  /**
   * Le nom de la personne (ne peut pas �tre modifi�).
   */
  private final String nom;

  /**
   * Le pr�nom de la personne (ne peut pas �tre modifi�).
   */
  private final String prenom;

  /**
   * L'�ge de la personne (la valeur 0 correspond � un �ge non d�fini).
   */
  private int age;

  /**
   * L'adresse de la personne (une chaine vide "" correspond � une adresse non
   * d�finie).
   */
  private String adresse;

  /**
   * Renvoie le nom de la personne.
   *
   * @return le nom de la personne
   */
  public String getNom() {
    return nom;
  }

  /**
   * Renvoie le pr�nom de la personne.
   *
   * @return le pr�nom de la personne
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Renvoie l'�ge de la personne.
   *
   * @return l'�ge de la personne
   */
  public int getAge() {
    return age;
  }

  /**
   * Modifie l'�ge de la personne.
   *
   * @param age le nouvel age (doit �tre sup�rieur � 0)
   */
  public void setAge(int age) {
    if (age > 0) {
      this.age = age;
    }
  }

  /**
   * Renvoie l'adresse de la personne.
   *
   * @return l'adresse de la personne
   */
  public String getAdresse() {
    return adresse;
  }

  /**
   * Modifie l'adresse de la personne.
   *
   * @param adresse la nouvelle adresse (doit �tre diff�rente de null)
   */
  public void setAdresse(String adresse) {
    if (adresse != null) {
      this.adresse = adresse;
    }
  }

  /**
   * Cr�e une personne avec ses informations obligatoires.
   *
   * @param nom    le nom de la personne
   * @param prenom le pr�nom de la personne
   */
  public InformationPersonnelle(String nom, String prenom) {
    this(nom, prenom, "", 0);
  }

  /**
   * Cr�e une personne avec toutes ses informations.
   *
   * @param nom     le nom de la personne
   * @param prenom  le pr�nom de la personne
   * @param adresse l'adresse de la personne
   * @param age     l'age de la personne
   */
  public InformationPersonnelle(String nom, String prenom, String adresse, int age) {
    if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() && adresse != null
        && age >= 0) {
      this.nom = nom;
      this.prenom = prenom;
      this.adresse = adresse;
      this.age = age;
    } else {
      throw new IllegalArgumentException("Les paramètres ne sont pas valides.");
    }
  }

  /**
   * Retourne le hashcode de l'objet en prenant en compte ses propriétés.
   *
   * @return le hashcode de l'objet
   */
  @Override
  public int hashCode() {
    return Objects.hash(adresse, age, nom, prenom);
  }

  /**
   * Compare l'objet actuel avec un autre objet pour l'égalité.
   *
   * @param obj l'objet à comparer
   * @return true si les objets sont égaux, false sinon
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    InformationPersonnelle other = (InformationPersonnelle) obj;
    return Objects.equals(adresse, other.adresse) && age == other.age
        && Objects.equals(nom, other.nom)
        && Objects.equals(prenom, other.prenom);
  }

  /**
   * Retourne une représentation textuelle de l'objet.
   *
   * @return une chaîne de caractères représentant l'objet
   */
  @Override
  public String toString() {
    return prenom + " " + nom + " d'age " + age + " ans, habite " + adresse;
  }
}
