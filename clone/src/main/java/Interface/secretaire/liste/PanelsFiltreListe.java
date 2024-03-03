package Interface.secretaire.liste;

import Interface.secretaire.requetes.RequetePanel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelsFiltreListe  extends VBox{

    String serviceName;
    Label serviceNameLabel;

    VBox content = new VBox(10); // Liste des requêtes
    public PanelsFiltreListe(String serviceName) {
        serviceNameLabel  = new Label(serviceName);
        Label trait= new Label("------------------------------------------------------------------");
        this.getChildren().addAll(serviceNameLabel, trait, content);
    }

    public void setX(String x) {
        this.serviceName = x;
    }

    public String getX() {
        return serviceName;
    }

    public void setContent(List<ListePanel> list) {
        content.getChildren().clear();
        content.getChildren().addAll(list);
    }
}

