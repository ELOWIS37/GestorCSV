package com.furroy.gestorcsv;

import com.furroy.gestorcsv.clases.ArxiusCSV;

/**
 *
 * @author ELOY
 * @version 6.3.0
 */
public class AppMain {
    public static void main (String[] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArxiusCSV().setVisible(true);
            }
        });
    }
}
