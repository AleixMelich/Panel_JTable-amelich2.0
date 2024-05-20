package org.amelich.view;

import javax.swing.*;

/**
 * Aquesta classe és la vista de la nostra aplicació.
 */
public class ViewPestanyes extends JFrame{
    private JPanel panel;
    private JTabbedPane pestanyes;
    private JButton modificarButton;
    private JButton insertarButton;
    private JButton borrarButton;

    //PESTANYA ALUMNES 2023/2024
    private JTable taula;
    private JTextField campNom;
    private JTextField campNota;
    private JCheckBox SI_CheckBox;
    private JButton filtreButton;
    private JButton exportarButton;

    //PESTANYA ESMORZAR
    private JTable taula1;
    private JTextField campQuantitat;
    private JComboBox comboBox1;


    //GETTERS
    public JTabbedPane getPestanyes() {
        return pestanyes;
    }
    public JButton getModificarButton() {
        return modificarButton;
    }
    public JButton getInsertarButton() {
        return insertarButton;
    }
    public JButton getBorrarButton() {
        return borrarButton;
    }

        //PESTANYA ALUMNES 2023/2024
    public JTable getTaula() {
        return taula;
    }
    public JTextField getCampNom() {
        return campNom;
    }
    public JTextField getCampNota() {
        return campNota;
    }
    public JCheckBox getSI_CheckBox() {
        return SI_CheckBox;
    }
    public JButton getFiltreButton() {
        return filtreButton;
    }
    public JButton getExportarButton() {
        return exportarButton;
    }

        //PESTANYA ESMORZAR
    public JTable getTaula1() {
        return taula1;
    }
    public JTextField getCampQuantitat() {
        return campQuantitat;
    }
    public JComboBox getComboBox1() {
        return comboBox1;
    }


    /**
     * Constructor de la classe.
     */
    public ViewPestanyes() {

        //PER PODER VEURE LA FINESTRA
        this.setContentPane(panel); // DEFINIM EL PANEL QUE CONTÉ TOTS ELS ELEMENTS
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // PERMETRE TANCAR LA FINESTRA
        this.pack(); // AJUSTAR LA FINESTRA AL CONTINGUT
        this.setVisible(true); // FER VISIBLE LA FINESTRA
        this.setSize(800, 600); // DEFINIM LA MIDA DE LA FINESTRA
        this.setLocationRelativeTo(null); // CENTRAR LA FINESTRA
        this.setTitle("PROJECTE UF5 - AMELICH"); // DEFINIM EL TITOL DE LA FINESTRA
        this.setIconImage(new ImageIcon("./src/main/resources/imagen.jpg").getImage()); // DEFINIM LA ICONA DE LA FINESTRA

        filtreButton.setToolTipText("Boto per filtrar les dades");
        exportarButton.setToolTipText("Boto per exportar les dades");
    }

    /**
     * Mètode per crear una finestra de filtrat.
     * @param caracterFiltre
     * @param scrollPane
     * @return
     */
    public JFrame createFilterWindow(String caracterFiltre, JScrollPane scrollPane) {
        JFrame finestraFiltrat = new JFrame();
        finestraFiltrat.setTitle("Filtrat per: " + caracterFiltre);
        finestraFiltrat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestraFiltrat.add(scrollPane);
        finestraFiltrat.pack();
        finestraFiltrat.setVisible(true);
        finestraFiltrat.setLocationRelativeTo(null);
        finestraFiltrat.setIconImage(new ImageIcon("./src/main/resources/filter.png").getImage());
        finestraFiltrat.setSize(400, 300);
        finestraFiltrat.setAlwaysOnTop(true); // PER A QUE ESTIGUI SEMPRE AL DAMUNT DE TOT
        return finestraFiltrat;
    }

}
