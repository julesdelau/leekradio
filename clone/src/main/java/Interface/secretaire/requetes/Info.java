package Interface.secretaire.requetes;

public class Info {
    private String type;
    private String medecin;
    private String date;
    private String patient;
    private String info;

    private boolean urgence;

    public Info(String type, String medecin, String date, String patient, boolean urgence) {
        this.type = type;
        this.medecin = medecin;
        this.date = date;
        this.patient = patient;
        this.urgence = urgence;
    }

    public String getType() {
        return type;
    }

    public String getMedecin() {
        return medecin;
    }

    public String getDate() {
        return date;
    }

    public String getPatient() {
        return patient;
    }

    public boolean isUrgent() {
        return urgence;
    }
}
