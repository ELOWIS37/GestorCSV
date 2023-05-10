/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.furroy.gestorcsv.clases;

import java.awt.Desktop;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ELOY
 */
public class FiltreCSV extends javax.swing.JFrame {
    String sep = File.separator;
    String rutaProjecte = System.getProperty("user.dir");

    String rutaConf = rutaProjecte+sep+"config"+sep;

    public FiltreCSV() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Filtrar 'base.csv'");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Al fer clic al botó, per cada fila de 'filtre.csv' cercarà per 'codi_article' totes les files coincidents amb l'arxiu 'base.csv'.");

        jLabel2.setFont(new java.awt.Font("Stencil", 0, 24)); // NOI18N
        jLabel2.setText("Filtrar csv");
        jLabel2.setToolTipText("");

        jLabel3.setText("Es generarà un arxiu de sortida 'out.csv' i un altre arxiu 'info.txt' amb els codis no trobats.");

        jButton2.setText("Obrir Directori");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Accedir al directori dels arxius de sortida.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
                String rutaOutput = rutaConf + sep + "output";
                // Obtener el objeto File de la carpeta
                File carpeta = new File(rutaOutput);

                // Verificar si la carpeta existe y es un directorio
                if (carpeta.exists() && carpeta.isDirectory()) {
                    // Abrir la carpeta con el explorador de archivos predeterminado del sistema
                    Desktop.getDesktop().open(carpeta);
                } else {
                    System.out.println("La carpeta especificada no existeix");
                }
            } catch (IOException ex) {
                System.out.println("Error al obrir la carpeta: " + ex.getMessage());
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        String baseFile = rutaConf + "Base.csv";
        String filtreFile = rutaConf + "Filtre.csv";

        // Crea los archivos de salida dentro de la carpeta "output"
        File outFile = new File(rutaConf + sep + "output" + sep + "out.csv");
        File infoFile = new File(rutaConf + sep + "output" + sep + "info.txt");

        try (BufferedReader baseReader = new BufferedReader(new FileReader(baseFile));
             BufferedReader filtreReader = new BufferedReader(new FileReader(filtreFile));
             BufferedWriter outWriter = new BufferedWriter(new FileWriter(outFile));
             BufferedWriter infoWriter = new BufferedWriter(new FileWriter(infoFile))) {

            Set<String> codigosBase = new HashSet<>();
            String line;
            while ((line = baseReader.readLine()) != null) {
                String codigo = line.split(",")[0].trim();
                codigosBase.add(codigo);
            }

            Set<String> codigosNoEncontrados = new HashSet<>();
            while ((line = filtreReader.readLine()) != null) {
                String codigo = line.split(",")[0].trim();
                if (codigosBase.contains(codigo)) {
                    outWriter.write(line);
                    outWriter.newLine();
                } else {
                    codigosNoEncontrados.add(codigo);
                }
            }

            if (!codigosNoEncontrados.isEmpty()) {
                infoWriter.write("Els següents codis d'article no s'han trobats en l'arxiu base.csv:");
                infoWriter.newLine();
                for (String codigo : codigosNoEncontrados) {
                    infoWriter.write(codigo);
                    infoWriter.newLine();
                }
            } else {
                infoWriter.write("Tots els codis d'article en l'arxiu filtre.csv coincideixen amb l'arxiu base.csv");
                infoWriter.newLine();
            }

            System.out.println("Procés finalitzat");

        } catch (IOException e) {
            System.out.println("Error al processar els arxius: " + e.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FiltreCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltreCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltreCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltreCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FiltreCSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}