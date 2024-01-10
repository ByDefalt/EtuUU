
import java.io.IOException;

import formation.*;

public class MainFormation {

  static void test1() throws CloneNotSupportedException {
    GestionFormation ges=new GestionFormation();
    ges.creerFormation("hgfd", "hgfd", "hfdg@gdfsg.nugdfsdgll");
    ges.setTailleGroupeDirige(5);
    ges.setTailleGroupePratique(5);
    ges.definirNombreOptions(5);
    ges.ajouterEnseignementObligatoire(new UniteEnseignement("vfed", "dsgfd"));
    ges.ajouterEnseignementOptionnel(new UniteEnseignement("vfed", "dsgfd"),10);
    for(int i=1;i<2;i++){
      ges.getGestionEtudiant().inscription(new InformationPersonnelle(i+"", i+""), i+"");
    }
    ges.attribuerAutomatiquementGroupes();
    System.out.println(ges+"\n\n");
    GestionFormation ges2=new GestionFormation();
    ges2.copierDepuis(ges);
    System.out.println(ges2);
  }

  public static void main(String[] args) throws CloneNotSupportedException {
    test1();
  }
}
