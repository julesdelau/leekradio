package Interface.secretaire.liste;

import Interface.secretaire.requetes.InfoRequetes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListePanel extends VBox{
    public ListePanel(InfoListe info) {
        Label type = new Label(info.getTypeRdv());
        Label medecin = new Label(info.getMedecinRdv());
        Label date = new Label(info.getDateRdv());
        Label patient = new Label(info.getPatientRdv());


        Button details = new Button("Détails");


        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(details);
        buttons.setPadding(new Insets(10));

        VBox labels = new VBox(10);
        labels.getChildren().addAll( type, medecin, date, patient);
        labels.setPadding(new Insets(10));




        VBox pane = new VBox();

        pane.getChildren().addAll(labels, buttons);

        this.getChildren().add(pane);

        this.setStyle("-fx-border-color: black; -fx-border-width: 2px;");


    }


}
