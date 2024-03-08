package Interface.secretaire.liste;

//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import interfaceUser.Sql_handler2;


import java.sql.Date;
import java.sql.Time;

public class InfoListe {

    private int idRdv;
    private String typeRdv;
    private String medecinRdv;
    private String dateRdv;
    private String patientRdv;

    private boolean isConfirmed;
    private String infoRdv;

    public InfoListe(int idRdv, String typeRdv, String medecinRdv, String dateRdv, String patientRdv, String infoRdv) {
        this.idRdv = idRdv;
        this.typeRdv = typeRdv;
        this.medecinRdv = medecinRdv;
        this.dateRdv = dateRdv;
        this.patientRdv = patientRdv;
        this.isConfirmed = isConfirmed;
        this.infoRdv = infoRdv;
    }

    // Getters and setters
    public int getIdRdv() {
        return idRdv;
    }

    public String getTypeRdv() {
        return typeRdv;
    }

    public void setTypeRdv(String typeRdv) {
        this.typeRdv = typeRdv;
    }

    public String getMedecinRdv() {
        return medecinRdv;
    }

    public void setMedecinRdv(String medecinRdv) {
        this.medecinRdv = medecinRdv;
    }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getPatientRdv() {
        return patientRdv;
    }

    public void setPatientRdv(String patientRdv) {
        this.patientRdv = patientRdv;
    }


    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getInfoRdv() {
        return infoRdv;
    }

    public void setInfoRdv(String infoRdv) {
        this.infoRdv = infoRdv;
    }
}



