package ui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import formation.Etudiant;
import formation.GestionFormation;
import formation.InformationPersonnelle;
import formation.UniteEnseignement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Le contr�leur associ� � la fen�tre d�finie dans formation.fxml.
 *
 * @author Eric Cariou
 */
public class FormationControleur {
  private GestionFormation ges;
  private Stage fenetreEtudiants;
  private Stage FenetreFormation;

  public void setFenetreFormation(Stage FenetreFormation) {
    this.FenetreFormation = FenetreFormation;
  }

  public void setFenetreEtudiants(Stage fenetreEtudiants) {
    this.fenetreEtudiants = fenetreEtudiants;
  }

  public void setGes(GestionFormation ges) {
    this.ges = ges;
  }

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

  /**
   * Réagit au clic sur le bouton d'affectation automatique en vérifiant d'abord
   * si une formation est sélectionnée.
   * Si une formation est sélectionnée et que les tailles de groupe dirigé et
   * pratique sont spécifiées,
   * la méthode attribue automatiquement les groupes de travaux dirigés et
   * pratiques via l'appel de la méthode
   * {@link GestionFormation#attribuerAutomatiquementGroupes()}.
   * Ensuite, met à jour les labels affichant le nombre de groupes de travaux
   * dirigés et pratiques affectés.
   * En cas d'absence de formation, affiche une alerte d'erreur indiquant "Aucune
   * Formation".
   * Si les tailles des groupes ne sont pas définies, affiche une alerte d'erreur
   * indiquant "Les tailles des groupes non définies".
   *
   * @param event L'événement déclenché par le clic sur le bouton.
   */
  @FXML
  void actionBoutonAffectationAutomatique(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (ges.getTailleGroupeDirige() != -1 && ges.getTailleGroupePratique() != -1) {
        ges.attribuerAutomatiquementGroupes();
        labelNbGroupesTD.setText(Integer.toString(ges.nombreGroupesTravauxDiriges()));
        labelNbGroupesTP.setText(Integer.toString(ges.nombreGroupesTravauxPratiques()));
      } else {
        this.afficherPopup("Les taille des groupe non définis", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action déclenchée lorsqu'un bouton d'affectation manuelle de groupes
   * est activé.
   * Vérifie si une formation est sélectionnée, puis tente de déplacer un étudiant
   * vers un nouveau groupe de travaux dirigés (TD) et travaux pratiques (TP) en
   * fonction des valeurs saisies dans les champs de texte.
   * Affiche une alerte d'erreur en fonction du résultat du changement de groupe.
   *
   * @param event L'événement déclencheur de l'action.
   */
  @FXML
  void actionBoutonAffectationManuelleGroupes(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().stream()
          .filter(etudiant -> Integer.toString(etudiant.getNumero())
              .equals(listeEtudiants.getSelectionModel().getSelectedItem()))
          .findFirst()
          .orElse(null);
      // Résultats possibles
      int resultat = ges.changerGroupe(
          etu,
          Integer.parseInt(entreeGroupeTDEtudiant.getText()),
          Integer.parseInt(entreeGroupeTPEtudiant.getText()));

      switch (resultat) {
        case 0:
          break;
        case -1:
          this.afficherPopup("Le déplacement de TD n'a pas pu être fait.", AlertType.ERROR);
          break;
        case -2:
          this.afficherPopup("Le déplacement de TP n'a pas pu être fait.", AlertType.ERROR);
          break;
        case -3:
          this.afficherPopup("Les déplacements de TD et de TP n'ont pas pu être faits.", AlertType.ERROR);
          break;
        default:
          this.afficherPopup("Opération non réussie", AlertType.ERROR);
          break;
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action associée au bouton d'affichage des étudiants d'un groupe de
   * Travaux Dirigés (TD).
   * Affiche la liste des étudiants appartenant au groupe de TD spécifié.
   * Affiche une alerte d'erreur en fonction des diférentes erreurs.
   *
   * @param event L'événement déclenché par l'utilisateur.
   */
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTD(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeGroupeTDEtudiant.getText() != null && !entreeGroupeTDEtudiant.getText().isEmpty()) {
        try {
          int a = Integer.parseInt(entreeGroupeTDEtudiant.getText());
          if (a > 0 && a <= ges.nombreGroupesTravauxDiriges()) {
            ObservableList<String> observableEtudiants = FXCollections.observableArrayList(
                Optional.ofNullable(ges.listeEtudiantsGroupeDirige(Integer.parseInt(entreeGroupeTDEtudiant.getText())))
                    .map(liste -> liste.stream()
                        .map(etudiant -> Integer.toString(etudiant.getNumero()))
                        .collect(Collectors.toList()))
                    .orElse(Collections.emptyList()));
            listeEtudiants.setItems(observableEtudiants);
            labelListeEtudiants.setText("Les étudiants du groupe de TD " + entreeGroupeTDEtudiant.getText());
          } else {
            this.afficherPopup("Le groupe n'existe pas", AlertType.ERROR);
          }
        } catch (NumberFormatException e) {
          this.afficherPopup("Le champ doit être un entier", AlertType.ERROR);
        }
      } else {
        this.afficherPopup("Le champ est vide", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action associée au bouton d'affichage des étudiants d'un groupe de
   * Travaux Pratiques (TP).
   * Affiche la liste des étudiants appartenant au groupe de TP spécifié.
   * Affiche une alerte d'erreur en fonction des diférentes erreurs.
   *
   * @param event L'événement déclenché par l'utilisateur.
   */
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTP(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeGroupeTPEtudiant.getText() != null && !entreeGroupeTPEtudiant.getText().isEmpty()) {
        try {
          int a = Integer.parseInt(entreeGroupeTPEtudiant.getText());
          if (a > 0 && a <= ges.nombreGroupesTravauxPratiques()) {
            ObservableList<String> observableEtudiants = FXCollections.observableArrayList(
                Optional.ofNullable(ges.listeEtudiantsGroupePratique(a))
                    .map(liste -> liste.stream()
                        .map(etudiant -> Integer.toString(etudiant.getNumero()))
                        .collect(Collectors.toList()))
                    .orElse(Collections.emptyList()));
            listeEtudiants.setItems(observableEtudiants);
            labelListeEtudiants.setText("Les étudiants du groupe de TP " + entreeGroupeTPEtudiant.getText());
          } else {
            this.afficherPopup("Le groupe n'existe pas", AlertType.ERROR);
          }
        } catch (NumberFormatException e) {
          this.afficherPopup("Le champ doit être un entier", AlertType.ERROR);
        }
      } else {
        this.afficherPopup("Le champ est vide", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action associée au bouton permettant d'afficher les étudiants inscrits
   * à une Unité d'Enseignement (UE) optionnelle sélectionnée.
   * Affiche une alerte d'erreur en fonction des diférentes erreurs.
   *
   * @param event L'événement déclencheur de l'action.
   */
  @FXML
  void actionBoutonAfficherEtudiantsUEOptionnelle(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      try {
        ObservableList<String> observableEtudiants = FXCollections.observableArrayList(ges
            .listeEtudiantsOption(ges.getGestionEtudiant().getListeUE().stream()
                .filter(ue -> ue.getNomUE().equals(listeUEOptionnelles.getSelectionModel().getSelectedItem()))
                .findFirst()
                .orElse(null))
            .stream()
            .map(etudiant -> Integer.toString(etudiant.getNumero()))
            .collect(Collectors.toSet()));
        if (observableEtudiants.size() != 0) {
          listeEtudiants.setItems(observableEtudiants);
          labelListeEtudiants
              .setText("Les étudiants inscrits à " + listeUEOptionnelles.getSelectionModel().getSelectedItem());
        } else {
          this.afficherPopup("Aucun Étudiants est inscrit à l'UE", AlertType.ERROR);
        }
      } catch (NullPointerException e) {
        this.afficherPopup("Aucune UE Optionel selectioner", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action liée au bouton d'affichage de tous les étudiants de la
   * formation.
   * Si le nom de la formation n'est pas nul et s'il y a des étudiants dans la
   * formation,
   * cette méthode crée une liste observable des numéros d'étudiants et l'assigne
   * à un composant graphique ListView.
   * Sinon, elle affiche un message d'erreur approprié.
   *
   * @param event L'événement déclenché par l'action du bouton.
   */
  @FXML
  void actionBoutonAfficherTousEtudiants(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (ges.getGestionEtudiant().getListeEtudiants().size() != 0) {
        ObservableList<String> observableEtudiants = FXCollections
            .observableArrayList(ges.getGestionEtudiant().getListeEtudiants().stream()
                .map(etudiant -> Integer.toString(etudiant.getNumero()))
                .collect(Collectors.toList()));
        listeEtudiants.setItems(observableEtudiants);
        labelListeEtudiants.setText("Tous les étudiants de la formation");
      } else {
        this.afficherPopup("Aucun Étudiants dans la formation", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action associée au bouton de création d'une nouvelle formation.
   * Cette méthode récupère les informations de la nouvelle formation à partir des
   * champs de saisie,
   * puis utilise l'objet GestionFormation (ges) pour créer la formation
   * correspondante.
   * Si la création réussit, les champs de saisie sont réinitialisés et certaines
   * étiquettes et listes sont effacées.
   * En cas d'échec, des messages d'erreur appropriés sont affichés sous forme de
   * pop-ups.
   *
   * @param event L'événement déclenché par l'action du bouton.
   */
  @FXML
  void actionBoutonCreerFormation(ActionEvent event) {
    ges.creerFormation(entreeNomFormation.getText(), entreeNomResponsableFormation.getText(),
        entreeEmailResponsableFormation.getText());
    if (ges.getNomFormation() != null) {
      labelNbGroupesTD.setText("...");
      labelNbGroupesTP.setText("...");
      entreeTailleGroupeTD.setText("");
      entreeTailleGroupeTP.setText("");
      entreeNombreChoixOptions.setText("");
      entreeNomResponsableUE.setText("");
      entreeCapaciteAccueil.setText("");
      entreeNomUE.setText("");
      listeUEObligatoires.getItems().clear();
      listeEtudiants.getItems().clear();
      listeUEOptionnelles.getItems().clear();
      entreeNomEtudiant.setText("");
      entreePrenomEtudiant.setText("");
      entreeAdresseEtudiant.setText("");
      entreeAgeEtudiant.setText("");
      entreeGroupeTDEtudiant.setText("");
      entreeGroupeTPEtudiant.setText("");
      checkInscriptionFinalisee.setSelected(false);
    } else {
      if (entreeNomResponsableFormation.getText() == null || entreeEmailResponsableFormation.getText().isEmpty()) {
        this.afficherPopup("Les champs nécessaire ne sont pas tous remplis !", AlertType.ERROR);
      } else {
        this.afficherPopup("Le champ Email est incorecte !", AlertType.ERROR);
      }
    }
  }

  /**
   * Répond à l'événement déclenché par l'action sur le bouton de création de
   * formation.
   * Cette méthode utilise les informations fournies dans les champs de saisie
   * pour créer une nouvelle formation
   * via l'objet GestionFormation (ges). Si la création de la formation est
   * réussie, les champs de saisie et
   * les éléments associés sont réinitialisés. Sinon, des messages d'erreur
   * appropriés sont affichés.
   *
   * @param event L'événement déclenché par l'action sur le bouton de création de
   *              formation.
   */
  @FXML
  void actionBoutonNombreChoixOptions(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeNombreChoixOptions.getText() != null && !entreeNombreChoixOptions.getText().isEmpty()) {
        try {
          ges.definirNombreOptions(Integer.parseInt(entreeNombreChoixOptions.getText()));
          ges.getGestionEtudiant().getListeEtudiants().forEach(etudiant -> ges.setNbOptionEtudiant(etudiant));
        } catch (NumberFormatException e) {
          this.afficherPopup("Le champ est incorecte !", AlertType.ERROR);
        }
      } else {
        this.afficherPopup("Le champ est vide !", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action déclenchée lorsqu'un utilisateur appuie sur le bouton pour
   * définir la taille du groupe de travaux dirigés (TD).
   * Si le nom de la formation est non nul, vérifie et met à jour la taille du
   * groupe de travaux dirigés en fonction de la valeur
   * saisie dans le champ d'entrée entreeTailleGroupeTD. Affiche des messages
   * d'erreur appropriés en cas de champ vide
   * ou de valeur incorrecte.
   *
   * @param event L'événement déclencheur de l'action.
   */
  @FXML
  void actionBoutonSetTailleGroupeTD(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeTailleGroupeTD.getText() != null && !entreeTailleGroupeTD.getText().isEmpty()) {
        try {
          ges.setTailleGroupeDirige(Integer.parseInt(entreeTailleGroupeTD.getText()));
        } catch (NumberFormatException e) {
          this.afficherPopup("Le champ est incorecte !", AlertType.ERROR);
        }
      } else {
        this.afficherPopup("Le champ est vide !", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Réagit au clic sur le bouton de définition de la taille du groupe de travaux
   * dirigés (TD).
   * Vérifie si une formation est sélectionnée, si le champ de saisie de la taille
   * du groupe TD n'est pas vide
   * et s'il contient une valeur numérique valide. En cas de succès, met à jour la
   * taille du groupe dirigé de la formation.
   * En cas d'échec, affiche une boîte de dialogue d'erreur appropriée.
   *
   * @param event L'événement déclenché par le clic sur le bouton.
   */
  @FXML
  void actionBoutonSetTailleGroupeTP(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeTailleGroupeTP.getText() != null && !entreeTailleGroupeTP.getText().isEmpty()) {
        try {
          ges.setTailleGroupePratique(Integer.parseInt(entreeTailleGroupeTP.getText()));
        } catch (NumberFormatException e) {
          this.afficherPopup("Le champ est incorecte !", AlertType.ERROR);
        }
      } else {
        this.afficherPopup("Le champ est vide !", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
    }
  }

  /**
   * Gère l'action associée à l'ouverture d'une nouvelle fenêtre affichant des
   * informations
   * à propos des auteurs du programme.
   *
   * Cette méthode crée une nouvelle fenêtre avec un titre spécifique, affiche les
   * noms des auteurs
   * dans un Label centré en haut, et fournit un bouton permettant de fermer la
   * fenêtre.
   *
   * @param event L'événement déclencheur, généralement lié à une action
   *              utilisateur (ex. clic sur un menu).
   */
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Stage nouvelleFenetre = new Stage();
    nouvelleFenetre.setTitle("Nouvelle Fenêtre");
    Label texteLabel = new Label("Auteurs : ROUSVAL ROMAIN et LE BRAS ERWAN");
    Button fermerButton = new Button("Fermer la Fenêtre");
    fermerButton.setOnAction(Event -> nouvelleFenetre.close());
    VBox layout = new VBox(10);
    layout.getChildren().addAll(texteLabel, fermerButton);
    layout.setAlignment(javafx.geometry.Pos.CENTER);
    Scene scene = new Scene(layout, 300, 150);
    nouvelleFenetre.setScene(scene);
    nouvelleFenetre.show();
  }

  /**
   * Gère l'événement de chargement de données lorsqu'une action est déclenchée
   * par le menu.
   * La méthode charge les données à partir d'un fichier de sauvegarde, puis
   * réinitialise les champs
   * de l'interface graphique en vidant et remplissant les éléments correspondants
   * avec les informations chargées.
   *
   * @param event L'événement déclencheur, généralement associé à un clic sur le
   *              menu de chargement.
   */
  @FXML
  void actionMenuCharger(ActionEvent event) {
    try {
      ges.chargerDonnees("save");
      // vide les champs
      labelNbGroupesTD.setText("...");
      labelNbGroupesTP.setText("...");
      entreeTailleGroupeTD.setText("");
      entreeTailleGroupeTP.setText("");
      entreeNombreChoixOptions.setText("");
      entreeNomResponsableUE.setText("");
      entreeCapaciteAccueil.setText("");
      entreeNomUE.setText("");
      listeUEObligatoires.getItems().clear();
      listeEtudiants.getItems().clear();
      listeUEOptionnelles.getItems().clear();
      entreeNomEtudiant.setText("");
      entreePrenomEtudiant.setText("");
      entreeAdresseEtudiant.setText("");
      entreeAgeEtudiant.setText("");
      entreeGroupeTDEtudiant.setText("");
      entreeGroupeTPEtudiant.setText("");
      checkInscriptionFinalisee.setSelected(false);
      // remplis avec info chargé
      if (ges.nombreGroupesTravauxDiriges() != -1) {
        labelNbGroupesTD.setText(Integer.toString(ges.nombreGroupesTravauxDiriges()));
      }
      if (ges.nombreGroupesTravauxPratiques() != -1) {
        labelNbGroupesTP.setText(Integer.toString(ges.nombreGroupesTravauxPratiques()));
      }
      if (ges.nombreGroupesTravauxDiriges() != -1) {
        entreeTailleGroupeTD.setText(Integer.toString(ges.getTailleGroupeDirige()));
      }
      if (ges.nombreGroupesTravauxDiriges() != -1) {
        entreeTailleGroupeTP.setText(Integer.toString(ges.getTailleGroupePratique()));
      }
      if (ges.nombreGroupesTravauxDiriges() != -1) {
        entreeNombreChoixOptions.setText(Integer.toString(ges.getNBoption()));
      }
      ges.getGestionEtudiant().getListeUE().stream().filter(ue -> ue.getOptionnel())
          .forEach(ue -> listeUEOptionnelles.getItems().add(ue.getNomUE()));
      ges.getGestionEtudiant().getListeUE().stream().filter(ue -> !ue.getOptionnel())
          .forEach(ue -> listeUEObligatoires.getItems().add(ue.getNomUE()));
      ges.getGestionEtudiant().getListeEtudiants().stream()
          .forEach(etu -> listeEtudiants.getItems().add(Integer.toString(etu.getNumero())));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gère l'événement déclenché lorsqu'un utilisateur choisit de quitter
   * l'application depuis le menu.
   * Cette méthode ferme les fenêtres associées aux étudiants et aux formations.
   *
   * @param event L'événement associé au déclenchement de l'action du menu
   *              quitter.
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    fenetreEtudiants.close();
    FenetreFormation.close();
  }

  /**
   * Gère l'événement déclenché lorsqu'une action de sauvegarde est déclenchée
   * depuis le menu.
   * Cette méthode appelle la méthode de sauvegarde des données de l'objet ges
   * (Gestionnaire) avec le
   * nom de fichier spécifié.
   *
   * @param event L'événement de type ActionEvent déclenché par l'action de
   *              sauvegarde du menu.
   * @throws IOException Si une exception d'entrée/sortie survient lors de la
   *                     sauvegarde des données.
   */
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      ges.sauvegarderDonnees("save");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gère l'événement de sélection d'un étudiant dans la liste des étudiants.
   * Met à jour les champs d'entrée avec les informations personnelles de
   * l'étudiant sélectionné,
   * y compris le nom, le prénom, l'adresse, l'âge, et les groupes de travaux
   * dirigés et pratiques.
   * Active la case à cocher d'inscription finalisée si toutes les conditions
   * nécessaires sont remplies.
   *
   * @param event L'événement de la souris déclenché lors de la sélection d'un
   *              étudiant.
   */
  @FXML
  void actionSelectionEtudiant(MouseEvent event) {
    if (ges.getNomFormation() != null) {
      Etudiant etu = ges.getGestionEtudiant().getListeEtudiants().stream()
          .filter(etudiant -> Integer.toString(etudiant.getNumero())
              .equals(listeEtudiants.getSelectionModel().getSelectedItem()))
          .findFirst()
          .orElse(null);
      if (etu != null) {
        InformationPersonnelle info = etu.getInformationPersonnelle();
        entreeNomEtudiant.setText(info.getNom());
        entreePrenomEtudiant.setText(info.getPrenom());
        entreeAdresseEtudiant.setText(info.getAdresse());
        entreeAgeEtudiant.setText(Integer.toString(info.getAge()));
        if (etu.getNumeroTd() != -1) {
          entreeGroupeTDEtudiant.setText(Integer.toString(etu.getNumeroTd()));
        }
        if (etu.getNumeroTp() != -1) {
          entreeGroupeTPEtudiant.setText(Integer.toString(etu.getNumeroTp()));
        }
        if (etu.getNumeroTd() != -1 && etu.getNumeroTp() != -1
            && etu.getListeUEsuivies().stream().filter(UniteEnseignement::getOptionnel).count() == ges.getNBoption()) {
          checkInscriptionFinalisee.setSelected(true);
        }
      }
    }
  }

  /**
   * Gère l'action de sélection d'une Unité d'Enseignement (UE) obligatoire.
   * Cette méthode est déclenchée en réponse à un événement de clic de souris sur
   * un élément de la liste
   * des UE obligatoires. Si une formation est définie, elle récupère les détails
   * de l'UE sélectionnée,
   * tels que le nom, le responsable et la capacité d'accueil, et les affiche dans
   * les champs correspondants.
   *
   * @param event L'événement de clic de souris qui a déclenché cette action.
   */
  @FXML
  void actionSelectionUEObligatoire(MouseEvent event) {
    if (ges.getNomFormation() != null) {
      UniteEnseignement ue2 = ges.getGestionEtudiant().getListeUE().stream()
          .filter(ue -> ue.getNomUE().equals(listeUEObligatoires.getSelectionModel().getSelectedItem()))
          .findFirst()
          .orElse(null);
      if (ue2 != null) {
        entreeNomUE.setText(ue2.getNomUE());
        entreeNomResponsableUE.setText(ue2.getNomEnseignant());
        entreeCapaciteAccueil.setText(Integer.toString(ue2.getNbPlacesMax()));
      }
    }
  }

  /**
   * Gère l'événement de sélection d'une Unité d'Enseignement (UE) optionnelle.
   * Si une formation est sélectionnée, recherche l'UE correspondante dans la
   * liste des UE optionnelles
   * et met à jour les champs d'interface graphique avec les informations de l'UE
   * sélectionnée, tels que
   * le nom de l'UE, le nom du responsable de l'UE et la capacité d'accueil de
   * l'UE.
   *
   * @param event L'événement de la souris déclenchant l'action.
   */
  @FXML
  void actionSelectionUEOptionnelle(MouseEvent event) {
    if (ges.getNomFormation() != null) {
      UniteEnseignement ue2 = ges.getGestionEtudiant().getListeUE().stream()
          .filter(ue -> ue.getNomUE().equals(listeUEOptionnelles.getSelectionModel().getSelectedItem()))
          .findFirst()
          .orElse(null);
      if (ue2 != null) {
        entreeNomUE.setText(ue2.getNomUE());
        entreeNomResponsableUE.setText(ue2.getNomEnseignant());
        entreeCapaciteAccueil.setText(Integer.toString(ue2.getNbPlacesMax()));
      }
    }
  }

  /**
   * Gère l'événement de clic sur le bouton de création d'une nouvelle Unité
   * d'Enseignement (UE).
   * Vérifie si une formation est sélectionnée, puis valide et ajoute une nouvelle
   * UE en fonction des champs saisis dans l'interface graphique.
   *
   * @param event L'événement déclenché par le clic sur le bouton.
   */
  @FXML
  void actionBoutonCreerNouvelleUE(ActionEvent event) {
    if (ges.getNomFormation() != null) {
      if (entreeNomUE.getText() != null && !entreeNomUE.getText().isEmpty() && entreeNomResponsableUE.getText() != null
          && !entreeNomResponsableUE.getText().isEmpty()) {
        UniteEnseignement ue = new UniteEnseignement(entreeNomUE.getText(), entreeNomResponsableUE.getText());
        try {
          if (radioBoutonObligatoire.isSelected()) {
            if (ges.ajouterEnseignementObligatoire(ue)) {
              listeUEObligatoires.getItems().add(ue.getNomUE());
            }
          } else if (radioBoutonOptionnelle.isSelected()) {
            int capaciteAccueil = Integer.parseInt(entreeCapaciteAccueil.getText());
            if (ges.ajouterEnseignementOptionnel(ue, capaciteAccueil)) {
              listeUEOptionnelles.getItems().add(ue.getNomUE());
            }
          }
        } catch (NumberFormatException e) {
          this.afficherPopup("La capacité d'accueil doit être un entier", AlertType.ERROR);
        }
        entreeNomResponsableUE.setText("");
        entreeCapaciteAccueil.setText("");
        entreeNomUE.setText("");
      } else {
        this.afficherPopup("Les champs nécessaire ne sont pas tous remplis !", AlertType.ERROR);
      }
    } else {
      this.afficherPopup("Aucune Formation", AlertType.ERROR);
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

  // this.afficherPopup("Aucune Formation", AlertType.ERROR);
  @FXML
  void initialize() {

  }
}
