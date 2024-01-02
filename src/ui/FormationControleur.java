package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  
  @FXML
  private ListView<String> listeUEObligatoires;
  
  @FXML
  private ListView<String> listeUEOptionnelles;
  
  @FXML
  private ToggleGroup obligation;
  
  @FXML
  private RadioButton radioBoutonObligatoire;
  
  @FXML
  private RadioButton radioBoutonOptionnelle;
  
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
    
  }
  
  @FXML
  void actionBoutonAfficherTousEtudiants(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonCreerFormation(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonNombreChoixOptions(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTD(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTP(ActionEvent event) {
    
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
    
  }
  @FXML
  void initialize() {
    
  }
}
