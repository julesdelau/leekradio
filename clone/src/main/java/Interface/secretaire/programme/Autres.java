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
public class Autres extends VBox {

    public Autres() {
        Label infoLabel = new Label("Entrer les informations du patient: ");
        Label nomLabel = new Label("Nom: ");
        Label prenomLabel = new Label("Prénom: ");
        Label dateDeNaissanceLabel = new Label("Date de naissance: ");
        Label ouLabel = new Label("OU");
        Label numSSLabel = new Label("Numéro de sécurité social: ");

        TextField nom = new TextField();
        TextField prenom = new TextField();
        TextField dateDeNaissance = new TextField();
        TextField numSS = new TextField();

        Button search1 = new Button("Rechercher patient");
        Button search2 = new Button("Rechercher patient");

        GridPane information = new GridPane();
        information.setPadding(new Insets(20));
        information.setHgap(10);
        information.setVgap(10);

        information.add(infoLabel, 0, 0);
        information.add(nomLabel, 0, 1);
        information.add(nom, 1, 1);
        information.add(prenomLabel, 2, 1);
        information.add(prenom, 3, 1);
        information.add(dateDeNaissanceLabel, 0, 2);
        information.add(dateDeNaissance, 1, 2);
        information.add(search1, 2, 2, 2, 1);
        information.add(ouLabel, 0, 3);
        information.add(numSSLabel, 0, 4);
        information.add(numSS, 1, 4);
        information.add(search2, 2, 4, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(information);

        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);

        search1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                Recap nouveauPanel = new Recap();
                root.setCenter(nouveauPanel);

            }

        });

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                Recap nouveauPanel = new Recap();
                root.setCenter(nouveauPanel);

            }

        });
    }

}
