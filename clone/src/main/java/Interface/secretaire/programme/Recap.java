/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire.programme;

import interfaceUser.Sql_handler2;
import static com.oracle.nio.BufferSecrets.instance;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author leoce
 */
public class Recap extends VBox {

    private static Label nom;
    private static Label prenom;
    private static Label sexe;

    private static Label adresse;
    private static Label dateDeNaissance;

    public Recap() {
        //lecture de la base de données
        Insets insets = new Insets(10);
        // Création des composants
        Label nomLabel = new Label("Nom: ");
        Label prenomLabel = new Label("Prénom: ");
        Label sexeLabel = new Label("Numéro de securité social: ");
        Label adresseLabel = new Label("Adresse : ");
        Label dateDeNaissanceLabel = new Label("Date de naissance :");
        Label motifLabel = new Label("Motif de la visite : ");
        Label dateLabel = new Label("Date du Rendez-vous : ");
        Label heureLabel = new Label("Heure du rendez-vous : ");
        Label infoComplementaireLabel = new Label("Information complémentaire :");

        nom = new Label("PUCCI");
        prenom = new Label("Jérémy");
        sexe = new Label("M");
        adresse = new Label("797 rue de chez lui 4566 ville ");
        dateDeNaissance = new Label("07/11/2001");


        TextField motif = new TextField();
        TextField date = new TextField();
        TextField heure = new TextField();
        TextField infoComplementaire = new TextField();

        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField sexeField = new TextField();
        TextField adresseField = new TextField();
        TextField dateDeNaissanceField = new TextField();

        Button valider = new Button("Valider");
        Button modifier = new Button("Modifier");
        Button enregistrer = new Button("Enregistrer");

        GridPane information = new GridPane();
        information.setPadding(new Insets(20));
        information.setHgap(10);
        information.setVgap(10);

        information.add(nomLabel, 0, 0);
        information.add(nom, 1, 0);
        information.add(prenomLabel, 2, 0);
        information.add(prenom, 3, 0);
        information.add(sexeLabel, 0, 1);
        information.add(sexe, 1, 1);
        information.add(adresseLabel, 0, 2);
        information.add(adresse, 1, 2);
        information.add(dateDeNaissanceLabel, 0, 3);
        information.add(dateDeNaissance, 1, 3);
        information.add(valider, 2, 4, 2, 1);
        information.add(modifier, 0, 4, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(information);
        root.setPadding(insets);
        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);

        this.setPadding(insets);
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                information.add(dateLabel, 0, 9);
                information.add(date, 1, 9);
                information.add(heureLabel, 2, 9);
                information.add(heure, 3, 9);
                information.add(motifLabel, 0, 10);
                information.add(motif, 1, 10);
                information.add(infoComplementaireLabel, 0, 11);
                information.add(infoComplementaire, 1, 11);

            }

        });

        modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                information.getChildren().removeAll(nom, prenom, sexe, adresse, dateDeNaissance, modifier, valider);

                nomField.setText(nom.getText());
                prenomField.setText(prenom.getText());
                sexeField.setText(sexe.getText());
                adresseField.setText(adresse.getText());
                dateDeNaissanceField.setText(dateDeNaissance.getText());

                information.add(nomField, 1, 0);
                information.add(prenomField, 3, 0);
                information.add(sexeField, 1, 1);
                information.add(adresseField, 1, 2);
                information.add(dateDeNaissanceField, 1, 3);
                information.add(enregistrer, 2, 4, 2, 1);

            }

        });

        enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                nom.setText(nomField.getText());
                prenom.setText(prenomField.getText());
                sexe.setText(sexeField.getText());
                adresse.setText(adresseField.getText());
                dateDeNaissance.setText(dateDeNaissanceField.getText());

                information.getChildren().removeAll(nomField, prenomField, sexeField, adresseField, dateDeNaissanceField, enregistrer);

                information.add(nom, 1, 0);
                information.add(prenom, 3, 0);
                information.add(sexe, 1, 1);
                information.add(adresse, 1, 2);
                information.add(dateDeNaissance, 1, 3);
                information.add(valider, 2, 4, 2, 1);
                information.add(modifier, 0, 4, 2, 1);
            }
        });
    }

public Recap(String nom, String prenom, String sexe, String adresse, String dateDeNaissance) {
        //lecture de la base de données
        Insets insets = new Insets(10);
        // Création des composants
        Label nomLabel = new Label("Nom: ");
        Label prenomLabel = new Label("Prénom: ");
        Label sexeLabel = new Label("Sexe: ");
        Label adresseLabel = new Label("Adresse : ");
        Label dateDeNaissanceLabel = new Label("Date de naissance :");
        Label motifLabel = new Label("Motif de la visite : ");
        Label dateLabel = new Label("Date du Rendez-vous : ");
        Label heureLabel = new Label("Heure du rendez-vous : ");
        Label infoComplementaireLabel = new Label("Information complémentaire :");

        this.nom = new Label(nom);
        this.prenom = new Label(prenom);
        this.sexe = new Label(sexe);
        this.adresse = new Label(adresse);
        this.dateDeNaissance = new Label(dateDeNaissance);

        TextField motif = new TextField();
        TextField date = new TextField();
        TextField heure = new TextField();
        TextField infoComplementaire = new TextField();

        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField sexeField = new TextField();
        TextField adresseField = new TextField();
        TextField dateDeNaissanceField = new TextField();

        Button valider = new Button("Valider");
        Button modifier = new Button("Modifier");
        Button enregistrer = new Button("Enregistrer");

        GridPane information = new GridPane();
        information.setPadding(new Insets(20));
        information.setHgap(10);
        information.setVgap(10);

        information.add(nomLabel, 0, 0);
        information.add(this.nom, 1, 0);
        information.add(prenomLabel, 2, 0);
        information.add(this.prenom, 3, 0);
        information.add(sexeLabel, 0, 1);
        information.add(this.sexe, 1, 1);
        information.add(adresseLabel, 0, 2);
        information.add(this.adresse, 1, 2);
        information.add(dateDeNaissanceLabel, 0, 3);
        information.add(this.dateDeNaissance, 1, 3);
        information.add(valider, 2, 4, 2, 1);
        information.add(modifier, 0, 4, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(information);
        root.setPadding(insets);
        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);

        this.setPadding(insets);
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                information.add(dateLabel, 0, 5);
                information.add(date, 1, 5);
                information.add(heureLabel, 2, 5);
                information.add(heure, 3, 5);
                information.add(motifLabel, 0, 6);
                information.add(motif, 1, 6);
                information.add(infoComplementaireLabel, 0, 7);
                information.add(infoComplementaire, 1, 7);

            }

        });

        modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                information.getChildren().removeAll(Recap.nom, Recap.prenom, Recap.sexe, Recap.adresse, Recap.dateDeNaissance, modifier, valider);

                nomField.setText(Recap.nom.getText());
                prenomField.setText(Recap.prenom.getText());
                sexeField.setText(Recap.sexe.getText());
                adresseField.setText(Recap.adresse.getText());
                dateDeNaissanceField.setText(Recap.dateDeNaissance.getText());

                information.add(nomField, 1, 0);
                information.add(prenomField, 3, 0);
                information.add(sexeField, 1, 1);
                information.add(adresseField, 1, 2);
                information.add(dateDeNaissanceField, 1, 3);
                information.add(enregistrer, 2, 4, 2, 1);

            }

        });

        enregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Recap.nom.setText(nomField.getText());
                Recap.prenom.setText(prenomField.getText());
                Recap.sexe.setText(sexeField.getText());
                Recap.adresse.setText(adresseField.getText());
                Recap.dateDeNaissance.setText(dateDeNaissanceField.getText());

                information.getChildren().removeAll(nomField, prenomField, sexeField, adresseField, dateDeNaissanceField, enregistrer);

                information.add(Recap.nom, 1, 0);
                information.add(Recap.prenom, 3, 0);
                information.add(Recap.sexe, 1, 1);
                information.add(Recap.adresse, 1, 2);
                information.add(Recap.dateDeNaissance, 1, 3);
                information.add(valider, 2, 4, 2, 1);
                information.add(modifier, 0, 4, 2, 1);
            }
        });
    }

}
