package ui;

import formation.GestionEtudiant;
import formation.GestionFormation;
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
    private GestionFormation ges;

    public void setGes(GestionFormation ges) {
        this.ges = ges;
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

    GestionEtudiant gesEtudiant = new GestionEtudiant();

    @FXML
    void actionBoutonChoisirOption(ActionEvent event) {
        this.gesEtudiant.choisirOption(null);
    }

    @FXML
    void actionBoutonConnexion(ActionEvent event) {
        this.gesEtudiant.connexion(Integer.parseInt(entreeNumeroEtudiant.getText()),
                entreeMotDePasseEtudiant.getText());
    }

    @FXML
    void actionBoutonDeconnexion(ActionEvent event) {
        this.gesEtudiant.deconnexion();
    }

    @FXML
    void actionBoutonInscription(ActionEvent event) {
        InformationPersonnelle info = new InformationPersonnelle(entreeNomEtudiant.getText(),
                entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
                Integer.parseInt(entreeAgeEtudiant.getText()));
        this.ges.getGestionEtudiant().inscription(info, entreeMotDePasseEtudiant.getText());
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
