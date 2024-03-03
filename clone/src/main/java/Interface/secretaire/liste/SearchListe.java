package Interface.secretaire.liste;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import static javafx.stage.StageStyle.UNDECORATED;

public class SearchListe extends Dialog<Pair<String, Boolean>> {
    Label nomPatientSearch = new Label("Entrer le nom du patient: ");
    TextField nomPatientField = new TextField();
    Label prenomPatientSearch = new Label("Prénom: ");
    TextField prenomPatientField = new TextField();


    Label ou = new Label("OU");

    Label nomMedecinSearch = new Label("Entrer le nom du médecin: ");
    TextField nomMedecinField = new TextField();
    Label prenomMedecinSearch = new Label("Prénom: ");
    TextField prenomMedecinField = new TextField();

    Label ou1 = new Label("OU");
    Label dateSearch = new Label("Entrer la date: ");
    TextField dateField = new TextField();

    public SearchListe(Stage ownerStage) {


        HBox patient = new HBox(10);
        patient.getChildren().addAll(nomPatientSearch, nomPatientField, prenomPatientSearch, prenomPatientField);
        patient.setAlignment(Pos.CENTER);

        HBox medecin = new HBox(10);
        medecin.getChildren().addAll(nomMedecinSearch, nomMedecinField, prenomMedecinSearch, prenomMedecinField);
        medecin.setAlignment(Pos.CENTER);

        HBox date = new HBox(10);
        date.getChildren().addAll(dateSearch, dateField);
        date.setAlignment(Pos.CENTER);

        VBox content = new VBox(10);
        content.getChildren().addAll(patient, ou, medecin, ou1, date);

        getDialogPane().setContent(content);
        ButtonType buttonTypeSearch = new ButtonType("Rechercher", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(buttonTypeSearch, buttonTypeCancel);
        getDialogPane().setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        this.initStyle(UNDECORATED);

        initOwner(ownerStage);

        setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeSearch) {
                if (!nomPatientField.getText().isEmpty() && !prenomPatientField.getText().isEmpty()) {
                    return new Pair<>("Patient", true);
                }
                if (!nomMedecinField.getText().isEmpty() && !prenomMedecinField.getText().isEmpty()) {
                    return new Pair<>("Medecin", true);
                }
                if (!nomPatientField.getText().isEmpty()) {
                    return new Pair<>("nomPatient", true);
                }
                if (!nomMedecinField.getText().isEmpty()) {
                    return new Pair<>("nomMedecin", true);
                }
                if(!prenomPatientField.getText().isEmpty()) {
                    return new Pair<>("prenomPatient", true);
                }
                if(!prenomMedecinField.getText().isEmpty()) {
                    return new Pair<>("prenomMedecin", true);
                }
                if(!nomPatientField.getText().isEmpty() && !prenomPatientField.getText().isEmpty() && !nomMedecinField.getText().isEmpty() && !prenomMedecinField.getText().isEmpty()) {
                    return new Pair<>("Patient et Medecin", true);
                }
                if(!nomPatientField.getText().isEmpty() && !prenomPatientField.getText().isEmpty() && !nomMedecinField.getText().isEmpty()) {
                    return new Pair<>("Patient et nomMedecin", true);
                }
                if(!nomPatientField.getText().isEmpty() && !prenomPatientField.getText().isEmpty() && !prenomMedecinField.getText().isEmpty()) {
                    return new Pair<>("Patient et prenomMedecin", true);
                }
                if(!nomMedecinField.getText().isEmpty() && !prenomMedecinField.getText().isEmpty() && !nomPatientField.getText().isEmpty()) {
                    return new Pair<>("Medecin et nomPatient", true);
                }
                if(!nomMedecinField.getText().isEmpty() && !prenomMedecinField.getText().isEmpty() && !prenomPatientField.getText().isEmpty()) {
                    return new Pair<>("Medecin et prenomPatient", true);
                }
                if(!dateField.getText().isEmpty()) {
                    return new Pair<>("Date", true);
                }
            }
            return new Pair<>("", false);
        });
    }

    public String getNomPatient() {
        return nomPatientField.getText();
    }

    public String getPrenomPatient() {
        return prenomPatientField.getText();
    }

    public String getNomMedecin() {
        return nomMedecinField.getText();
    }

    public String getPrenomMedecin() {
        return prenomMedecinField.getText();
    }
}

