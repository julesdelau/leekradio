package Interface.RadioManipulateur;

import Interface.profil.Profil;
import interfaceUser.Sql_handler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Radiologue extends Application {
    Sql_handler moteur;
    AnchorPane header = new AnchorPane();
    private boolean isProfilOpen = false;

    Insets insets = new Insets(10);

    private boolean is_medecin;
    private String name;
    private Vector<String> listepatientatraiter;
    private Vector<String> listepatienttraite;
    int temp =0;

    public Radiologue() {
        moteur = new Sql_handler();
    }
public void start(Stage primaryStage) {
    Label bonjour = new Label("Bienvenue dans votre espace!");
    Label utilisateur = new Label("Utilisateur: ");
    Label aTraiter = new Label("Radiologies à traiter: ");
    Label Traite = new Label("Radiologies traitées: ");
    Label rotation = new Label("Rotation: ");
    Label zoom = new Label("Zoom: ");

    Button rotationDroite = new Button("Rotation à droite");
    Button rotationGauche = new Button("Rotation à gauche");
    Button zoomButton = new Button("Zoom");
    Button dezoomButton = new Button("Dézoom");
    Button search = new Button("Rechercher radiographie");
    Button numeriser = new Button("Numériser radiographie");
    Button envoyer = new Button("Envoyer");



    TextArea compteRendu = new TextArea();

    ScrollPane scrollPaneAfaire = new ScrollPane();
    ScrollPane scrollPaneTraite = new ScrollPane();

    ObservableList<String> items = FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5");

    ListView<String> listRadioAfaire = new ListView<>(items);

    listRadioAfaire.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);

    listRadioAfaire.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                listRadioAfaireValueChanged(newValue);
            });

    scrollPaneAfaire.setContent(listRadioAfaire);

    ObservableList<String> items2 = FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5");

    ListView<String> listRadioATraiter = new ListView<>(items2);

    listRadioATraiter.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);

    listRadioATraiter.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                listRadioATraiterValueChanged(newValue);
            });

    scrollPaneTraite.setContent(listRadioATraiter);



    Button profil = new Button("Profil");

    bonjour.setFont(Font.font("Arial", FontWeight.BOLD, 20));

    bonjour.setTextFill(Color.DARKGRAY);

    ImageView profilIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/profil.png")));
    profilIcon.setFitHeight(50);
    profilIcon.setFitWidth(50);
    profil.setGraphic(profilIcon);

    ImageView logo = new ImageView(new Image("/images/logo.png"));
    logo.setFitHeight(100);
    logo.setFitWidth(200);

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

    BorderStroke borderStroke = new BorderStroke(
            Color.RED,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(0, 0, 2, 0) // Seule la bordure inférieure a une largeur de 2 pixels
    );

    Border border = new Border(borderStroke);

    header.setBorder(border);

    headerButtons.getChildren().addAll(profil);
    header.getChildren().addAll(logo, headerButtons);

    VBox panelVide = new VBox();
    panelVide.setAlignment(Pos.CENTER);
    panelVide.getChildren().add(bonjour);

    VBox panelAfaire = new VBox();
    VBox panelTraite = new VBox();
    panelAfaire.setAlignment(Pos.CENTER);
    panelTraite.setAlignment(Pos.CENTER);

    panelAfaire.getChildren().addAll(aTraiter, scrollPaneAfaire);
    panelTraite.getChildren().addAll(Traite, scrollPaneTraite);

    VBox listes = new VBox(20);
    listes.getChildren().addAll(panelAfaire, panelTraite);
    listes.setPadding(new Insets(10));


    BorderPane root = new BorderPane();

    root.setTop(header);
    root.setCenter(panelVide);
    root.setLeft(listes);

    BorderPane.setMargin(panelVide, new Insets(10));

    Profil profilPanel = new Profil();

    profil.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (!isProfilOpen) {
                // Afficher le panneau Profil
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(root.getCenter(), profilPanel);
                StackPane.setAlignment(profilPanel, Pos.CENTER);
                //root.setPrefSize(profilPanel.getPrefWidth(), profilPanel.getPrefHeight());
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

    rotationGauche.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            temp = temp - 90;
            System.out.println("Rotation à gauche");
        }
    });

    rotationDroite.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            temp = temp + 90;
            System.out.println("Rotation à droite");
        }
    });

    zoomButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Zoom");
        }
    });

    dezoomButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Dézoom");
        }
    });

    search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Rechercher radiographie");
        }
    });

    numeriser.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Numériser radiographie");
        }
    });

    envoyer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Envoyer");

        }
    });


    Scene scene = new Scene(root);

    // Configuration de la fenêtre principale
    primaryStage.setTitle("LeekRadiologie - Page Radiologue");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);
    primaryStage.show();


}
    public static void main(String[] args) {
        launch(args);
    }

    private void listRadioAfaireValueChanged(String newValue) {
        System.out.println("Élément sélectionné : " + newValue);
    }

    private void listRadioATraiterValueChanged(String newValue) {
        System.out.println("Élément sélectionné : " + newValue);
    }
}
