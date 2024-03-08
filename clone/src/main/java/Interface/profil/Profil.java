/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.profil;

import static javafx.application.Application.launch;

import interfaceUser.Sql_handler2;
import javafx.animation.TranslateTransition;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.util.Duration;


public class Profil extends VBox {
    private Label identifiant;
    private Label nom;
    private Label prenom;
    private Sql_handler2 sqlHandler = new Sql_handler2();
    public Profil() {
        identifiant = new Label("Identifiant: ");
        nom = new Label();
        prenom = new Label();

        Label fonction = new Label("Espace secrétaire");
        Label nomText = new Label("Nom:");
        Label prenomText = new Label("Prénom:");




        Button deconnexion = new Button("Déconnexion");

        fonction.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        fonction.setTextFill(Color.DARKGRAY);

        ImageView deconnexionIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/deconnecter.png")));
        deconnexionIcon.setFitHeight(20);
        deconnexionIcon.setFitWidth(20);
        deconnexion.setGraphic(deconnexionIcon);

//        BorderPane root = new BorderPane();
//
////        logoPanel.toFront();
////        root.setTop(logoPanel);
//        root.setTop(info);
//        root.setCenter(deconnexion);
//                Action à effectuer lors du clic sur le bouton "Déconnexion"
        deconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Mettez ici le code à exécuter lorsque le bouton "Déconnexion" est cliqué
                System.out.println("Bouton Déconnexion cliqué !");
                //Vas sur la page de connexion
            }
        });

        GridPane information = new GridPane();
        information.setPadding(new Insets(10));
        information.setHgap(10);
        information.setVgap(10);

        information.add(nomText, 2, 0);
        information.add(nom, 3, 0);
        information.add(prenomText, 2, 1);
        information.add(prenom, 3, 1);
        information.add(deconnexion, 2, 2, 2, 1);

        VBox content = new VBox(10); // Créez un conteneur pour le contenu du profil
        content.setStyle("-fx-background-color: gray;"); // Appliquez le style au conteneur
//        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(fonction, information); // Ajoutez les éléments au conteneur

//        content.setPrefSize(700, 600);
        // Ajoutez le conteneur au panneau Profil
        this.getChildren().add(content);

//        setStyle("-fx-background-color: red;"); // Couleur de fond du panneau
        setTranslateX(3000);

    }

    public void show() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), this);
        transition.setToX(1300); // Afficher le panneau à gauche 1100
//        transition.setToY(125);
        transition.play();
        System.out.println("show");
    }



    // Méthode pour cacher le panneau Profil en le faisant glisser
    public void hide() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), this);
        transition.setToX(3000); // Cacher le panneau à droite
        transition.play();
        System.out.println("hide");
    }

    public void setUtilisateur(String idMedecin) {
        // Récupérer le nom et le prénom du médecin à partir de l'ID
        String medecinName = sqlHandler.getMedecinName(idMedecin);

        // Afficher le nom et le prénom du médecin
        if (medecinName != null && !medecinName.isEmpty()) {
            String[] parts = medecinName.split(" ");
            if (parts.length >= 2) {
                nom.setText(parts[0]);
                prenom.setText(parts[1]);
            }
        }
    }
}
