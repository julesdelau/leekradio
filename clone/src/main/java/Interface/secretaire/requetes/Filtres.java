package Interface.secretaire.requetes;

import Interface.secretaire.Requetes;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

import static javafx.stage.StageStyle.UNDECORATED;

public class Filtres extends Dialog<Pair<String, Boolean>>{

    RadioButton urgence = new RadioButton("Urgence (par défaut)");
    RadioButton service = new RadioButton("Par service");
    RadioButton medecin = new RadioButton("Par médecin");
    RadioButton patient = new RadioButton("Par patient");
    RadioButton date = new RadioButton("Par date croissante");
    RadioButton date2 = new RadioButton("Par date décroissante");
    public Filtres(Stage ownerStage) {
        ToggleGroup toggleGroup = new ToggleGroup();

        urgence.setToggleGroup(toggleGroup);
        service.setToggleGroup(toggleGroup);
        medecin.setToggleGroup(toggleGroup);
        patient.setToggleGroup(toggleGroup);
        date.setToggleGroup(toggleGroup);
        date2.setToggleGroup(toggleGroup);

        urgence.setSelected(true);

        VBox content = new VBox();
        content.getChildren().addAll(urgence, service, medecin, patient, date, date2);
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);
        getDialogPane().setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        this.initStyle(UNDECORATED);

        initOwner(ownerStage);


        setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
                if (urgence.isSelected()) {
                    return new Pair<>("Urgence", true);
                }
                if (service.isSelected()) {
                    return new Pair<>("Service", true);
                }
                if (medecin.isSelected()) {
                    return new Pair<>("Medecin", true);
                }
                if (patient.isSelected()) {
                    return new Pair<>("Patient", true);
                }
                if (date.isSelected()) {
                    return new Pair<>("Date", true);
                }
                if (date2.isSelected()) {
                    return new Pair<>("Date2", true);
                }
            }
            return null;
        });
    }

    public int getSelected() {
        int i = 0;
        if(urgence.isSelected()) {
            i=1;
        }
        if(service.isSelected()) {
            i=2;
        }
        if(medecin.isSelected()) {
            i=3;
        }
        if(patient.isSelected()) {
            i=4;
        }
        if(date.isSelected()) {
            i=5;
        }
        if(date2.isSelected()) {
            i=6;
        }
        return i;
    }
}
