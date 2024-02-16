/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clone;

import interfaceUser.ConnectionFrame;

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
    }
}
