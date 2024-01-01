package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Le contrôleur associé à la fenêtre définie dans etudiants.fxml.
 *
 * @author Eric Cariou
 */
public class EtudiantsControleur {
  
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
  private TextField entreeGroupeTD;
  
  @FXML
  private TextField entreeGroupeTP;
  
  @FXML
  private TextField entreeMotDePasseEtudiant;
  
  @FXML
  private TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNombreOptions;
  
  @FXML
  private TextField entreeNumeroEtudiant;
  
  @FXML
  private TextField entreePrenomEtudiant;
  
  @FXML
  private ListView<String> listeMessagesNonLus;
  
  @FXML
  private ListView<String> listeTousMessages;
  
  @FXML
  private ListView<String> listeUEOptionnellesFormation;
  
  @FXML
  private ListView<String> listeUESuiviesEtudiant;
  
  @FXML
  private TextArea zoneTexteContenuMessage;
  
  @FXML
  void actionBoutonChoisirOption(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonRafraichirListesMessages(ActionEvent event) {
    
  }
  
  @FXML
  void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {
    
  }
  
  @FXML
  void actionSelectionMessageListeTousMessages(MouseEvent event) {
    
  }
  
  @FXML
  void initialize() {}
  
}
