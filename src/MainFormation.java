import java.util.HashSet;
import java.util.Map;
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
    for (int i = 0; i < 100; i++) {
      InformationPersonnelle inf = new InformationPersonnelle(i + "", i + "");
      Etudiant etu = new Etudiant(inf, i + "");
      ges1.getListeEtudiants().add(etu);
    }
    ges1.setTailleGroupeDirige(10);
    ges1.setTailleGroupePratique(10);
    ges1.attribuerAutomatiquementGroupes();
    InformationPersonnelle inf = new InformationPersonnelle("l", "l");
    Etudiant etu = new Etudiant(inf, "l");
    ges1.getListeEtudiants().add(etu);
    ges1.attribuerAutomatiquementGroupes();
    for (Entry<Integer, Set<Etudiant>> entry : ges1.getTds().entrySet()) {
      System.out.println("Clé : " + entry.getKey() +" ,nb element : "+entry.getValue().size()+ ", Valeur : " + entry.getValue());
    }
    for (Entry<Integer, Set<Etudiant>> entry : ges1.getTps().entrySet()) {
      System.out.println("Clé : " + entry.getKey() +" ,nb element : "+entry.getValue().size()+ ", Valeur : " + entry.getValue());
    }
  }

  public static void main(String[] args) {
    test1();
  }
}
