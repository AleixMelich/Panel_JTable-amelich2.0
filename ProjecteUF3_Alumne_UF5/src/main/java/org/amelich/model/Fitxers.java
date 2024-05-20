package org.amelich.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

/**
 * Classe que conté els mètodes per llegir i escriure les dades en un fitxer
 */
public class Fitxers {
    //propietat constant per definir el nom del fitxer una sola vegada dins l'aplicació
    public static final String FITXER="./src/main/resources/dades2.dat";
    static File f = new File(FITXER);        //apuntador al fitxer, però no el crea

    /**
     * Mètode per llegir les dades del fitxer
     * @param model model de la taula
     */
    public static void llegirDades(DefaultTableModel model) {


        //Mirem si el fitxer ja existix (o no)
        if (f.exists() && !f.isDirectory()) {
            ObjectInputStream entrada = null;
            try {
                entrada = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
                while (true) {
                    Alumne alumne = (Alumne) entrada.readObject();
                    model.addRow(new Object[]{alumne.getNomCognom(), alumne.getNota(), alumne.isFct(), alumne});
                }
            } catch (EOFException ex) {
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al llegir les dades");
            } finally {
                try {
                    if (entrada != null) entrada.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al tancar el fitxer de lectura");
                }
            }
        }
    }

    /**
     * Mètode per escriure les dades al fitxer
     * @param model model de la taula
     */
    public static void escriureDades(DefaultTableModel model) {

        //Mirem si hi ha dades
        if (model.getRowCount() >= 0) {
            ObjectOutputStream sortida = null;
            try {
                sortida = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
                for (int i = 0; i < model.getRowCount(); i++) {
                    Alumne escriptura = (Alumne) model.getValueAt(i, 3);
                    sortida.writeObject(escriptura);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar les dades");
            } finally {
                try {
                    if (sortida != null) sortida.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al tancar el fitxer de sortida");
                }
            }
        }
    }
}

