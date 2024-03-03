package Interface.secretaire.liste;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import static javafx.stage.StageStyle.UNDECORATED;

public class FiltresListe extends Dialog<Pair<String, Boolean>> {


    RadioButton service = new RadioButton("Par service");
    RadioButton medecin = new RadioButton("Par médecin");
    RadioButton patient = new RadioButton("Par patient");
    RadioButton date = new RadioButton("Par date croissante");
    RadioButton date2 = new RadioButton("Par date décroissante");
    RadioButton dateDuJour = new RadioButton("Seulement la date du jour");
    public FiltresListe(Stage ownerStage) {
        ToggleGroup toggleGroup = new ToggleGroup();


        service.setToggleGroup(toggleGroup);
        medecin.setToggleGroup(toggleGroup);
        patient.setToggleGroup(toggleGroup);
        date.setToggleGroup(toggleGroup);
        date2.setToggleGroup(toggleGroup);
        dateDuJour.setToggleGroup(toggleGroup);


        VBox content = new VBox();
        content.getChildren().addAll(service, medecin, patient, date, date2, dateDuJour);
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);
        getDialogPane().setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        this.initStyle(UNDECORATED);

        initOwner(ownerStage);


        setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
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
                if (dateDuJour.isSelected()) {
                    return new Pair<>("DateDuJour", true);
                }
            }
            return null;
        });
    }

}

