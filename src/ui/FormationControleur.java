package ui;

import java.net.URL;
import java.util.ResourceBundle;

import formation.GestionFormation;
import formation.UniteEnseignement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * Le contr�leur associ� � la fen�tre d�finie dans formation.fxml.
 *
 * @author Eric Cariou
 */
public class FormationControleur {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private CheckBox checkInscriptionFinalisee;

  @FXML
  private TextField entreeAdresseEtudiant;

  @FXML
  private TextField entreeAgeEtudiant;

  @FXML
  private TextField entreeCapaciteAccueil;

  @FXML
  private TextField entreeEmailResponsableFormation;

  @FXML
  private TextField entreeGroupeTDEtudiant;

  @FXML
  private TextField entreeGroupeTPEtudiant;

  @FXML
  private TextField entreeNomEtudiant;

  @FXML
  private TextField entreeNomFormation;

  @FXML
  private TextField entreeNomResponsableFormation;

  @FXML
  private TextField entreeNomResponsableUE;

  @FXML
  private TextField entreeNomUE;

  @FXML
  private TextField entreeNombreChoixOptions;

  @FXML
  private TextField entreePrenomEtudiant;

  @FXML
  private TextField entreeTailleGroupeTD;

  @FXML
  private TextField entreeTailleGroupeTP;

  @FXML
  private Label labelListeEtudiants;

  @FXML
  private Label labelNbGroupesTD;

  @FXML
  private Label labelNbGroupesTP;

  @FXML
  private ListView<String> listeEtudiants;
  ObservableList<String> listeEtudiantsObservableList = FXCollections.observableArrayList();

  @FXML
  private ListView<String> listeUEObligatoires;
  ObservableList<String> listeUEObligatoiresObservableList = FXCollections.observableArrayList();

  @FXML
  private ListView<String> listeUEOptionnelles;
  ObservableList<String> listeUEOptionnellesObservableList = FXCollections.observableArrayList();

  @FXML
  private ToggleGroup obligation;

  @FXML
  private RadioButton radioBoutonObligatoire;

  @FXML
  private RadioButton radioBoutonOptionnelle;

  GestionFormation ges = new GestionFormation();

  @FXML
  void actionBoutonAffectationAutomatique(ActionEvent event) {

  }

  @FXML
  void actionBoutonAffectationManuelleGroupes(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherEtudiantsGroupeTD(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherEtudiantsGroupeTP(ActionEvent event) {

  }

  @FXML
  void actionBoutonAfficherEtudiantsUEOptionnelle(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      ges.listeEtudiantsOption();
    }
  }

  @FXML
  void actionBoutonAfficherTousEtudiants(ActionEvent event) {

  }

  @FXML
  void actionBoutonCreerFormation(ActionEvent event) {
    ges.creerFormation(entreeNomFormation.getText(), entreeNomResponsableFormation.getText(),
        entreeEmailResponsableFormation.getText());
  }

  @FXML
  void actionBoutonNombreChoixOptions(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      ges.definirNombreOptions(Integer.parseInt(entreeNombreChoixOptions.getText()));
    }
  }

  @FXML
  void actionBoutonSetTailleGroupeTD(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      ges.setTailleGroupeDirige(Integer.parseInt(entreeTailleGroupeTD.getText()));
      labelNbGroupesTD.setText(ges.getTailleGroupeDirige() + "");
    }
  }

  @FXML
  void actionBoutonSetTailleGroupeTP(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      ges.setTailleGroupePratique(Integer.parseInt(entreeTailleGroupeTP.getText()));
      labelNbGroupesTP.setText(ges.getTailleGroupePratique() + "");
    }
  }

  @FXML
  void actionMenuApropos(ActionEvent event) {

  }

  @FXML
  void actionMenuCharger(ActionEvent event) {

  }

  @FXML
  void actionMenuQuitter(ActionEvent event) {

  }

  @FXML
  void actionMenuSauvegarder(ActionEvent event) {

  }

  @FXML
  void actionSelectionEtudiant(MouseEvent event) {

  }

  @FXML
  void actionSelectionUEObligatoire(MouseEvent event) {

  }

  @FXML
  void actionSelectionUEOptionnelle(MouseEvent event) {

  }

  @FXML
  void actionBoutonCreerNouvelleUE(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      UniteEnseignement ue = new UniteEnseignement(entreeNomUE.getText(), entreeNomResponsableUE.getText());
      try {
        if (radioBoutonObligatoire.isSelected()) {
          if (ges.ajouterEnseignementObligatoire(ue)) {
            listeUEObligatoiresObservableList.add(ue.getNomUE());
          }
        } else if (radioBoutonOptionnelle.isSelected()) {
          // Tentative de conversion de la capacité d'accueil en entier
          int capaciteAccueil = Integer.parseInt(entreeCapaciteAccueil.getText());

          // Si la conversion réussit, ajouter l'enseignement optionnel
          if (ges.ajouterEnseignementOptionnel(ue, capaciteAccueil)) {
            listeUEOptionnellesObservableList.add(ue.getNomUE());
          }
        }
      } catch (NumberFormatException e) {
        // Gérer le cas où la conversion en entier échoue
        System.err.println("Erreur : La capacité d'accueil doit être un entier.");
        // Ajouter d'autres actions si nécessaire, comme afficher un message à
        // l'utilisateur.
      }
    }
  }

  @FXML
  void initialize() {
    listeUEObligatoires.setItems(listeUEObligatoiresObservableList);
    listeEtudiants.setItems(listeEtudiantsObservableList);
    listeUEOptionnelles.setItems(listeUEOptionnellesObservableList);
  }
}
