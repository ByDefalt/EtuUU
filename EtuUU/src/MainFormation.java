import formation.*;

public class MainFormation {

  static void test1(GestionFormation e) {
    GestionFormation ge=new GestionFormation();
    e.creerFormation("ki", "lo", "ju");
    e.creerFormation("ko", "li", "ja");
    e.creerFormation("ki", "lo", "ju");
    e.ccc();
    e.definirNombreOptions(9);
    e.definirNombreOptions(15);
    e.definirNombreOptions(86);
    e.setTailleGroupeDirige(20);
    e.setTailleGroupePratique(26);
    System.out.println("--------------------------------------------");
    ge.ccc();
    System.out.println(e);
  }

  public static void main(String[] args) {
    test1(new GestionFormation("lol", "huk", "ju"));
  }
}
