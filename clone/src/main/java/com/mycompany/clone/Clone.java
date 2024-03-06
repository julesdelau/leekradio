/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clone;

import interfaceUser.ConnectionFrame;
import interfaceUser.Sql_handler;


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
        
        //test remplissage table
        Sql_handler h = new Sql_handler();
       // h.drop("DMRTEST");
       // h.CreateTable();
        int iddmr = 213;
        
        String nom = "jeannnnnnnnnnne";
        String prenom = "neymmmmmmarette";
        String date =h.createdate(2005, 5, 24,20,20);
        
        String adresse = "24 grand trou";
        String photo = "1";
        String compterendu = "yapa00000000000000000000000000";
      // h.AddExamen(iddmr, nom, prenom,date , adresse, photo, compterendu,0);
       //h.testTps();
       //h.getimages();
    }
}
