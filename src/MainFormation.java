import formation.GestionFormation;
import formation.UniteEnseignement;

public class MainFormation {

  static void test1() {
    GestionFormation ges1=new GestionFormation();
    ges1.creerFormation("jsp1", "mpl", "mlp@gmail.com");
    boolean res;
    UniteEnseignement ue=new UniteEnseignement("lkj", "kuin");
    res=ges1.ajouterEnseignementObligatoire(ue);
    res=ges1.ajouterEnseignementOptionnel(ue, 0);
    System.out.println(res);
  }

  public static void main(String[] args) {
    test1();
  }
}
