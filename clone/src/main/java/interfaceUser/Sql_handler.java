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

    public boolean addDmr(String iddmr, String fichierXml) {
        try{
            String sql = "INSERT into dmr VALUE( '" +iddmr+"' , '" +fichierXml +"')";
           st = connection.createStatement();
           rs = st.executeQuery(sql);
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
// table identite(id,mdp,specialite,nom , prenom)id des medic secret et manip

//table finder(iddmr , nom ,prenom, adresse, datenaissnce)
// soi dmr(iddmr , fichierxml)
