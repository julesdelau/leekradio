/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire.programme;

import interfaceUser.Sql_handler2;
import Interface.connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.SQLException;




/**
 *
 * @author leoce
 */
public class InscriptionPatient extends VBox{
    private Connection connection;
    public InscriptionPatient(Connection connection){
        this.connection = connection;
        Insets insets = new Insets(10);
        // Création des composants
        Label nomLabel = new Label("Nom: ");
        Label prenomLabel = new Label("Prénom: ");
        Label sexeLabel = new Label("Sexe: ");
        Label adresseLabel = new Label("Adresse : ");
        Label dateDeNaissanceLabel = new Label("Date de naissance :");

        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField sexeField = new TextField();
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
        information.add(sexeLabel, 0, 1);
        information.add(sexeField, 1, 1);
        information.add(adresseLabel, 0, 2);
        information.add(adresseField, 1, 2);
        information.add(dateDeNaissanceLabel, 0, 3);
        information.add(dateDeNaissanceField, 1, 3);
        information.add(enregistrer, 1, 4, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(information);
        root.setPadding(insets);

        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);
        this.setPadding(insets);



        enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String sexe = sexeField.getText();
                String adresse = adresseField.getText();
                String dateDeNaissance = dateDeNaissanceField.getText();

                Sql_handler2 sqlHandler = new Sql_handler2();
                String id = sqlHandler.generateIdPatient();

                sqlHandler.addPatient(id, nom, prenom, sexe, adresse, dateDeNaissance);

                Recap recap;

                root.getChildren().clear();
                recap = new Recap(nom, prenom, sexe, adresse, dateDeNaissance);
                root.setCenter(recap);
            }
        });

    }
}

