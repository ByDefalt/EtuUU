package ui;

import java.util.stream.Collectors;

import formation.Etudiant;
import formation.GestionEtudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.Message;
import formation.NonConnecteException;
import formation.UniteEnseignement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		String ueNom = listeUESuiviesEtudiant.getSelectionModel().getSelectedItem();
		if (ueNom != null) {
			for (UniteEnseignement ue : this.gestionEtudiant.getListeUE()) {
				if (ue.getNomUE().equals(ueNom)) {
					try {
						this.gestionEtudiant.choisirOption(ue);
					} catch (NonConnecteException e) {
						this.afficherPopup("Erreur de connexion", AlertType.ERROR);
					}
				}
			}
		}
	}

	@FXML
	void actionBoutonConnexion(ActionEvent event) {
		if (entreeNumeroEtudiant.getText().isEmpty() || entreeMotDePasseEtudiant.getText().isEmpty()) {
			this.afficherPopup("Les champs nécessaire a la connexion ne sont pas tous remplis !", AlertType.ERROR);
		} else {
			this.gestionEtudiant.connexion(Integer.parseInt(entreeNumeroEtudiant.getText()),
					entreeMotDePasseEtudiant.getText());
			try {
				Etudiant etudiant = this.gestionEtudiant.getEtudiantConnecte();
				entreeNomEtudiant.setText(etudiant.getInformationPersonnelle().getNom());
				entreePrenomEtudiant.setText(etudiant.getInformationPersonnelle().getPrenom());
				entreeAdresseEtudiant.setText(etudiant.getInformationPersonnelle().getAdresse());
				entreeAgeEtudiant.setText(String.valueOf(etudiant.getInformationPersonnelle().getAge()));
				if (etudiant.getNumeroTd() != -1) {
					entreeGroupeTD.setText(String.valueOf(etudiant.getNumeroTd()));
				} else {
					entreeGroupeTD.setText("non défini");
				}
				if (etudiant.getNumeroTp() != -1) {
					entreeGroupeTP.setText(String.valueOf(etudiant.getNumeroTp()));
				} else {
					entreeGroupeTP.setText("non défini");
				}
				if (etudiant.getNbOption() != -1) {
					entreeNombreOptions.setText(String.valueOf(etudiant.getNbOption()));
				} else {
					entreeNombreOptions.setText("non défini");
				}
				if (this.gestionEtudiant.inscriptionFinalisee()) {
					checkInscriptionFinalisee.setSelected(true);
				} else {
					checkInscriptionFinalisee.setSelected(false);
				}
				ObservableList<String> listeUe = FXCollections.observableArrayList(etudiant.getListeUEsuivies()
						.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
				listeUESuiviesEtudiant = new ListView<>(listeUe);

				ObservableList<String> listeUEOptionnelles = FXCollections
						.observableArrayList(this.gestionEtudiant.enseignementsOptionnels()
								.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
				listeUEOptionnellesFormation = new ListView<>(listeUEOptionnelles);

				ObservableList<String> messages = FXCollections
						.observableArrayList(this.gestionEtudiant.listeTousMessages());
				listeTousMessages = new ListView<>(messages);

				ObservableList<String> messagesNonLus = FXCollections
						.observableArrayList(this.gestionEtudiant.listeTousMessages());
				listeMessagesNonLus = new ListView<>(messagesNonLus);
			} catch (NonConnecteException e) {
				this.afficherPopup("Erreur de connexion", AlertType.ERROR);
			}
		}
	}

	@FXML
	void actionBoutonDeconnexion(ActionEvent event) {
		try {
			this.gestionEtudiant.deconnexion();

			entreeNumeroEtudiant.setText("");
			entreeMotDePasseEtudiant.setText("");
			entreeNomEtudiant.setText("");
			entreePrenomEtudiant.setText("");
			entreeAdresseEtudiant.setText("");
			entreeAgeEtudiant.setText("");
			entreeGroupeTD.setText("");
			entreeGroupeTP.setText("");
			entreeNombreOptions.setText("");
			checkInscriptionFinalisee.setSelected(false);

			listeUESuiviesEtudiant.getItems().clear();
			listeUEOptionnellesFormation.getItems().clear();
			listeTousMessages.getItems().clear();
			listeMessagesNonLus.getItems().clear();
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
    void actionBoutonInscription(ActionEvent event) {
    	if(!entreeNomEtudiant.getText().isEmpty() && !entreePrenomEtudiant.getText().isEmpty() && !entreeAdresseEtudiant.getText().isEmpty() 
    			&& !entreeAgeEtudiant.getText().isEmpty() && !entreeMotDePasseEtudiant.getText().isEmpty()) {
    		InformationPersonnelle info = new InformationPersonnelle(entreeNomEtudiant.getText(),
                    entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
                    Integer.parseInt(entreeAgeEtudiant.getText()));
            int resInscription = this.gestionEtudiant.inscription(info, entreeMotDePasseEtudiant.getText());
			
            for(Etudiant etudiant : this.gestionEtudiant.getListeEtudiants()) {
            	if(etudiant.getNumero() == resInscription) {
            		if(this.gestionFormation.getNBoption() != -1) {
            			this.gestionFormation.setNbOptionEtudiant(etudiant);
            		}
            	}
            }
            if(resInscription != -1) {
            	entreeNomEtudiant.setText("");
            	entreePrenomEtudiant.setText("");
            	entreeAdresseEtudiant.setText("");
            	entreeAgeEtudiant.setText("");
            	entreeNumeroEtudiant.setText(String.valueOf(resInscription));
            }
    	} else {
    		this.afficherPopup("Les champs nécessaire a l'inscription ne sont pas tous remplis !", AlertType.ERROR);
    	}
    }

	@FXML
	void actionBoutonRafraichirListesMessages(ActionEvent event) {
		try {
			for (Message message : this.gestionEtudiant.getEtudiantConnecte().getMessages()) {
				if (this.gestionEtudiant.listeMessageNonLus().contains(message.getTitre())) {
					message.setLu();
				}
			}

			ObservableList<String> messages = FXCollections
					.observableArrayList(this.gestionEtudiant.listeTousMessages());
			listeTousMessages = new ListView<>(messages);

			ObservableList<String> messagesNonLus = FXCollections
					.observableArrayList(this.gestionEtudiant.listeTousMessages());
			listeMessagesNonLus = new ListView<>(messagesNonLus);

		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
	void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {
		try {
			for (Message message : this.gestionEtudiant.getEtudiantConnecte().getMessages()) {
				if (this.gestionEtudiant.listeMessageNonLus().contains(message.getTitre())) {
					zoneTexteContenuMessage.setText(message.getContenu());
				}
			}
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
	void actionSelectionMessageListeTousMessages(MouseEvent event) {
		try {
			for (Message message : this.gestionEtudiant.getEtudiantConnecte().getMessages()) {
				if (this.gestionEtudiant.listeTousMessages().contains(message.getTitre())) {
					zoneTexteContenuMessage.setText(message.getContenu());
				}
			}
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	private void afficherPopup(String message, AlertType type) {
		Alert alert = new Alert(type);
		if (type == AlertType.ERROR) {
			alert.setTitle("Erreur");
		} else {
			alert.setTitle("Information");
		}
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.setResizable(true);
		alert.showAndWait();
	}

	@FXML
	void initialize() {
		// Vous pouvez ajouter des initialisations ici si nécessaire
	}
}

