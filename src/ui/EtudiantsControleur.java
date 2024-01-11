package ui;

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

	/**
	 * Gère l'action associée au bouton "Choisir Option".
	 * Cette méthode est appelée lorsqu'un utilisateur clique sur le bouton pour choisir une option
	 * parmi les Unités d'Enseignement optionnelles de la formation.
	 * Si l'utilisateur est connecté, il peut choisir une option, et la liste des Unités d'Enseignement
	 * suivies par l'étudiant est mise à jour.
	 *
	 * @param event L'événement associé au clic sur le bouton.
	 */
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

	/**
	 * Gère l'action associée au bouton "Connexion".
	 * Cette méthode est appelée lorsqu'un utilisateur tente de se connecter en utilisant le numéro
	 * d'étudiant et le mot de passe saisis. Si les champs nécessaires à la connexion sont remplis,
	 * elle vérifie la validité de la connexion en utilisant la méthode de la gestion des étudiants.
	 * Si la connexion réussit, les informations de l'étudiant connecté sont affichées dans l'interface,
	 * y compris les détails personnels, les groupes TD/TP, le nombre d'options, l'état de l'inscription,
	 * les Unités d'Enseignement suivies, les Unités d'Enseignement optionnelles disponibles, et les messages.
	 * En cas d'échec de la connexion, un message d'erreur approprié est affiché.
	 *
	 * @param event L'événement associé au clic sur le bouton de connexion.
	 */
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

	/**
	 * Gère l'action associée au bouton "Déconnexion".
	 * Cette méthode est appelée lorsqu'un utilisateur souhaite se déconnecter. Elle appelle la méthode
	 * de déconnexion de la gestion des étudiants. Si la déconnexion réussit, elle réinitialise les champs
	 * d'entrée et les éléments d'interface graphique associés à l'étudiant connecté.
	 *
	 * @param event L'événement associé au clic sur le bouton de déconnexion.
	 */
	@FXML
	void actionBoutonRafraichirListesMessages(ActionEvent event) {
		try {
			ObservableList<String> messages = FXCollections
					.observableArrayList(this.gestionFormation.getGestionEtudiant().listeTousMessages());
			listeTousMessages.setItems(messages);
			ObservableList<String> messagesNonLus = FXCollections
					.observableArrayList(this.gestionFormation.getGestionEtudiant().listeMessageNonLus());
			listeMessagesNonLus.setItems(messagesNonLus);
			zoneTexteContenuMessage.setText("");
			listeTousMessages.getSelectionModel().clearSelection();
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	/**
	 * Gère l'action associée à la sélection d'un message dans la liste des messages non lus.
	 * Cette méthode est appelée lorsqu'un utilisateur sélectionne un message dans la liste des messages non lus.
	 * Elle affiche le contenu du message sélectionné dans la zone de texte prévue à cet effet et marque le message comme lu.
	 *
	 * @param event L'événement associé à la sélection d'un message dans la liste des messages non lus.
	 */
	@FXML
	void actionSelectionMessageListeMessagesNonLus(MouseEvent event) {
		try {
			String selectMessage = listeMessagesNonLus.getSelectionModel().getSelectedItem();
			int selectIndex = listeMessagesNonLus.getSelectionModel().getSelectedIndex()+
			this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages().
			stream().filter(mes->mes.estLu()==true).collect(Collectors.toList()).size();
			if (selectMessage != null) {
				Message message = this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages().get(selectIndex);
				zoneTexteContenuMessage.setText(message.getContenu());
				message.setLu();
			}
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	/**
	 * Gère l'action associée à la sélection d'un message dans la liste de tous les messages.
	 * Cette méthode est appelée lorsqu'un utilisateur sélectionne un message dans la liste de tous les messages.
	 * Elle affiche le contenu du message sélectionné dans la zone de texte prévue à cet effet et marque le message comme lu.
	 *
	 * @param event L'événement associé à la sélection d'un message dans la liste de tous les messages.
	 */
	@FXML
	void actionSelectionMessageListeTousMessages(MouseEvent event) {
		try {
			String selectMessage = listeTousMessages.getSelectionModel().getSelectedItem();
			int selectIndex = listeTousMessages.getSelectionModel().getSelectedIndex();

			if (selectMessage != null) {
				Message message = this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages().get(selectIndex);
				zoneTexteContenuMessage.setText(message.getContenu());
				this.gestionFormation.getGestionEtudiant().getEtudiantConnecte().getMessages().get(selectIndex).setLu();
			}
		} catch (NonConnecteException e) {
			this.afficherPopup("Vous n'êtes pas connecté", AlertType.ERROR);
		}
	}

	/**
	 * Affiche une boîte de dialogue (popup) avec un message spécifié et un type
	 * d'alerte donné.
	 *
	 * @param message Le message à afficher dans la boîte de dialogue.
	 * @param type    Le type d'alerte (Erreur, Information, etc.).
	 *                Utilisez les constantes de la classe AlertType, par exemple
	 *                AlertType.ERROR ou AlertType.INFORMATION.
	 * 
	 * @see javafx.scene.control.Alert.AlertType
	 */
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
