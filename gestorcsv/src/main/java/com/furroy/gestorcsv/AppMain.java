package com.furroy.gestorcsv;

import com.furroy.gestorcsv.clases.GestorCSV;

/**
 *
 * @author ELOY
 * @version 4.8.5
 */
public class AppMain {
    public static void main (String[] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestorCSV().setVisible(true);
            }
        });
    }
}
