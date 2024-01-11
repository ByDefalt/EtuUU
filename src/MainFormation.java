import static org.junit.jupiter.api.Assertions.assertEquals;

import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UniteEnseignement;

public class MainFormation {

  static void test1() throws NonConnecteException {
    // scénario 1
    GestionFormation ges = new GestionFormation();
    ges.creerFormation("L3 informatique", "Dark Vador", "dark.vador@empire.com");
    UniteEnseignement ue1 = new UniteEnseignement("Java 2", "Mickaël Kerboeuf");
    UniteEnseignement ue2 = new UniteEnseignement("Conception d'applications", "Eric Cariou");
    UniteEnseignement ue3 = new UniteEnseignement("Programmation C avancée", "Stéphane Rubini");
    UniteEnseignement ue4 = new UniteEnseignement("Objets connectés et robotique", "Yvon Autret");
    UniteEnseignement ue5 = new UniteEnseignement("Administration système", "Laurent Nana");
    ges.ajouterEnseignementObligatoire(ue1);
    ges.ajouterEnseignementObligatoire(ue2);
    ges.ajouterEnseignementObligatoire(ue3);
    ges.ajouterEnseignementOptionnel(ue4, 3);
    ges.ajouterEnseignementOptionnel(ue5, 3);
    ges.setTailleGroupeDirige(3);
    ges.setTailleGroupePratique(2);
    ges.definirNombreOptions(1);
    // scénario 2
    ges.getGestionEtudiant().inscription(new InformationPersonnelle("Skywalker", "Luke", "pas", 20), "mp");
    ges.getGestionEtudiant().inscription(new InformationPersonnelle("Kenobi", "Obiwan", "pas", 20), "mp");
    ges.getGestionEtudiant().inscription(new InformationPersonnelle("Organa", "Leia", "pas", 20), "mp");
    ges.getGestionEtudiant().inscription(new InformationPersonnelle("Solo", "Han", "pas", 20), "mp");
    ges.getGestionEtudiant().connexion(0, "mp");
    ges.getGestionEtudiant().choisirOption(ue4);
    ges.getGestionEtudiant().deconnexion();
    ges.getGestionEtudiant().connexion(1, "mp");
    ges.getGestionEtudiant().choisirOption(ue4);
    ges.getGestionEtudiant().deconnexion();
    ges.getGestionEtudiant().connexion(2, "mp");
    ges.getGestionEtudiant().choisirOption(ue4);
    ges.getGestionEtudiant().deconnexion();
    ges.getGestionEtudiant().connexion(3, "mp");
    if (!ges.getGestionEtudiant().choisirOption(ue4)) {
      ges.getGestionEtudiant().choisirOption(ue5);
    }
    ges.getGestionEtudiant().deconnexion();
    ges.getGestionEtudiant().connexion(1, "mp");
    System.out.println(ges.getGestionEtudiant().getNumeroGroupeTravauxDiriges());
    System.out.println(ges.getGestionEtudiant().getNumeroGroupeTravauxPratiques());
    ges.attribuerAutomatiquementGroupes();
    System.out.println(ges.getTds().get(1));
    System.out.println(ges.getTds().get(2));
    System.out.println(ges.getTps().get(1));
    System.out.println(ges.getTps().get(2));
    System.out.println(ges.listeEtudiantsOption(ue4));
    System.out.println(ges.listeEtudiantsOption(ue5)+"\n\n");
    System.out.println(ges.getGestionEtudiant().listeMessageNonLus());System.out.println(ges.getGestionEtudiant().getNumeroGroupeTravauxDiriges());
    System.out.println(ges.getGestionEtudiant().getNumeroGroupeTravauxPratiques());
    System.out.println(ges.getGestionEtudiant().getEtudiantConnecte().getListeUEsuivies());
  }

  public static void main(String[] args) throws NonConnecteException {
    test1();
  }
}
