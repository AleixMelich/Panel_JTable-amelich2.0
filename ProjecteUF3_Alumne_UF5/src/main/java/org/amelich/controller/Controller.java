package org.amelich.controller;

import org.amelich.app.LaMevaExcepcio;
import org.amelich.model.Alumne;
import org.amelich.model.Alumne.Esmorzar;
import org.amelich.model.Model;
import org.amelich.view.ViewPestanyes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Classe que controla les accions de l'aplicació
 */
public class Controller implements PropertyChangeListener { //1. Implementació de interfície PropertyChangeListener

    //2. Propietat lligada per controlar quan genero una excepció
    public static final String PROP_EXCEPCIO = "excepcio";
    private LaMevaExcepcio excepcio;

    public LaMevaExcepcio getExcepcio() {
        return excepcio;
    }

    public void setExcepcio(LaMevaExcepcio excepcio) {
        LaMevaExcepcio valorVell = this.excepcio;
        this.excepcio = excepcio;
        canvis.firePropertyChange(PROP_EXCEPCIO, valorVell, excepcio);
    }

    //3. Propietat PropertyChangesupport necessària per poder controlar les propietats lligades
    PropertyChangeSupport canvis = new PropertyChangeSupport(this);

    //4. Mètode on posarem el codi de tractament de les excepcions --> generat per la interfície PropertyChangeListener
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LaMevaExcepcio rebuda = (LaMevaExcepcio) evt.getNewValue();

        try {
            throw rebuda;
        } catch (LaMevaExcepcio e) {
            //Aquí farem ele tractament de les excepcions de l'aplicació
            switch (evt.getPropertyName()) {
                case PROP_EXCEPCIO:

                    switch (rebuda.retornaNumero()) {
                        case 1:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge(), "Avís", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 2, 3:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge(), "Error", JOptionPane.ERROR_MESSAGE);
                            this.view.getCampNom().setSelectionStart(0);
                            this.view.getCampNom().setSelectionEnd(this.view.getCampNom().getText().length());
                            this.view.getCampNom().requestFocus();
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge(), "Error", JOptionPane.ERROR_MESSAGE);
                            this.view.getCampNota().setSelectionStart(0);
                            this.view.getCampNota().setSelectionEnd(this.view.getCampNota().getText().length());
                            this.view.getCampNota().requestFocus();
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge());
                            if (this.view.getCampNom().getText().isBlank()) {
                                this.view.getCampNom().requestFocus();
                            } else if (this.view.getCampNota().getText().isBlank()) {
                                this.view.getCampNota().requestFocus();
                            } else {
                                this.view.getCampQuantitat().requestFocus();
                            }
                            break;
                        case 6:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge(), "Error", JOptionPane.ERROR_MESSAGE);
                            this.view.getCampQuantitat().setSelectionStart(0);
                            this.view.getCampQuantitat().setSelectionEnd(this.view.getCampQuantitat().getText().length());
                            this.view.getCampQuantitat().requestFocus();
                            break;
                        case 7, 8, 9:
                            JOptionPane.showMessageDialog(null, rebuda.retornaMissatge(), "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
            }
        }
    }

    private Model model;
    private ViewPestanyes view;

    /**
     * Constructor de la classe Controller
     *
     * @param model model de l'aplicació
     * @param view  vista de l'aplicació
     */
    public Controller(Model model, ViewPestanyes view) {
        this.model = model;
        this.view = view;

        //Mètode per lligar la vista i el model
        lligarVistaModel();

        //Assigno el codi dels listeners
        assignarCodiListeners();

        //5. Necessari perquè Controller reaccioni davant de canvis a les propietats lligades
        canvis.addPropertyChangeListener(this);

    }

    /**
     * Mètode per lligar la vista i el model
     */
    private void lligarVistaModel() {
        //Es fixa la pestanya alumnes
        JTable taula = view.getTaula();
        taula.setModel(this.model.getModel());
        //S'amaga la columna objecte
        taula.getColumnModel().getColumn(3).setMinWidth(0);
        taula.getColumnModel().getColumn(3).setMaxWidth(0);
        taula.getColumnModel().getColumn(3).setPreferredWidth(0);

        //Es fixa la pestanya Esmorzar
        JTable taula1 = view.getTaula1();
        taula1.setModel(this.model.getModelEsmorzar());
        //Desactivem les pestanyes del panel
        view.getPestanyes().setEnabledAt(1, false);
        view.getPestanyes().setTitleAt(1, "Esmorzar de..");
    }

    /**
     * Mètode per assignar el codi dels listeners
     * Aquest mètode conté el codi dels listeners dels botons i de les taules
     * Així com també el codi per a la gestió de les excepcions
     * Aquest mètode també conté el codi per a la gestió de la finestra de filtratge
     * i el codi per a la gestió de la finestra de guardat de les dades
     */
    public void assignarCodiListeners() {
        Model modelo = this.model;

        DefaultTableModel model = modelo.getModel();
        DefaultTableModel modelEsmorzar = modelo.getModelEsmorzar();
        DefaultTableModel modelFiltrat = modelo.getModelFiltrat();

        JTabbedPane pestanyes = view.getPestanyes();
        JButton insertarButton = view.getInsertarButton();
        JButton borrarButton = view.getBorrarButton();
        JButton modificarButton = view.getModificarButton();
        JButton exportarButton = view.getExportarButton();

        //PESTANYA ALUMNES 2023/2024
        JTable taula = view.getTaula();
        JTextField campNom = view.getCampNom();
        JTextField campNota = view.getCampNota();
        JCheckBox SI_CheckBox = view.getSI_CheckBox();
        JButton filtreButton = view.getFiltreButton();

        //PESTANYA ESMORZAR
        JTable taula1 = view.getTaula1();
        JTextField campQuantitat = view.getCampQuantitat();
        JComboBox comboBox1 = view.getComboBox1();

        // CONFIGURACIONS DE LA TAULA
        configureTable(taula);
        configureTable(taula1);


// CODI DEL CLIC AL BOTO INSERTAR
        insertarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             * @return Insereix un alumne o un esmorzar segons la pestanya seleccionada.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S'ha clicat el boto de INSERTAR");

                if (pestanyes.getSelectedIndex() == 0) {    //SI ESTEM A LA PESTANYA ALUMNE:
                    if (campNom.getText().isBlank() || campNota.getText().isBlank()) {
                        setExcepcio(new LaMevaExcepcio(5));
                    } else {
                        try {
                            boolean nomRepetit = false;
                            for (int i = 0; i < model.getRowCount(); i++) {
                                if (campNom.getText().equals(model.getValueAt(i, 0))) {
                                    setExcepcio(new LaMevaExcepcio(3));
                                    nomRepetit = true;
                                    break;
                                }
                            }

                            if (!nomRepetit) {
                                NumberFormat num = NumberFormat.getNumberInstance(Locale.getDefault());   //Creem un número que entèn la cultura que utilitza l'aplicació
                                double nota = num.parse(campNota.getText().trim()).doubleValue();  //intentem convertir el text a double
                                String nom=(campNom.getText());
                                if (nom.matches(".*\\d.*")){
                                    setExcepcio(new LaMevaExcepcio(2));
                                } else if (!campNota.getText().matches("[0-9]+([.,][0-9]+)?") || nota < 0 || nota > 10){
                                    throw new ParseException("", 0);
                                } else{
                                    Alumne al = new Alumne(campNom.getText(), nota, SI_CheckBox.isSelected(), new TreeSet<Esmorzar>());
                                    model.addRow(new Object[]{campNom.getText(), nota, SI_CheckBox.isSelected(), al});
                                    JOptionPane.showMessageDialog(null, "Has inscrit un nou alumne", "Inscripció correcta", JOptionPane.INFORMATION_MESSAGE);
                                    llimpiarCampsAlumnes();
                                }
                            }
                        } catch (ParseException ex) {
                            setExcepcio(new LaMevaExcepcio(4));
                        }
                    }
                } else {      //SI ESTEM A LA PESTANYA ESMORZAR:
                    if (campQuantitat.getText().isBlank()) {
                        setExcepcio(new LaMevaExcepcio(5));
                    } else {
                        try {
                            Alumne al = (Alumne) model.getValueAt(taula.getSelectedRow(), 3); // Obtenim l'alumne seleccionat a la primera taula
                            int quantitat = Integer.parseInt(campQuantitat.getText());
                            Esmorzar nouEsmorzar = new Esmorzar((Esmorzar.Producte) comboBox1.getSelectedItem(), quantitat); // Creem el nou Esmorzar
                            llimpiarCampsEsmorzar();


                            // Busquem si el Esmorzar ja existeix en el TreeSet
                            Esmorzar esmorzarExistent = null;
                            for (Esmorzar esmorzar : al.getEsmorzars()) {
                                if (esmorzar.getProducte().equals(nouEsmorzar.getProducte())) {
                                    esmorzarExistent = esmorzar;
                                    break;
                                }
                            }

                            if (esmorzarExistent != null) {
                                // Si el Esmorzar ja existeix, actualitzem la seva quantitat
                                esmorzarExistent.setQuantitat(esmorzarExistent.getQuantitat() + nouEsmorzar.getQuantitat());
                                JOptionPane.showMessageDialog(null, "Producte existent, s'ha actualitzat la quantitat.", "Actualització correcta", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                // Si el Esmorzar no existeix, l'afegim al TreeSet
                                al.getEsmorzars().add(nouEsmorzar);
                            }

                            // Actualitzem la taula
                            ompliEsmorzar(al, modelEsmorzar);
                        } catch (NumberFormatException ex) {
                            setExcepcio(new LaMevaExcepcio(6));
                        }
                    }
                }
            }
        });


// SELECCIÓ DELS ELEMENTS DE LA TAULA ALUMNES
        taula.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int filaSel = taula.getSelectedRow();
                if (filaSel != -1) { // TENIM UNA FILA SELECCIONADA
                    campNom.setText(model.getValueAt(filaSel, 0).toString());
                    campNota.setText(model.getValueAt(filaSel, 1).toString().replaceAll("\\.", ","));
                    SI_CheckBox.setSelected((Boolean) model.getValueAt(filaSel, 2));

                    // ACTIVEM LA PESTANYA DE L'ESMORZAR DE L'ALUMNE SELECCIONAT
                    view.getPestanyes().setEnabledAt(1, true);
                    view.getPestanyes().setTitleAt(1, "Esmorzar de.. " + campNom.getText());

                    //POSEM VALOR AL COMBO DE PRODUCTES
                    view.getComboBox1().setModel(modelo.getComboBoxModel());
                    ompliEsmorzar((Alumne) model.getValueAt(filaSel, 3), modelEsmorzar);

                } else { // NO TENIM CAP FILA SELECCIONADA, POSEM TOTS ELS CAMPS EN BLANC I DESACTIVEM PESTANYA 1
                    llimpiarCampsAlumnes();

                    //DESACTIVEM LA PESTANYA 1
                    view.getPestanyes().setEnabledAt(1, false);
                    view.getPestanyes().setTitleAt(1, "Esmorzar de..");
                }
            }
        });


// SELECCIÓ DELS ELEMENTS DE LA TAULA ESMORZAR
        taula1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int filaSel = taula1.getSelectedRow();
                if (filaSel != -1) {
                    campQuantitat.setText(modelEsmorzar.getValueAt(filaSel, 1).toString());
                    comboBox1.setSelectedItem(modelEsmorzar.getValueAt(filaSel, 0));
                } else {
                    llimpiarCampsEsmorzar();
                }
            }
        });


// CODI DEL BOTO BORRAR
        borrarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             * @return Borra un alumne o un esmorzar segons la pestanya seleccionada.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S'ha clicat el boto de BORRAR");

                if (pestanyes.getSelectedIndex() == 0) { // Si estem a la primera pestanya
                    int filaSel = taula.getSelectedRow();
                    if (filaSel != -1) {
                        model.removeRow(filaSel);
                        llimpiarCampsAlumnes();
                    } else {
                        setExcepcio(new LaMevaExcepcio(1));
                    }
                } else if (pestanyes.getSelectedIndex() == 1) { // Si estem a la segona pestanya
                    int filaSel = taula1.getSelectedRow();
                    if (filaSel != -1) {
                        // Obtenim l'alumne seleccionat a la primera taula
                        Alumne alumneSel = (Alumne) model.getValueAt(taula.getSelectedRow(), 3);
                        // Eliminem l'esmorzar de l'alumne
                        buidaEsmorzar(alumneSel, modelEsmorzar, filaSel);
                        llimpiarCampsEsmorzar();
                    } else {
                        setExcepcio(new LaMevaExcepcio(1));
                    }
                }
            }
        });


// CODI DEL BOTO MODIFICAR
        modificarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             * @return Modifica un alumne o un esmorzar segons la pestanya seleccionada.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S'ha MODIFICAT un insert");

                if (pestanyes.getSelectedIndex() == 0) { // Si estem a la primera pestanya
                    int filaSel = taula.getSelectedRow();
                    if (filaSel != -1) {

                        if (campNom.getText().isBlank() || campNota.getText().isBlank()) {
                            setExcepcio(new LaMevaExcepcio(5));
                        } else {
                            try {
                                model.removeRow(filaSel);

                                NumberFormat num = NumberFormat.getNumberInstance(Locale.getDefault());   //Creem un número que entèn la cultura que utilitza l'aplicació
                                double nota = num.parse(campNota.getText().trim()).doubleValue();  //intentem convertir el text a double
                                String nom = (campNom.getText());
                                if (nom.matches(".*\\d.*")) {
                                    setExcepcio(new LaMevaExcepcio(2));
                                } else if (!campNota.getText().matches("[0-9]+([.,][0-9]+)?") || nota < 0 || nota > 10) {
                                    throw new ParseException("", 0);
                                } else {
                                    Alumne al = new Alumne(campNom.getText(), nota, SI_CheckBox.isSelected(), new TreeSet<Esmorzar>());
                                    model.addRow(new Object[]{campNom.getText(), nota, SI_CheckBox.isSelected(), al});
                                    JOptionPane.showMessageDialog(null, "Has modificat l'alumne", "Modificació correcta", JOptionPane.INFORMATION_MESSAGE);
                                    llimpiarCampsAlumnes();
                                }
                            }catch (ParseException ex) {
                                    setExcepcio(new LaMevaExcepcio(4));
                            }
                            //DESACTIVEM LA PESTANYA 1
                            view.getPestanyes().setEnabledAt(1, false);
                            view.getPestanyes().setTitleAt(1, "Esmorzar de..");
                        }
                    } else setExcepcio(new LaMevaExcepcio(1));
                } else if (pestanyes.getSelectedIndex() == 1) { // Si estem a la segona pestanya
                    JOptionPane.showMessageDialog(null, "De moment, un esmorzar no es pot modificar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


// CODI DEL BOTO FILTRAR
        filtreButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             * @return Filtra els alumnes per la primera lletra del seu nom.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S'ha clicat el boto de FILTRAR");
                String caracterFiltre = JOptionPane.showInputDialog(null, "Introdueix el caràcter amb el qual vols filtrar els alumnes:", "Filtra per caràcter", JOptionPane.QUESTION_MESSAGE);

                if (caracterFiltre != null && !caracterFiltre.isEmpty()) {
                    DefaultTableModel model = (DefaultTableModel) taula.getModel();

                    // Convertim les files de la taula a una llista d'alumnes
                    List<Alumne> alumnes = new ArrayList<>();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        alumnes.add((Alumne) model.getValueAt(i, 3));
                    }

                    // Convertimos el caracterFiltre a minúsculas
                    String caracterFiltreLowerCase = caracterFiltre.toLowerCase();

                    // Utilitzem el mètode stream() per a filtrar els alumnes
                    List<Alumne> alumnesFiltrats = alumnes.stream()
                            .filter(alumne -> alumne.getNomCognom().toLowerCase().startsWith(caracterFiltreLowerCase))
                            .collect(Collectors.toList());

                    // Llimpiem la taula modelFiltrat
                    modelFiltrat.setRowCount(0);

                    if (!alumnesFiltrats.isEmpty()) {
                        // Afegim a la taula modelFiltrat només l'alumne filtrat
                        for (Alumne alumne : alumnesFiltrats) {
                            modelFiltrat.addRow(new Object[]{alumne.getNomCognom(), alumne.getNota(), alumne.isFct()});
                        }

                        // Creem una nova taula per a la finestra de filtratge
                        JTable taulaFiltrat = new JTable(modelFiltrat);
                        JScrollPane scrollPane = new JScrollPane(taulaFiltrat);
                        configureTable(taulaFiltrat);
                        // Creem la finestra de filtratge
                        JFrame finestraFiltrat = view.createFilterWindow(caracterFiltre, scrollPane);
                    } else {
                        setExcepcio(new LaMevaExcepcio(9));
                    }
                }
            }
        });


// CODI DEL BOTO EXPORTAR
        exportarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             * @return Exporta les dades a un fitxer de text.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S'ha clicat el boto de EXPORTAR");

                if (model.getRowCount() > 0) {
                    File arxiu = seleccionarArxiu();
                    if (arxiu != null) {
                        PrintWriter writer = crearPrintWriter(arxiu);
                        if (writer != null) {
                            escriureFiles(writer);
                            JOptionPane.showMessageDialog(null, "Fitxer guardat amb exit amb el nom: " + arxiu.getName() + "\n" + "A la ruta: " + arxiu.getAbsolutePath());
                        }
                    }
                } else {
                    setExcepcio(new LaMevaExcepcio(7));
                }
            }

            /**
             * @return Retorna el fitxer seleccionat.
             */
            private File seleccionarArxiu() {
                JFileChooser fitxerElegit = new JFileChooser();
                fitxerElegit.setCurrentDirectory(new File("./src/main/resources/"));
                fitxerElegit.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fitxerElegit.setDialogTitle("Guardar arxiu de sortida");

                int userSelection = fitxerElegit.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File arxiu = fitxerElegit.getSelectedFile();
                    if (!arxiu.getAbsolutePath().endsWith(".txt")) {
                        arxiu = new File(arxiu.getAbsolutePath() + ".txt");
                    }
                    return arxiu;
                }
                return null;
            }

            /**
             * @param arxiu Fitxer on es guardarà la informació.
             * @return Retorna un PrintWriter per a escriure les dades al fitxer.
             */
            private PrintWriter crearPrintWriter(File arxiu) {
                try {
                    return new PrintWriter(new BufferedWriter(new FileWriter(arxiu)));
                } catch (IOException ex) {
                    setExcepcio(new LaMevaExcepcio(8));
                    return null;
                }
            }

            /**
             * @param writer PrintWriter per a escriure les dades al fitxer.
             * @return Escriu les dades al fitxer.
             */
            private void escriureFiles(PrintWriter writer) {
                try {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        writer.println("ENTRADA " + (i + 1));
                        writer.println("Nom i cognom: " + model.getValueAt(i, 0));
                        writer.println("Nota: " + model.getValueAt(i, 1));
                        writer.println("FCT: " + model.getValueAt(i, 2));
                        writer.println("--------------------");
                    }
                } finally {
                    writer.close();
                }
            }
        });


// TRACTAR EXCEPCIONS, TRY/CATCH/FINALLY
        // EN AQUEST CAS DETERMINEM QUE S'HA D'INTRODUIR UNA NOTA VALIDA (DEL 0 AL 10)
        campNota.addFocusListener(new FocusAdapter() {
            /**
             * Invoked when a component loses the keyboard focus.
             *
             * @param e
             * @return Controla que la nota sigui un número vàlid.
             */
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (!campNota.getText().matches("[0-9]+([.,][0-9]+)?")) {
                    setExcepcio(new LaMevaExcepcio(4));
                }

                try {
                    NumberFormat num=NumberFormat.getNumberInstance(Locale.getDefault());
                    double nota= num.parse(campNota.getText().trim()).doubleValue();
                    if (nota<0 || nota>10) {
                        setExcepcio(new LaMevaExcepcio(4));
                    }
                } catch (ParseException ignored) {}
            }
        });

        // EN AQUEST CAS DETERMINEM QUE S'HA D'INTRODUIR UNA NOM VALID ON NOMES PERMETRÀ CARACTERS
        campNom.addFocusListener(new FocusAdapter() {
            /**
             * Invoked when a component loses the keyboard focus.
             *
             * @param e
             * @return Controla que el nom sigui un text vàlid.
             */
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                String nom=(campNom.getText());
                if (nom.matches(".*\\d.*")){
                    setExcepcio(new LaMevaExcepcio(2));
                }
            }
        });

        // EN AQUEST CAS DETERMINEM QUE S'HA D'INTRODUIR UNA QUANTITAT VALIDA (NUMEROS ENTERS)+
        campQuantitat.addFocusListener(new FocusAdapter() {
            /**
             * Invoked when a component loses the keyboard focus.
             *
             * @param e
             * @return Controla que la quantitat sigui un número vàlid.
             */
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (campQuantitat.getText().matches(".*[a-zA-Z].*")) {
                    setExcepcio(new LaMevaExcepcio(6));
                }
            }
        });


// DESAR LES DADES AL TANCAR LA FINESTRA SI L'USUARI VOL
        view.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             * @return Guarda les dades al tancar la finestra si l'usuari vol.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        "Vols guardar les dades abans de tancar?",
                        "Confirmació",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm == 0) {
                    modelo.escriureDadesFitxer();
                } else {
                    System.exit(0);
                }
            }
        });
    }


//METODES
    //METODES DE LLIMPIAR CAMPS
    private void llimpiarCampsAlumnes() {
        view.getCampNom().setText("");
        view.getCampNota().setText("");
        view.getSI_CheckBox().setSelected(false);
        view.getCampNom().requestFocus();
    }
    private void llimpiarCampsEsmorzar() {
        view.getCampQuantitat().setText("");
        view.getCampQuantitat().requestFocus();
        view.getComboBox1().setPopupVisible(true); //amb aquesta linia despleguem el combobox
    }

    //METODE DE CONFIGURACIÓ DE LA TAULA
    private void configureTable(JTable table) {
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,13));
        table.setRowHeight(23);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setDefaultEditor(Object.class, null);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //METODES DE CONTROL DE LA TAULA ESMORZAR
    private static void ompliEsmorzar(Alumne al,DefaultTableModel modelEsmorzar) {
        // Llimpiem la taula
        modelEsmorzar.setRowCount(0);
        // Omplim la taula amb els esmorzars de l'alumne
        for (Esmorzar esmorzar : al.getEsmorzars()){
            modelEsmorzar.addRow(new Object[]{esmorzar.getProducte(), esmorzar.getQuantitat()});
        }
    }
    private static void buidaEsmorzar(Alumne al, DefaultTableModel modelEsmorzar, int filaSel) {
        // Eliminem l'esmorzar de la col·lecció de l'alumne
        Esmorzar esmorzarToRemove = new ArrayList<>(al.getEsmorzars()).get(filaSel);
        al.getEsmorzars().remove(esmorzarToRemove);

        // Eliminem la fila seleccionada de la taula
        modelEsmorzar.removeRow(filaSel);
    }
}
