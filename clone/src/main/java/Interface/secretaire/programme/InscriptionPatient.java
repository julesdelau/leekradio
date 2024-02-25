/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire.programme;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author leoce
 */
public class InscriptionPatient extends VBox{
    public InscriptionPatient(){
        Insets insets = new Insets(10);
        // Création des composants
        Label nomLabel = new Label("Nom: ");
        Label prenomLabel = new Label("Prénom: ");
        Label numSSLabel = new Label("Numéro de securité social: ");
        Label numTelLabel = new Label("Numéro de téléphone: ");
        Label emailLabel = new Label("Adresse mail: ");
        Label adresseLabel = new Label("Adresse : ");
        Label dateDeNaissanceLabel = new Label("Date de naissance :");

        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField numSSField = new TextField();
        TextField numTelField = new TextField();
        TextField emailField = new TextField();
        TextField adresseField = new TextField();
        TextField dateDeNaissanceField = new TextField();

        Button enregistrer = new Button("Enregistrer");

        GridPane information = new GridPane();
        information.setPadding(new Insets(20));
        information.setHgap(10);
        information.setVgap(10);

        information.add(nomLabel, 0, 0);
        information.add(nomField, 1, 0);
        information.add(prenomLabel, 2, 0);
        information.add(prenomField, 3, 0);
        information.add(numSSLabel, 0, 1);
        information.add(numSSField, 1, 1);
        information.add(numTelLabel, 0, 2);
        information.add(numTelField, 1, 2);
        information.add(emailLabel, 0, 3);
        information.add(emailField, 1, 3);
        information.add(adresseLabel, 0, 4);
        information.add(adresseField, 1, 4);
        information.add(dateDeNaissanceLabel, 0, 5);
        information.add(dateDeNaissanceField, 1, 5);
        information.add(enregistrer, 1, 6, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(information);
        root.setPadding(insets);

        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);
        this.setPadding(insets);

        Recap recap = new Recap();

        enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String numSS = numSSField.getText();
                String numTel = numTelField.getText();
                String email = emailField.getText();
                String adresse = adresseField.getText();
                String dateDeNaissance = dateDeNaissanceField.getText();
                root.getChildren().clear();

                Recap recapPanel = new Recap(nom, prenom, numSS, numTel, email, adresse, dateDeNaissance);
                root.setCenter(recapPanel);
            }
        });

    }
}

