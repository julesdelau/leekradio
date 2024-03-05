/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaceUser;

import java.sql.*;
import java.util.Vector;

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
        
    }

    public void SeConnecter() {
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
    
    public void quit(){
        String sql="QUIT";
         try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        
    }

    public int connection(String user, String mdp) {
        SeConnecter();
        int retour = 0;
        String sql = "SELECT specialite FROM identite WHERE id='" + user + "' AND mdp='" + mdp + "'";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            retour = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          

        }
quit();
        return retour;
        
    }

    public String createdate(int year, int month, int day) {
        if (day > 31 || month > 12) {
            return "01/01/0000";

        }
        String sortie = "" + day + "/" + month + "/" + year;
        return sortie;
    }

    public int initialiser() {
        SeConnecter();
        String sql = "SELECT * FROM DMRTEST ";
        try {

            st = connection.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            quit();
            return rs.getMetaData().getColumnCount();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;

        }

    }

    public Vector<String> listpatient(boolean istreated) {
        SeConnecter();
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

        }
        // System.out.println(sortie);
        quit();
        return sortie;
    }

    public boolean addDmr(int iddmr, String nom, String prenom, String date, String adresse, String photo, String compterendu, int dejatraite) {
        SeConnecter();
        try {
            String sql = "INSERT INTO DMRTEST values( '" + iddmr + "' , '" + nom + "' , '" + prenom + "' , '" + date + "' , '" + adresse + "' , '" + photo + "' , '" + compterendu + "' , '" + dejatraite + "')";
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            quit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            quit();
            return false;
        }
    }

    public boolean SubmitCR(String CR, String IDMR) {
        SeConnecter();
        String sql = "UPDATE DMRTEST "
                + "SET COMPTE_RENDU ='" + CR + "' "
                + "WHERE IDMR ='" + IDMR + "' ";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");
            quit();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            quit();
            return false;

        }

    }

    public void ChangerFlag(String IDMR) {
        SeConnecter();
        // on change l'etat du traitement du patient en deja traité ( flag = 1)

        String sql = "UPDATE DMRTEST "
                + "SET DEJATRAITE ='" + 1 + "' "
                + "WHERE IDMR ='" + IDMR + "' ";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");
            quit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    public void CreateTable() {
        SeConnecter();
        String sql = "CREATE TABLE DMRTEST "// devient examen
                + "(IDMR integer NOT NULL, "// devient id examen
                + " NAME varchar(20) NOT NULL, "// degagera
                + " FIRSTNAME varchar(20) NOT NULL, "// degagera
                + " BIRTHDATE DATE , "// devient date de l'exament
                + " ADRESSE varchar(120) NOT NULL, "// degagera
                + " IDPHOTO varchar(50) , "
                + "COMPTE_RENDU varchar(2000) , "
                + " DEJATRAITE integer NOT NULL, "
                + " PRIMARY KEY (IDMR))";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");
            quit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

        }
    }

    public void drop(String table) {
        SeConnecter();
        String sql = "DROP TABLE " + table;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("fait");
            quit();

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
//crrer la methode quit
// copier le constru pr crrer methode connect et ajouter connet et quit das chaque methoide
