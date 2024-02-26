package Interface.secretaire.requetes;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class PanelsFiltre extends VBox{
    String serviceName;
    Label serviceNameLabel;

    VBox content = new VBox(10); // Liste des requêtes
    public PanelsFiltre(String serviceName) {
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

    public void setContent(List<RequetePanel> list) {
        content.getChildren().clear();
        content.getChildren().addAll(list);
    }
}
