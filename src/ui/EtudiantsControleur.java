package ui;

import java.util.List;
import java.util.stream.Collectors;

import formation.Etudiant;
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

	public void setGes(GestionFormation gestionFormation) {
		this.gestionFormation = gestionFormation;
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
		try {
			String ueNom = listeUEOptionnellesFormation.getSelectionModel().getSelectedItem();

			if (ueNom != null) {
				for (UniteEnseignement ue : this.gestionFormation.getGestionEtudiant().getListeUE()) {
					if (ue.getNomUE().equals(ueNom)) {
						this.gestionFormation.getGestionEtudiant().choisirOption(ue);
						break;
					}
				}
			}

			ObservableList<String> listeUe = FXCollections
					.observableArrayList(this.gestionFormation.getGestionEtudiant().enseignementsSuivis()
							.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
			listeUESuiviesEtudiant.setItems(listeUe);
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
	void actionBoutonConnexion(ActionEvent event) {
		if (entreeNumeroEtudiant.getText().isEmpty() || entreeMotDePasseEtudiant.getText().isEmpty()) {
			this.afficherPopup("Les champs nécessaire a la connexion ne sont pas tous remplis !", AlertType.ERROR);
		} else {
			try {
				boolean res = this.gestionFormation.getGestionEtudiant().connexion(
						Integer.parseInt(entreeNumeroEtudiant.getText()),
						entreeMotDePasseEtudiant.getText());
				if (res) {
					Etudiant etudiant = this.gestionFormation.getGestionEtudiant().getEtudiantConnecte();
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
					if (this.gestionFormation.getGestionEtudiant().inscriptionFinalisee()) {
						checkInscriptionFinalisee.setSelected(true);
					} else {
						checkInscriptionFinalisee.setSelected(false);
					}
					ObservableList<String> listeUe = FXCollections
							.observableArrayList(etudiant.getListeUEsuivies()
									.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
					listeUESuiviesEtudiant.setItems(listeUe);

					ObservableList<String> listeUEOptionnelles = FXCollections
							.observableArrayList(this.gestionFormation.getGestionEtudiant().enseignementsOptionnels()
									.stream().map(UniteEnseignement::getNomUE).collect(Collectors.toSet()));
					listeUEOptionnellesFormation.setItems(listeUEOptionnelles);

					ObservableList<String> messages = FXCollections
							.observableArrayList(this.gestionFormation.getGestionEtudiant().listeTousMessages());
					listeTousMessages.setItems(messages);

					ObservableList<String> messagesNonLus = FXCollections
							.observableArrayList(this.gestionFormation.getGestionEtudiant().listeMessageNonLus());
					listeMessagesNonLus.setItems(messagesNonLus);
				} else {
					this.afficherPopup("Le numéro ou le mot de passe est incorrect.", AlertType.ERROR);
				}
			} catch (NumberFormatException e) {
				this.afficherPopup("Le numéro étudiant doit être un nombre.", AlertType.ERROR);
			} catch (NonConnecteException e) {
				this.afficherPopup("Erreur de connexion.", AlertType.ERROR);
			}
		}
	}

	@FXML
	void actionBoutonDeconnexion(ActionEvent event) {
		try {
			this.gestionFormation.getGestionEtudiant().deconnexion();

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
		if (!entreeNomEtudiant.getText().isEmpty() && !entreePrenomEtudiant.getText().isEmpty()
				&& !entreeAdresseEtudiant.getText().isEmpty()
				&& !entreeAgeEtudiant.getText().isEmpty() && !entreeMotDePasseEtudiant.getText().isEmpty()) {
			try {
				InformationPersonnelle info = new InformationPersonnelle(entreeNomEtudiant.getText(),
						entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
						Integer.parseInt(entreeAgeEtudiant.getText()));
				int resInscription = this.gestionFormation.getGestionEtudiant().inscription(info,
						entreeMotDePasseEtudiant.getText());

				for (Etudiant etudiant : this.gestionFormation.getGestionEtudiant().getListeEtudiants()) {
					if (etudiant.getNumero() == resInscription) {
						if (this.gestionFormation.getNBoption() != -1) {
							this.gestionFormation.setNbOptionEtudiant(etudiant);
						}
					}
				}
				if (resInscription != -1) {
					/*
					 * entreeNomEtudiant.setText("");
					 * entreePrenomEtudiant.setText("");
					 * entreeAdresseEtudiant.setText("");
					 * entreeAgeEtudiant.setText("");
					 */
					entreeNumeroEtudiant.setText(String.valueOf(resInscription));
				}
			} catch (NumberFormatException e) {
				this.afficherPopup("L'age doit être un nombre.", AlertType.ERROR);
			}
		} else {
			this.afficherPopup("Les champs nécessaire a l'inscription ne sont pas tous remplis !", AlertType.ERROR);
		}
	}

	@FXML
	void actionBoutonRafraichirListesMessages(ActionEvent event) {
		try {
			ObservableList<String> messages = FXCollections
					.observableArrayList(this.gestionFormation.getGestionEtudiant().listeTousMessages());
			listeTousMessages.setItems(messages);

			ObservableList<String> messagesNonLus = FXCollections
					.observableArrayList(this.gestionFormation.getGestionEtudiant().listeMessageNonLus());
			listeMessagesNonLus.setItems(messagesNonLus);
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
	void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {

		try {
			String selectMessage = listeMessagesNonLus.getSelectionModel().getSelectedItem();
			int selectIndex = listeMessagesNonLus.getSelectionModel().getSelectedIndex();
			if (selectMessage != null) {
				Message message = this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages().get(selectIndex);
				zoneTexteContenuMessage.setText(message.getContenu());
				message.setLu();
			}
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	@FXML
	void actionSelectionMessageListeTousMessages(MouseEvent event) {
		try {
			String selectMessage = listeTousMessages.getSelectionModel().getSelectedItem();
			int selectIndex = listeTousMessages.getSelectionModel().getSelectedIndex();

			if (selectMessage != null) {
				Message message = this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages()
						.get(selectIndex);
				zoneTexteContenuMessage.setText(message.getContenu());
				message.setLu();
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
