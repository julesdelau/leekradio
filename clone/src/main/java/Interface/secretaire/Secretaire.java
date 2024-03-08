/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire;

import Interface.profil.Profil;
import interfaceUser.Sql_handler;
import Interface.connection.Connection;

import java.util.*;

import javafx.event.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Secretaire extends Application {

    Sql_handler moteur;
    AnchorPane header = new AnchorPane();
    private boolean isProfilOpen = false;

    Profil profilPanel = new Profil();

    Connection connection;

    public Secretaire() {
        moteur = new Sql_handler();
    }

    public Secretaire(Connection connection) {
        this.connection = connection;
        moteur = new Sql_handler();


    }

    @Override
    public void start(Stage primaryStage) {
        Secretaire secretaire = new Secretaire(connection);
        // Création des composants
        Label bonjour = new Label("Bienvenue dans votre espace!");
        Button programme = new Button("Programmer Rendez-vous");
        Button calendar = new Button("Consulter Calendrier");
        Button request = new Button("Consulter Requetes");
        Button list = new Button("Liste des Rendez-vous");
        Button profil = new Button("Profil");
        Button menuButton = new Button("Menu");

        bonjour.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        bonjour.setTextFill(Color.DARKGRAY);
        
        
        ImageView profilIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/profil.png")));
        profilIcon.setFitHeight(50);
        profilIcon.setFitWidth(50);
        profil.setGraphic(profilIcon);

        menuButton.prefWidthProperty().bind(profil.widthProperty());
        menuButton.prefHeightProperty().bind(profil.heightProperty());


        ImageView logo = new ImageView(new Image("/images/logo.png"));
        logo.setFitHeight(100);
        logo.setFitWidth(200);


        // Uniformiser la taille des boutons
        programme.setPrefSize(180, 50);
        calendar.setPrefSize(180, 50);
        request.setPrefSize(180, 50);
        list.setPrefSize(180, 50);

        // Création des panneaux
        VBox menu = new VBox(50);
        menu.setAlignment(Pos.CENTER);
        menu.setPrefWidth(250);
        Insets insets = new Insets(10);
        menu.setPadding(insets);
        menu.setStyle("-fx-background-color: #333;");

        HBox headerButtons = new HBox(50);

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
        menu.getChildren().addAll(programme, calendar, request, list);

        // Création du conteneur principal BorderPane
        BorderPane root = new BorderPane();


        BorderPane.setMargin(panelVide, new Insets(10));



// 
        root.setTop(header);
        root.setLeft(menu);
        root.setCenter(panelVide);


        // Action à effectuer lors du clic sur le bouton "Programme"
        programme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bouton Programme cliqué !");
                isProfilOpen = false;
                panelVide.getChildren().clear();
                Programme nouveauPanel = new Programme(connection);
                nouveauPanel.setAlignment(Pos.CENTER);

                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());

                root.setCenter(nouveauPanel);
                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
                List<Button> boutons = new ArrayList<>();

                boutons.add(programme);
                boutons.add(calendar);
                boutons.add(request);
                boutons.add(list);

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
        calendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
                boutons.add(calendar);
                boutons.add(request);
                boutons.add(list);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                calendar.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
            }
        });

        // Action à effectuer lors du clic sur le bouton "Requetes"
        request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
                boutons.add(calendar);
                boutons.add(request);
                boutons.add(list);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                request.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");

            }
        });

        // Action à effectuer lors du clic sur le bouton "Liste"
        list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
                boutons.add(calendar);
                boutons.add(request);
                boutons.add(list);

                // Configuration de l'événement de clic pour chaque bouton
                for (Button bouton : boutons) {
                    // Réinitialise le style de tous les boutons
                    for (Button b : boutons) {
                        b.setStyle("");
                    }
                    // Change le style du bouton cliqué

                }
                list.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");

            }
        });

        profil.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!isProfilOpen) {
                    // Afficher le panneau Profil
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().addAll(root.getCenter(), profilPanel);
                    StackPane.setAlignment(profilPanel, Pos.CENTER);
                    root.setCenter(stackPane);
                    profilPanel.toFront();
                    profilPanel.show();


                } else {
                    // Cacher le panneau Profil
                    profilPanel.hide();
                }
                isProfilOpen = !isProfilOpen;


            }
        });

        // Création de la scène 
        Scene scene = new Scene(root);

        // Configuration de la fenêtre principale
        primaryStage.setTitle("LeekRadiologie - Page secrétaire");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }

    public void mettreAJourUtilisateur(String utilisateur){
        profilPanel.setUtilisateur(utilisateur);

    }

}
