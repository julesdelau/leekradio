///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Interface.secretaire;
//
//import java.util.ArrayList;
//import java.util.List;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//
///**
// *
// * @author leoce
// */
//public class Menu extends VBox {
//    private boolean isProfilOpen = false;
//    
//    public Menu() {
//        Button programme = new Button("Programmer Rendez-vous");
//        Button calendrier = new Button("Consulter Calendrier");
//        Button requetes = new Button("Consulter Requetes");
//        Button liste = new Button("Liste des Rendez-vous");
//    
//        programme.setPrefSize(180, 50);
//        calendrier.setPrefSize(180, 50);
//        requetes.setPrefSize(180, 50);
//        liste.setPrefSize(180, 50);
//    
//        VBox menu = new VBox(50);
//        menu.setAlignment(Pos.CENTER);
//        menu.setPrefWidth(250);
//        Insets insets = new Insets(10);
//        menu.setPadding(insets);
//        menu.setStyle("-fx-background-color: #333;");
//        
//        menu.getChildren().addAll(programme, calendrier, requetes, liste);
//        
//                programme.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
//                System.out.println("Bouton Programme cliqué !");
//                isProfilOpen = false;
//                panelVide.getChildren().clear();
//                Programme nouveauPanel = new Programme();
//                nouveauPanel.setAlignment(Pos.CENTER);
//
//                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
//                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());
//
//                root.setCenter(nouveauPanel);
//                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
//                List<Button> boutons = new ArrayList<>();
//
//                boutons.add(programme);
//                boutons.add(calendrier);
//                boutons.add(requetes);
//                boutons.add(liste);
//
//                // Configuration de l'événement de clic pour chaque bouton
//                for (Button bouton : boutons) {
//                    // Réinitialise le style de tous les boutons
//                    for (Button b : boutons) {
//                        b.setStyle("");
//                    }
//                    // Change le style du bouton cliqué
//
//                }
//                programme.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
//
//            }
//        });
//
//        // Action à effectuer lors du clic sur le bouton "Calendrier"
//        calendrier.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
//                System.out.println("Bouton Calendrier cliqué !");
//                isProfilOpen = false;
//                panelVide.getChildren().clear();
//                Calendrier nouveauPanel = new Calendrier();
//                nouveauPanel.setAlignment(Pos.CENTER);
//
//                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
//                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());
//
//                root.setCenter(nouveauPanel);
//                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
//
//                List<Button> boutons = new ArrayList<>();
//
//                boutons.add(programme);
//                boutons.add(calendrier);
//                boutons.add(requetes);
//                boutons.add(liste);
//
//                // Configuration de l'événement de clic pour chaque bouton
//                for (Button bouton : boutons) {
//                    // Réinitialise le style de tous les boutons
//                    for (Button b : boutons) {
//                        b.setStyle("");
//                    }
//                    // Change le style du bouton cliqué
//
//                }
//                calendrier.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
//            }
//        });
//
//        // Action à effectuer lors du clic sur le bouton "Requetes"
//        requetes.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
//                System.out.println("Bouton Requetes cliqué !");
//                isProfilOpen = false;
//                panelVide.getChildren().clear();
//                Requetes nouveauPanel = new Requetes();
//                nouveauPanel.setAlignment(Pos.CENTER);
//
//                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
//                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());
//
//                root.setCenter(nouveauPanel);
//                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
//
//                List<Button> boutons = new ArrayList<>();
//
//                boutons.add(programme);
//                boutons.add(calendrier);
//                boutons.add(requetes);
//                boutons.add(liste);
//
//                // Configuration de l'événement de clic pour chaque bouton
//                for (Button bouton : boutons) {
//                    // Réinitialise le style de tous les boutons
//                    for (Button b : boutons) {
//                        b.setStyle("");
//                    }
//                    // Change le style du bouton cliqué
//
//                }
//                requetes.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
//
//            }
//        });
//
//        // Action à effectuer lors du clic sur le bouton "Liste"
//        liste.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // Mettez ici le code à exécuter lorsque le bouton "Profil" est cliqué
//                System.out.println("Bouton Liste cliqué !");
//                isProfilOpen = false;
//                panelVide.getChildren().clear();
//                Liste nouveauPanel = new Liste();
//                nouveauPanel.setAlignment(Pos.CENTER);
//
//                nouveauPanel.prefWidthProperty().bind(panelVide.widthProperty());
//                nouveauPanel.prefHeightProperty().bind(panelVide.heightProperty());
//
//                root.setCenter(nouveauPanel);
//                BorderPane.setAlignment(nouveauPanel, Pos.CENTER);
//
//                List<Button> boutons = new ArrayList<>();
//
//                boutons.add(programme);
//                boutons.add(calendrier);
//                boutons.add(requetes);
//                boutons.add(liste);
//
//                // Configuration de l'événement de clic pour chaque bouton
//                for (Button bouton : boutons) {
//                    // Réinitialise le style de tous les boutons
//                    for (Button b : boutons) {
//                        b.setStyle("");
//                    }
//                    // Change le style du bouton cliqué
//
//                }
//                liste.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
//
//            }
//        });
//    }
//}
