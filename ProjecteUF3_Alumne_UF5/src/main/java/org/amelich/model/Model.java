package org.amelich.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Model {
    //Propietats de la classe
    private DefaultTableModel model;
    private DefaultTableModel modelEsmorzar;
    private DefaultTableModel modelFiltrat;

    private ComboBoxModel<Alumne.Esmorzar.Producte> comboBoxModel;


    //Getters dels objectes del model
    public DefaultTableModel getModel() {
        return model;
    }
    public DefaultTableModel getModelEsmorzar() {
        return modelEsmorzar;
    }
    public DefaultTableModel getModelFiltrat() {
        return modelFiltrat;
    }

    public ComboBoxModel<Alumne.Esmorzar.Producte> getComboBoxModel() {
        return comboBoxModel;
    }


    /**
     * Constructor de la classe Model
     */
    public Model() {

        model=new DefaultTableModel(new Object[]{"NOM","NOTA","FCT","Object"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // LES CELES NO SON EDITABLES
                return false;
            }

            //Permet definir el tipo de cada columna
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Double.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return Object.class;
                }
            }
        };


        modelEsmorzar=new DefaultTableModel(new Object[]{"PRODUCTE","QUANTITAT"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // LES CELES NO SON EDITABLES
                return false;
            }

            //Permet definir el tipo de cada columna
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Integer.class;
                    default:
                        return Object.class;
                }
            }
        };


        modelFiltrat=new DefaultTableModel(new Object[]{"NOM","NOTA","FCT"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // LES CELES NO SON EDITABLES
                return false;
            }

            //Permet definir el tipo de cada columna
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Double.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return Object.class;
                }
            }
        };

        // Estructura del ComboBoxModel
        comboBoxModel = new DefaultComboBoxModel<>(Alumne.Esmorzar.Producte.values());


        //Omplim la taula en dades del fitxer
        Fitxers.llegirDades(model);
    }

    /**
     * Mètode per afegir un alumne a la taula
     */
    public void escriureDadesFitxer(){
        Fitxers.escriureDades(model);
    }
}
