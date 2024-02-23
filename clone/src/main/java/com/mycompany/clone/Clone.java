/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clone;

import interfaceUser.ConnectionFrame;
import interfaceUser.Sql_handler;
import static java.sql.JDBCType.DATE;
import static java.sql.Types.NULL;

/**
 *
 * @author Jules_D
 */
public class Clone {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionFrame().setVisible(true);
                // pour test

            }
        });
        System.out.println("Hello World!");
        //test remplissage table
        Sql_handler h = new Sql_handler();
       // h.drop("DMRTEST");
       // h.CreateTable();
        int iddmr = 001;
        String nom = "jean";
        String prenom = "neymare";
        String date =h.createdate(2005, 5, 24);
        
        String adresse = "24 grand trou";
        String photo = "1";
        String compterendu = "yapa00000000000000000000000000";
        //h.addDmr(iddmr, nom, prenom,date , adresse, photo, compterendu);
    }
}
