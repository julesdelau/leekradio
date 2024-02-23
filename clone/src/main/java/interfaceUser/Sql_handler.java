/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaceUser;

import java.sql.*;


/**
 *
 * @author Jules_D
 */
public class Sql_handler {

    Connection connection;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsmd;

    public Sql_handler() {
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
    public String createdate(int year ,int month , int day){
        if(day>31||month>12){
            return "01/01/0000";
            
        }
        String sortie =""+day+"/"+month+"/"+year;
        return sortie;
    }
    public void listpatient(boolean istreated){
        
        String sql = "SELECT * FROM DMRTEST ";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.getMetaData().toString();
            System.out.println(rs.getMetaData().toString());
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("suis la");

        }
    }

    public boolean addDmr(int iddmr, String nom, String prenom, String date, String adresse, String photo, String compterendu) {
        try {
            String sql = "INSERT INTO DMRTEST values( '" + iddmr + "' , '" + nom + "' , '" + prenom + "' , '" + date + "' , '" + adresse + "' , '" + photo + "' , '" + compterendu + "')";
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
        String sql = "DROP TABLE " + table ;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
    }
}
// table identite(id,mdp,specialite,nom , prenom)id des medic secret et manip

//table finder(iddmr , nom ,prenom, adresse, datenaissnce)
// soi dmr(iddmr , nom , prenom , datenaissance ,adfesse,urlverpacs ,compte randu)
// table pacs -> idphoto , photo format icon
//https://netpbm.sourceforge.net pour pgm
