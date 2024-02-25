/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire;

import Interface.secretaire.programme.Recap;
import Interface.secretaire.programme.InscriptionPatient;
import Interface.secretaire.programme.Autres;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author leoce
 */
public class Programme extends VBox {

    public Programme() {
        Insets insets = new Insets(10);
        // Création des composants
        Label labelFiche = new Label("Fiche Rendez-vous");
        Label niupLabel = new Label("Entrer le numéro d'identifiant unique du patient: ");

        TextField niup = new TextField();

        Button search = new Button("Rechercher patient");
        Button other = new Button("Autres moyens de recherches");
        Button pasNuip = new Button("Nouveau Patient");
        Button brouillon = new Button("Enregistrer Brouillon");
        Button confirmer = new Button("Confirmer Rendez-vous");
//        Button rightButton = new Button("=>");
//        Button leftButton = new Button("<=");

        ImageView brouillonIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/brouillon.png")));
        brouillonIcon.setFitHeight(20);
        brouillonIcon.setFitWidth(20);
        brouillon.setGraphic(brouillonIcon);

        ImageView confirmerIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/enregistrer.png")));
        confirmerIcon.setFitHeight(20);
        confirmerIcon.setFitWidth(20);
        confirmer.setGraphic(confirmerIcon);

        // Uniformiser la taille des boutons
        brouillon.setPrefSize(180, 50);
        confirmer.setPrefSize(180, 50);
//        rightButton.setPrefSize(50, 50);
//        leftButton.setPrefSize(50, 50);

        // Création des panneaux
        VBox enregistrerButtons = new VBox(20);

        AnchorPane enregistrer = new AnchorPane();
        AnchorPane.setBottomAnchor(enregistrerButtons, 10.0);
        AnchorPane.setRightAnchor(enregistrerButtons, 10.0);
        double topOffset = (enregistrer.getHeight() + enregistrer.getWidth()) / 2;
        AnchorPane.setBottomAnchor(enregistrerButtons, topOffset);
        enregistrer.setPadding(insets);

        HBox header = new HBox();
        header.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        header.setAlignment(Pos.CENTER);
        header.setPadding(insets);
        header.getChildren().add(labelFiche);

        HBox recherche = new HBox(10);
        recherche.getChildren().addAll(niupLabel, niup, search);
        recherche.setAlignment(Pos.CENTER);

        HBox alternative = new HBox(20);
        alternative.getChildren().addAll(other, pasNuip);
        alternative.setAlignment(Pos.CENTER);

        enregistrerButtons.getChildren().addAll(brouillon, confirmer);
        enregistrer.getChildren().addAll(enregistrerButtons);

        VBox autrePanel = new VBox(10);
        autrePanel.setAlignment(Pos.CENTER);
        VBox center = new VBox(10);
        center.getChildren().addAll(recherche, alternative, autrePanel);
        center.setPadding(insets);

        BorderPane fiche = new BorderPane();
        fiche.setTop(header);
        fiche.setPadding(insets);
        fiche.setCenter(center);

        BorderPane root = new BorderPane();
        root.setRight(enregistrer);
        root.setCenter(fiche);
        root.setPadding(insets);

        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);
        this.setPadding(insets);

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                autrePanel.getChildren().clear();
                Recap nouveauPanel = new Recap();
                autrePanel.getChildren().addAll(nouveauPanel);

            }

        });

        pasNuip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                autrePanel.getChildren().clear();
                InscriptionPatient nouveauPanel = new InscriptionPatient();
                autrePanel.getChildren().addAll(nouveauPanel);

            }

        });

        other.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                autrePanel.getChildren().clear();
                Autres nouveauPanel = new Autres();
                autrePanel.getChildren().addAll(nouveauPanel);

            }

        });

        brouillon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                root.getChildren().clear();

                Label messageLabel = new Label("Brouillon enregistré!");
                messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

                root.setCenter(messageLabel);
                BorderPane.setAlignment(messageLabel, Pos.CENTER);

            }
        });

        confirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                root.getChildren().clear();

                Label messageLabel = new Label("Rendez-vous enregistré!"); // ou recap 
                messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

                root.setCenter(messageLabel);
                BorderPane.setAlignment(messageLabel, Pos.CENTER);

            }
        });

    }
}
