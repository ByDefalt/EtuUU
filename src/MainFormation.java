import formation.Etudiant;
import formation.InformationPersonnelle;
import formation.UniteEnseignement;

public class MainFormation {

  static void test1() throws CloneNotSupportedException {
    Etudiant etu1=new Etudiant(new InformationPersonnelle("reg", "htrtr"), "htrr");
    Etudiant etu2=new Etudiant(new InformationPersonnelle("nbgrrt", "thrrgfv"), "bvsfx");
    etu1.getListeUEsuivies().add(new UniteEnseignement("vevfe", "nhgngh"));
    Etudiant etu3=etu1.clone();
    System.out.println(etu1);
    System.out.println(etu3);
  }

  public static void main(String[] args) throws CloneNotSupportedException {
    test1();
  }
}
