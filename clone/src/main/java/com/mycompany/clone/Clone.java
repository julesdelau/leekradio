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
        int iddmr = 001;
        
        String nom = "jean";
        String prenom = "neymare";
        String date =h.createdate(2005, 5, 24);
        
        String adresse = "24 grand trou";
        String photo = "1";
        String compterendu = "yapa00000000000000000000000000";
       // h.addDmr(iddmr, nom, prenom,date , adresse, photo, compterendu,0);
       // h.addDmr(2, "delaurenti", "jules",h.createdate(2002, 01, 24) , "22 rue pixerecourt , paris", photo, compterendu,0);
       // h.addDmr(3, "meuh", "patient 2",h.createdate(2005, 11, 21) , adresse, photo, compterendu,1);
       // h.addDmr(4, "ivan", "sebastion",h.createdate(iddmr, iddmr, iddmr) , adresse, photo, compterendu,1);
       // h.addDmr(5," jean", "prenom",h.createdate(iddmr, iddmr, iddmr) , adresse, photo, compterendu,0);
    }
}
