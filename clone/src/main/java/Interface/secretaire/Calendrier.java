/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire;

import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class Calendrier extends VBox {

    public Calendrier() {
        Insets insets = new Insets(10);
        // Création des composants
        Label mois = new Label("Mois");
        Label labelCalendrier = new Label("Calendrier");
        Button brouillon = new Button("Enregistrer Brouillon");
        Button confirmer = new Button("Confirmer Modification");
        Button rightButton = new Button("=>");
        Button leftButton = new Button("<=");

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
        rightButton.setPrefSize(50, 50);
        leftButton.setPrefSize(50, 50);

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
        header.getChildren().add(mois);
        header.setPadding(insets);

        VBox calendrier = new VBox();
        calendrier.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        calendrier.setAlignment(Pos.CENTER);
        calendrier.getChildren().add(labelCalendrier);
        calendrier.setPadding(insets);

        // Ajout des boutons a enregistrer
        enregistrerButtons.getChildren().addAll(brouillon, confirmer);
        enregistrer.getChildren().addAll(enregistrerButtons);

        // Création du conteneur fiche BorderPane
        BorderPane fiche = new BorderPane();
        fiche.setTop(header);
        fiche.setCenter(calendrier);
        fiche.setPadding(insets);

// Création du conteneur des boutons
        HBox leftButtonsBox = new HBox();
        leftButtonsBox.setAlignment(Pos.CENTER);
        leftButtonsBox.getChildren().addAll(leftButton);
        leftButtonsBox.setPadding(insets);

        HBox rightButtonsBox = new HBox();
        rightButtonsBox.setAlignment(Pos.CENTER);
        rightButtonsBox.getChildren().addAll(rightButton);
        rightButtonsBox.setPadding(insets);
//
//        // Ajout des boutons au centre du BorderPane
//        center.setBottom(buttonsBox);
        //         Création du conteneur central BorderPane
        BorderPane center = new BorderPane();
        center.setLeft(leftButtonsBox);
        center.setRight(rightButtonsBox);
        center.setCenter(fiche);
        center.setPadding(insets);

        // Création du conteneur principal BorderPane
        BorderPane root = new BorderPane();
        root.setRight(enregistrer);
        root.setCenter(center);
        root.setPadding(insets);

        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);
        this.setPadding(insets);

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

                Label messageLabel = new Label("Modification enregistré!"); // ou recap 
                messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

                root.setCenter(messageLabel);
                BorderPane.setAlignment(messageLabel, Pos.CENTER);

            }
        });

    }
}
