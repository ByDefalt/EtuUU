import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Map.Entry;

import formation.Etudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UniteEnseignement;

public class MainFormation {

  static void test1() {
    GestionFormation ges = new GestionFormation();
    ges.creerFormation("jsp1", "mpl", "mlp@gmail.com");
    for (int i = 0; i < 101; i++) {
      InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
      ;
      ges.getGestionEtudiant().inscription(inf, i + "");
    }
    ges.setTailleGroupeDirige(10);
    ges.setTailleGroupePratique(10);
    ges.attribuerAutomatiquementGroupes();
    ges.definirNombreOptions(5);
    UniteEnseignement ue = new UniteEnseignement("l", "l");
    assertTrue(ges.ajouterEnseignementOptionnel(ue, 60));
    UniteEnseignement ue2 = new UniteEnseignement("m", "m");
    assertTrue(ges.ajouterEnseignementOptionnel(ue2, 12));
    for (int i = 0; i < 50; i++) {
      ges.getGestionEtudiant().connexion(i, i + "");
      try {
        ges.getGestionEtudiant().choisirOption(ue);
      } catch (NonConnecteException e) {
        e.printStackTrace();
      }
      try {
        ges.getGestionEtudiant().deconnexion();
      } catch (NonConnecteException e) {
        e.printStackTrace();
      }
    }
    System.out.println(ges.listeEtudiantsOption(ue));
  }

  public static void main(String[] args) {
    test1();
  }
}
