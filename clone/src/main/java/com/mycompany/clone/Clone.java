/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clone;

import interfaceUser.ConnectionFrame;
import interfaceUser.Sql_handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
       
        Map<String, ArrayList<byte[]>> valueMap ;
        valueMap=h.GetAllImages();
        System.out.println( valueMap.keySet().toArray()[1]);
       System.out.println(valueMap.get(valueMap.keySet().toArray()[1].toString()).get(0).getClass());
        h.TransformeImage(valueMap.get(valueMap.keySet().toArray()[0].toString()).get(0));// la secondee image n'est pas un png , pas un type handlable
       
    }
}
