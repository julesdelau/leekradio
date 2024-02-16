/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire;

import Interface.profil.Profil;

import java.util.*;
import javafx.animation.TranslateTransition;
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.*;

public class SecretaireFX extends Application {

    AnchorPane header = new AnchorPane();
    private boolean isProfilOpen = false;

    @Override
    public void start(Stage primaryStage) {
        // Création des composants
        Label bonjour = new Label("Bienvenue dans votre espace!");
        Button programme = new Button("Programmer Rendez-vous");
        Button calendrier = new Button("Consulter Calendrier");
        Button requetes = new Button("Consulter Requetes");
        Button liste = new Button("Liste des Rendez-vous");
        Button profil = new Button("Profil");
//        Button deconnexion = new Button("Déconnexion");

        bonjour.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        bonjour.setTextFill(Color.DARKGRAY);

        ImageView profilIcon = new ImageView(new Image(getClass().getResourceAsStream("/interface/secretaire/images/profil.png")));
        profilIcon.setFitHeight(50);
        profilIcon.setFitWidth(50);
        profil.setGraphic(profilIcon);

//        ImageView deconnexionIcon = new ImageView(new Image(getClass().getResourceAsStream("/interface/secretaire/images/deconnecter.png")));
//        deconnexionIcon.setFitHeight(20);
//        deconnexionIcon.setFitWidth(20);
//        deconnexion.setGraphic(deconnexionIcon);
        ImageView logo = new ImageView(new Image("/interface/secretaire/images/logo.png"));
        logo.setFitHeight(100);
        logo.setFitWidth(200);

//        AnchorPane logoPanel=new AnchorPane();
//        logoPanel.setStyle("-fx-background-color: white;");
//          AnchorPane.setTopAnchor(logo, 10.0);
//        AnchorPane.setLeftAnchor(logo, 10.0);
//        
//            logoPanel.setTopAnchor(logo, 0.0);
//            logoPanel.setLeftAnchor(logo, 0.0);
//            double topOffset = (logo.getHeight() + logo.getWidth()) / 2;
//            AnchorPane.setTopAnchor(logo, topOffset);
//            logoPanel.setTopAnchor(logo, topOffset);
//        logoPanel.getChildren().addAll(logo);
        // Uniformiser la taille des boutons
        programme.setPrefSize(180, 50);
        calendrier.setPrefSize(180, 50);
        requetes.setPrefSize(180, 50);
        liste.setPrefSize(180, 50);

        // Création des panneaux
        VBox menu = new VBox(50);
        menu.setAlignment(Pos.CENTER);
        menu.setPrefWidth(250);
        Insets insets = new Insets(10);
        menu.setPadding(insets);
        menu.setStyle("-fx-background-color: #333;");

        HBox headerButtons = new HBox();

        header.setStyle("-fx-background-color: #444;");
        AnchorPane.setTopAnchor(logo, 10.0);
        AnchorPane.setLeftAnchor(logo, 10.0);
        AnchorPane.setBottomAnchor(headerButtons, 10.0);
        AnchorPane.setRightAnchor(headerButtons, 10.0);
        double topOffset = (header.getHeight() + header.getWidth()) / 2;
        AnchorPane.setTopAnchor(logo, topOffset);
        AnchorPane.setBottomAnchor(headerButtons, topOffset);
        header.setPadding(insets);

        // Création d'une bordure rouge uniquement en bas
        BorderStroke borderStroke = new BorderStroke(
                Color.RED,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(0, 0, 2, 0) // Seule la bordure inférieure a une largeur de 2 pixels
        );

        Border border = new Border(borderStroke);

        // Application de la bordure au panneau
        header.setBorder(border);

        headerButtons.getChildren().addAll(profil);
        header.getChildren().addAll(logo, headerButtons);

        VBox panelVide = new VBox();
        panelVide.setAlignment(Pos.CENTER);
        panelVide.getChildren().add(bonjour);

        // Ajout des boutons au menu
        menu.getChildren().addAll(programme, calendrier, requetes, liste);

        // Création du conteneur principal BorderPane
        BorderPane root = new BorderPane();

//        logoPanel.toFront();
//        root.setTop(logoPanel);
        root.setTop(header);
        root.setLeft(menu);
        root.setCenter(panelVide);

//        root.getChildren().add(logoPanel);
        // Ajout des marges entre les éléments du BorderPane
//        BorderPane.setMargin(menu, new Insets(10));
        BorderPane.setMargin(panelVide, new Insets(10));

        Profil profilPanel = new Profil();

//                VBox content = new VBox();
//        content.getChildren().addAll(root, profilPanel);
//
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().addAll(profilPanel,root);
// 
        root.setTop(header);
        root.setLeft(menu);
        root.setCenter(panelVide);

        // Action à effectuer lorsque le bouton profil est cliqué
        profil.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!isProfilOpen) {
                    // Afficher le panneau Profil
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().addAll(root.getCenter(), profilPanel);
                    StackPane.setAlignment(profilPanel, Pos.CENTER);
                    root.setPrefSize(profilPanel.getPrefWidth(), profilPanel.getPrefHeight());
                    root.setCenter(stackPane);
//                    root.setCenter(profilPanel);
                    profilPanel.toFront();
                    profilPanel.show();

                } else {
                    // Cacher le panneau Profil
                    profilPanel.hide();
                }
                isProfilOpen = !isProfilOpen;

            }
        });

//        // Action à effectuer lorsque la souris passe sur le bouton
//        profil.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                // Afficher le panneau Profil
//                System.out.println("entrer");
//                profilPanel.show();
//            }
//        });
//
//        // Action à effectuer lorsque la souris quitte le bouton
//        profil.setOnMouseExited(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                // Cacher le panneau Profil
//                System.out.println("sortie");
//                profilPanel.hide();
//            }
//        });
        // Action à effectuer lors du clic sur le bouton "Déconnexion"
//        deconnexion.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Mettez ici le code à exécuter lorsque le bouton "Déconnexion" est cliqué
//                System.out.println("Bouton Déconnexion cliqué !");
//            }
//        });
        // Action à effectuer lors du clic sur le bouton "Programme"
        programme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
                System.out.println("Bouton Programme cliqué !");
                isProfilOpen = false;
                panelVide.getChildren().clear();
                Programme nouveauPanel = new Programme();
                nouveauPanel.setAlignment(Pos.CENTER);

                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());

                root.setCenter(nouveauPanel);
                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
                List<Button> boutons = new ArrayList<>();

                boutons.add(programme);
                boutons.add(calendrier);
                boutons.add(requetes);
                boutons.add(liste);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                programme.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");

            }
        });

        // Action à effectuer lors du clic sur le bouton "Calendrier"
        calendrier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
                System.out.println("Bouton Calendrier cliqué !");
                isProfilOpen = false;
                panelVide.getChildren().clear();
                Calendrier nouveauPanel = new Calendrier();
                nouveauPanel.setAlignment(Pos.CENTER);

                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());

                root.setCenter(nouveauPanel);
                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);

                List<Button> boutons = new ArrayList<>();

                boutons.add(programme);
                boutons.add(calendrier);
                boutons.add(requetes);
                boutons.add(liste);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                calendrier.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
            }
        });

        // Action à effectuer lors du clic sur le bouton "Requetes"
        requetes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
                System.out.println("Bouton Requetes cliqué !");
                isProfilOpen = false;
                panelVide.getChildren().clear();
                Requetes nouveauPanel = new Requetes();
                nouveauPanel.setAlignment(Pos.CENTER);

                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());

                root.setCenter(nouveauPanel);
                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);

                List<Button> boutons = new ArrayList<>();

                boutons.add(programme);
                boutons.add(calendrier);
                boutons.add(requetes);
                boutons.add(liste);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                requetes.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");

            }
        });

        // Action à effectuer lors du clic sur le bouton "Liste"
        liste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
                System.out.println("Bouton Liste cliqué !");
                isProfilOpen = false;
                panelVide.getChildren().clear();
                Liste nouveauPanel = new Liste();
                nouveauPanel.setAlignment(Pos.CENTER);

                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());

                root.setCenter(nouveauPanel);
                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);

                List<Button> boutons = new ArrayList<>();

                boutons.add(programme);
                boutons.add(calendrier);
                boutons.add(requetes);
                boutons.add(liste);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                liste.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");

            }
        });

        // Création de la scène 
        Scene scene = new Scene(root);

        // Configuration de la fenêtre principale
        primaryStage.setTitle("LeekRadiologie - Page secrétaire");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("oui");
            } else {
                System.out.println("non");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
