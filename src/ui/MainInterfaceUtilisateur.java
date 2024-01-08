package ui;

import java.io.IOException;
import java.net.URL;

import formation.GestionFormation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.EtudiantsControleur;

/**
 * Classe qui lance l'interface graphique.
 *
 * @author Eric Cariou
 */
// MainInterfaceUtilisateur.java
public final class MainInterfaceUtilisateur extends Application {
    private SharedModel sharedModel = new SharedModel();

    @Override
    public void start(Stage primaryStage) {
        // ... Instanciation d'autres classes si nécessaire
        GestionFormation ges=new GestionFormation();
        // Définir la variable dans le modèle partagé
        sharedModel.setSharedVariable(ges);

        try {
            // Lancement de la fenêtre de formation
            FXMLLoader formationLoader = new FXMLLoader(getClass().getResource("formation.fxml"));
            VBox formationRoot = (VBox) formationLoader.load();

            // Accédez au contrôleur de la fenêtre formation pour définir le modèle partagé
            FormationControleur formationControleur = formationLoader.getController();
            formationControleur.setSharedModel(sharedModel);

            Scene formationScene = new Scene(formationRoot, 930, 590);

            primaryStage.setScene(formationScene);
            primaryStage.setResizable(true);
            primaryStage.setTitle("Gestion de formation");
            primaryStage.show();

            // Lancement de la fenêtre d'étudiants
            FXMLLoader etudiantsLoader = new FXMLLoader(getClass().getResource("etudiants.fxml"));
            VBox etudiantsRoot = (VBox) etudiantsLoader.load();

            // Accédez au contrôleur de la fenêtre étudiants pour définir le modèle partagé
            EtudiantsControleur etudiantsControleur = etudiantsLoader.getController();
            etudiantsControleur.setSharedModel(sharedModel);

            Scene etudiantsScene = new Scene(etudiantsRoot, 920, 500);

            Stage etudiantsStage = new Stage();
            etudiantsStage.setResizable(true);
            etudiantsStage.setTitle("Gestion des étudiants");
            etudiantsStage.setScene(etudiantsScene);
            etudiantsStage.show();

        } catch (IOException e) {
            System.err.println("Erreur lors du lancement des fenêtres : " + e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
