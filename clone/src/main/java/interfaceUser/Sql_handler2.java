package interfaceUser;

import Interface.secretaire.liste.InfoListe;
import Interface.secretaire.programme.Recap;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Jules_D
 */
public class Sql_handler2 {

    Connection connection;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsmd;

    public Sql_handler2() {
        // Nom de la classe pour le pilote Oracle
        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";

        // URL du serveur IM2AG de gestion de bases de données
        String dbUrl = "jdbc:oracle:thin:@im2ag-oracle.univ-grenoble-alpes.fr:1521:im2ag";

        // Connection avec la base de données
        connection = null;

        // Etape 1 : connexion au SGBD
        try {
            // Lecture de l'identifiant et du mot de passe
            String username = "gabardo";
            String password = "1d91e99d53";

            // Chargement du driver
            Class.forName(jdbcDriver);
            // Paramétrage de la limite de temps pour la tentative de connexion (en secondes)
            DriverManager.setLoginTimeout(4);
            // Tentative de connexion
            connection = DriverManager.getConnection(dbUrl, username, password);

        } catch (ClassNotFoundException | SQLException ex) {
            // En cas de driver introuvable
            System.out.println(ex.getMessage());
        }
        // Si la connection est établie, tester une requête simple
        // Note : cette requête ne nécessite pas de BD (elle utilise la table "dual")
        if (connection != null) {
            System.out.println("Connexion établie.");

        } else {
            // La connexion n'a pas pu être établie (la référence vaut null)
            System.out.println("Problème de connexion. Vérifiez votre configuration réseau.");
        }
    }

    public int connection(String user, String mdp) {
        int retour = 0;
        String sql = "SELECT specialite FROM identite WHERE id='" + user + "' AND mdp='" + mdp + "'";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            retour = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("suis la");

        }


        return retour;
    }

    public String createdate(int year, int month, int day) {
        if (day > 31 || month > 12) {
            return "01/01/0000";

        }
        String sortie = "" + day + "/" + month + "/" + year;
        return sortie;
    }

    public Vector<String> listpatient(boolean istreated) {
        int traitement;
        Vector<String> sortie = new Vector<String>();
        String sql = "SELECT * FROM DMRTEST ";
        try {
            if (istreated) {
                traitement = 1;
            } else {
                traitement = 0;
            }
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            for (int i = 0; i < rs.getRow(); i++) {
                if (rs.getInt(rs.getMetaData().getColumnLabel(rs.getMetaData().getColumnCount())) == traitement) {
                    for (int j = 1; j < rs.getMetaData().getColumnCount(); j++) {// on cemmence ne 1 et fini en nb colonnes +1

                        sortie.add(rs.getString(rs.getMetaData().getColumnLabel(j)));
                    }
                }
                rs.next();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("suis la");

        }
        // System.out.println(sortie);
        return sortie;
    }

    public boolean addDmr(int iddmr, String nom, String prenom, String date, String adresse, String photo, String compterendu, int dejatraite) {
        try {
            String sql = "INSERT INTO DMRTEST values( '" + iddmr + "' , '" + nom + "' , '" + prenom + "' , '" + date + "' , '" + adresse + "' , '" + photo + "' , '" + compterendu + "' , '" + dejatraite + "')";
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void CreateTable() {
        String sql = "CREATE TABLE DMRTEST "
                + "(IDMR integer NOT NULL, "
                + " NAME varchar(20) NOT NULL, "
                + " FIRSTNAME varchar(20) NOT NULL, "
                + " BIRTHDATE DATE , "
                + " ADRESSE varchar(120) NOT NULL, "
                + " IDPHOTO varchar(50) , "
                + "COMPTE_RENDU varchar(2000) , "
                + " DEJATRAITE integer NOT NULL, "
                + " PRIMARY KEY (IDMR))";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
    }

    public void drop(String table) {
        String sql = "DROP TABLE " + table;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
    }

    public Vector<String> recupererExamen(int iddmr) {
        String sql = "SELECT * FROM EXAMEN WHERE IDMR = " + iddmr;
        Vector<String> sortie = new Vector<String>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            for (int i = 0; i < rs.getRow(); i++) {
                for (int j = 1; j < rs.getMetaData().getColumnCount(); j++) {
                    sortie.add(rs.getString(rs.getMetaData().getColumnLabel(j)));
                }
                rs.next();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("suis la");
        }
        return sortie;
    }

//    public Vector<String[]> getPatientsData() {
//        Vector<String[]> patientsData = new Vector<>();
//
//        String sql = "SELECT nom, prenom, adresse, sexe, TO_CHAR(dateNaissance, 'DD/MM/YYYY') FROM patient";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                String[] patient = new String[7];
//                patient[0] = rs.getString("nom");
//                patient[1] = rs.getString("prenom");
//                patient[2] = rs.getString("sexe");
//                patient[3] = rs.getString("adresse");
//                patient[4] = rs.getString(5); // Date de naissance
//                patientsData.add(patient);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return patientsData;
//    }

    public List<String[]> getPatientsByName(String nomInput) {
        List<String[]> patientsData = new ArrayList<>();

        String sql = "SELECT nom, prenom, sexe, adresse, TO_CHAR(dateNaissance, 'DD/MM/YYYY') FROM patient WHERE nom = '" + nomInput + "'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String[] patientData = new String[5];
                patientData[0] = rs.getString("nom");
                patientData[1] = rs.getString("prenom");
                patientData[2] = rs.getString("sexe");
                patientData[3] = rs.getString("adresse");
                patientData[4] = rs.getString(5); // Date de naissance
                patientsData.add(patientData);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return patientsData;
    }

    public boolean addPatient(String id, String nom, String prenom, String sexe, String adresse, String dateNaissance) {
        String sql = "INSERT INTO patient (id_patient, nom, prenom, adresse, dateNaissance, sexe) VALUES (?, ?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);
            preparedStatement.setString(4, adresse);
            preparedStatement.setString(5, dateNaissance);
            preparedStatement.setString(6, sexe);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public String generateIdPatient() {
        String newId = null;
        String sql = "SELECT MAX(id_patient) FROM patient";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String maxId = rs.getString(1);
                if (maxId != null) {
                    // Si la table n'est pas vide, incrémentez l'identifiant maximum trouvé
                    int maxIdInt = Integer.parseInt(maxId);
                    newId = String.valueOf(maxIdInt + 1);
                } else {
                    // Si la table est vide, commencez avec l'identifiant 1
                    newId = "1";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return newId;
    }

    public String generateIdRdv() {
        String newId = null;
        String sql = "SELECT MAX(id_rdv) FROM rdv";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String maxId = rs.getString(1);
                if (maxId != null) {
                    // Si la table n'est pas vide, incrémentez l'identifiant maximum trouvé
                    int maxIdInt = Integer.parseInt(maxId);
                    newId = String.valueOf(maxIdInt + 1);
                } else {
                    // Si la table est vide, commencez avec l'identifiant 1
                    newId = "1";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return newId;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Methode pour recuperer toutes les rendez-vous de la base de donnees de la table rdv
    public List<InfoListe> getRdv() {
        List<InfoListe> rdvList = new ArrayList<>();
        String sql = "SELECT * FROM rdv";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idRdv = rs.getInt("id_rdv");
                String idPatient = rs.getString("id_patient");
                String dateRdv = rs.getString("date_rdv");
                String typeRdv = rs.getString("type_rdv");
                String idMedecin = rs.getString("id_medecin");
                String infoComplementaire = rs.getString("infos");

                // Obtenir le nom complet du patient
                String patientName = getPatientName(idPatient);
                // Obtenir le nom complet du médecin
                String medecinName = getMedecinName(idMedecin);

                // Créer un objet InfoListe avec les données récupérées
                InfoListe rdv = new InfoListe(idRdv, typeRdv, medecinName, dateRdv, patientName, infoComplementaire);

                // Ajouter l'objet à la liste
                rdvList.add(rdv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rdvList;
    }


    //Methode pour ajouter un rendez-vous dans la base de donnees
    public boolean addRdv(String idPatient, String dateRdv, String typeRdv, String idMedecin, String infos) {
        String sql = "INSERT INTO rdv (id_rdv, id_patient, date_rdv, type_rdv, id_medecin, infos) VALUES (?,?, TO_DATE(?, 'DD/MM/YYYY HH24:MI'), ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, generateIdRdv());
            preparedStatement.setString(2, idPatient);
            preparedStatement.setString(3, dateRdv);
            preparedStatement.setString(4, typeRdv);
            preparedStatement.setString(5, idMedecin);
            preparedStatement.setString(6, infos);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Methode pour chercher l'id d'un patient par son nom, prenom et date de naissance
    public String getPatientId(String nom, String prenom, String dateNaissance) {
        String idPatient = null;
        String sql = "SELECT id_patient FROM patient WHERE nom = ? AND prenom = ? AND dateNaissance = TO_DATE(?, 'DD/MM/YYYY')";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "'"+ nom+ "'");
            preparedStatement.setString(2, "'"+prenom+"'");
            preparedStatement.setString(3, "'"+dateNaissance+"'");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                idPatient = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idPatient;
    }

    //Methode pour chercher l'id d'un medecin par son nom, prenom
    public String getMedecinId(String nom, String prenom) {
        String idMedecin = null;
        String sql = "SELECT id_medecin FROM medecin WHERE nom = ? AND prenom = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                idMedecin = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idMedecin;
    }

    //Methode pour chercher le nom et le prenom d'un patient par id
    public String getPatientName(String idPatient) {
        String patientName = null;
        String sql = "SELECT nom, prenom FROM patient WHERE id_patient = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idPatient);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                patientName = rs.getString(1) + " " + rs.getString(2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return patientName;
    }

    //Methode pour chercher le nom et le prenom d'un medecin par id
    public String getMedecinName(String idMedecin) {
        String medecinName = null;
        String sql = "SELECT nom, prenom FROM personel WHERE id_medecin = '" + idMedecin + "'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                medecinName = rs.getString(1) + " " + rs.getString(2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return medecinName;
    }


}




