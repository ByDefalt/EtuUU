package ui;

import java.util.stream.Collectors;

import formation.Etudiant;
import formation.GestionEtudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UniteEnseignement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private GestionFormation gestionFormation;
    private GestionEtudiant gestionEtudiant;

    public void setGes(GestionFormation gestionFormation) {
        this.gestionFormation = gestionFormation;
        this.gestionEtudiant = this.gestionFormation.getGestionEtudiant();
    }

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
    	// Implémentez cette méthode selon vos besoins
    }

    @FXML
    void actionBoutonConnexion(ActionEvent event) throws NonConnecteException{
        this.gestionEtudiant.connexion(Integer.parseInt(entreeNumeroEtudiant.getText()),
                entreeMotDePasseEtudiant.getText());
        try {
	        Etudiant etudiant = this.gestionEtudiant.getEtudiantConnecte();
	        entreeNomEtudiant.setText(etudiant.getInformationPersonnelle().getNom());
	        entreePrenomEtudiant.setText(etudiant.getInformationPersonnelle().getPrenom());
	        entreeAdresseEtudiant.setText(etudiant.getInformationPersonnelle().getAdresse());
	        entreeAgeEtudiant.setText(String.valueOf(etudiant.getInformationPersonnelle().getAge()));
	        entreeGroupeTD.setText(String.valueOf(etudiant.getNumeroTd()));
	        entreeGroupeTP.setText(String.valueOf(etudiant.getNumeroTp()));
	        entreeNombreOptions.setText(String.valueOf(etudiant.getNbOption()));
	        if (this.gestionEtudiant.inscriptionFinalisee()) {
	            checkInscriptionFinalisee.setSelected(true);
	        } else {
	            checkInscriptionFinalisee.setSelected(false);
	        }
	        ObservableList<String> listeUe = FXCollections.observableArrayList(etudiant.getListeUEsuivies()
	        		.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
	        listeUESuiviesEtudiant = new ListView<>(listeUe);
	        
	        ObservableList<String> listeUEOptionnelles = FXCollections.observableArrayList(this.gestionEtudiant.enseignementsOptionnels()
	        		.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
	        listeUEOptionnellesFormation = new ListView<>(listeUEOptionnelles);
	        ObservableList<String> messages = FXCollections.observableArrayList(this.gestionEtudiant.listeTousMessages());
	        listeTousMessages = new ListView<>(messages);
	        
	        ObservableList<String> messagesNonLus = FXCollections.observableArrayList(this.gestionEtudiant.listeTousMessages());
	        listeMessagesNonLus = new ListView<>(messagesNonLus);
        } catch (NonConnecteException e) {
        	System.out.println(e);
        }
    }

    @FXML
    void actionBoutonDeconnexion(ActionEvent event) throws NonConnecteException {
        this.gestionEtudiant.deconnexion();
    }

    @FXML
    void actionBoutonInscription(ActionEvent event) {
        InformationPersonnelle info = new InformationPersonnelle(entreeNomEtudiant.getText(),
                entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
                Integer.parseInt(entreeAgeEtudiant.getText()));
        int resInscription = this.gestionEtudiant.inscription(info, entreeMotDePasseEtudiant.getText());
        if(resInscription != -1) {
        	entreeNomEtudiant.setText(null);
        	entreePrenomEtudiant.setText(null);
        	entreeAdresseEtudiant.setText(null);
        	entreeAgeEtudiant.setText(null);
        	entreeNumeroEtudiant.setText(String.valueOf(resInscription));
        }
        
    }

    @FXML
    void actionBoutonRafraichirListesMessages(ActionEvent event) {
        // Implémentez cette méthode selon vos besoins
    }

    @FXML
    void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {
        // Implémentez cette méthode selon vos besoins
    }

    @FXML
    void actionSelectionMessageListeTousMessages(MouseEvent event) {
        // Implémentez cette méthode selon vos besoins
    }

    @FXML
    void initialize() {
        // Vous pouvez ajouter des initialisations ici si nécessaire
    }
}
