package Interface.secretaire.liste;

import Interface.secretaire.requetes.InfoRequetes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListePanel extends VBox{
    public ListePanel(InfoListe info) {
        Label type = new Label(info.getType());
        Label medecin = new Label(info.getMedecin());
        Label date = new Label(info.getDate());
        Label patient = new Label(info.getPatient());


        Button details = new Button("Détails");
        Button refuse = new Button("Refuser");
        Button accept = new Button("Accepter");

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(details, refuse, accept);
        buttons.setPadding(new Insets(10));

        VBox labels = new VBox(10);
        labels.getChildren().addAll( type, medecin, date, patient);
        labels.setPadding(new Insets(10));




        VBox pane = new VBox();
//        AnchorPane.setTopAnchor(labels, 10.0);
//        AnchorPane.setLeftAnchor(labels, 10.0);
//        AnchorPane.setBottomAnchor(buttons, 10.0);
//        AnchorPane.setRightAnchor(buttons, 10.0);
//        double topOffset = (pane.getHeight() + pane.getWidth()) / 2;
//        AnchorPane.setTopAnchor(labels, topOffset);
//        AnchorPane.setBottomAnchor(buttons, topOffset);
        pane.getChildren().addAll(labels, buttons);

        this.getChildren().add(pane);

        this.setStyle("-fx-border-color: black; -fx-border-width: 2px;");


    }


}
