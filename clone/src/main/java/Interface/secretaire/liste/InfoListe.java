package Interface.secretaire.liste;

public class InfoListe {
    private String type;
    private String medecin;
    private String date;
    private String patient;



    public InfoListe(String type, String medecin, String date, String patient) {
        this.type = type;
        this.medecin = medecin;
        this.date = date;
        this.patient = patient;
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


}


