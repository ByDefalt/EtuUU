import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.Map.Entry;

import formation.Etudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.UniteEnseignement;

public class MainFormation {

  static void test1() {
    GestionFormation ges1 = new GestionFormation();
    ges1.creerFormation("jsp1", "mpl", "mlp@gmail.com");
    boolean res;
    UniteEnseignement ue = new UniteEnseignement("lkj", "kuin");
    res = ges1.ajouterEnseignementObligatoire(ue);
    res = ges1.ajouterEnseignementOptionnel(ue, 0);
    for (int i = 0; i < 101; i++) {
      InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
      ges1.getGestionEtudiant().inscription(inf, i+"");
    }
    ges1.setTailleGroupeDirige(10);
    ges1.setTailleGroupePratique(10);
    ges1.attribuerAutomatiquementGroupes();
    //ges1.changerGroupe(ges1.listeEtudiantsGroupeDirige(1).iterator().next(), 9, 0);
    for (Entry<Integer, Set<Etudiant>> entry : ges1.getTds().entrySet()) {
      System.out.println("Clé : " + entry.getKey() +" ,nb element : "+entry.getValue().size()+ ", Valeur : " + entry.getValue());
    }
  }

  public static void main(String[] args) {
    test1();
  }
}
