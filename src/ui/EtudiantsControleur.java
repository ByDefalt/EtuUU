package ui;

import java.net.URL;
import java.util.ResourceBundle;

import formation.GestionEtudiant;
import formation.InformationPersonnelle;
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
  private java.awt.TextField entreeAdresseEtudiant;
  
  @FXML
  private java.awt.TextField entreeAgeEtudiant;
  
  @FXML
  private TextField entreeGroupeTD;
  
  @FXML
  private TextField entreeGroupeTP;
  
  @FXML
  private java.awt.TextField entreeMotDePasseEtudiant;
  
  @FXML
  private java.awt.TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNombreOptions;
  
  @FXML
  private java.awt.TextField entreeNumeroEtudiant;
  
  @FXML
  private java.awt.TextField entreePrenomEtudiant;
  
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
  
  GestionEtudiant gesEtudiant = new GestionEtudiant();
  
  @FXML
  void actionBoutonChoisirOption(ActionEvent event) {
	  this.gesEtudiant.choisirOption(null);
  }
  
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    this.gesEtudiant.connexion(Integer.parseInt(entreeNumeroEtudiant.getText()), entreeMotDePasseEtudiant.getText());
  }
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    this.gesEtudiant.deconnexion();
  }
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
	  InformationPersonnelle info = new InformationPersonnelle(entreeNomEtudiant.getText(), entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(), Integer.parseInt(entreeAgeEtudiant.getText()));
	  this.gesEtudiant.inscription(info, entreeMotDePasseEtudiant.getText());
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
