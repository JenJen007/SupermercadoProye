
package org.example.Vista;

import org.example.Control.GondolaController;
import org.example.Modelo.Gondola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GondolaView extends JFrame {
    private GondolaController controller;
    private JTextField nroUbicacionField, sectorField, espacioLibreField;
    private JCheckBox disponibleCheckBox, extremoCheckBox, completo10CheckBox;
    private JTextArea displayArea;

   /* public void setController(GondolaController controller) {
        this.controller = controller;
    }*/

    public GondolaView(GondolaController controller) {
        this.controller = controller;
        initialize();
        controller.mostrarGondolas();
    }
    private void initialize(){
        setTitle("Gestión de Góndolas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Número de Ubicación:"));
        nroUbicacionField = new JTextField();
        inputPanel.add(nroUbicacionField);

        inputPanel.add(new JLabel("Sector:"));
        sectorField = new JTextField();
        inputPanel.add(sectorField);

        inputPanel.add(new JLabel("Disponible:"));
        disponibleCheckBox = new JCheckBox();
        inputPanel.add(disponibleCheckBox);

        inputPanel.add(new JLabel("Espacio Libre:"));
        espacioLibreField = new JTextField();
        inputPanel.add(espacioLibreField);

        inputPanel.add(new JLabel("Extremo:"));
        extremoCheckBox = new JCheckBox();
        inputPanel.add(extremoCheckBox);

        inputPanel.add(new JLabel("Completo 10%:"));
        completo10CheckBox = new JCheckBox();
        inputPanel.add(completo10CheckBox);

        JButton addButton = new JButton("Agregar Góndola");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nroUbicacion = Integer.parseInt(nroUbicacionField.getText());
                String sector = sectorField.getText();
                boolean disponible = disponibleCheckBox.isSelected();
                int espacioLibre = Integer.parseInt(espacioLibreField.getText());
                boolean extremo = extremoCheckBox.isSelected();
                boolean completo10 = completo10CheckBox.isSelected();
                controller.crearGondola( nroUbicacion, sector, disponible, espacioLibre, extremo, completo10);
            }
        });
        inputPanel.add(addButton);
        JButton updateButton = new JButton("Actualizar Góndola");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nroUbicacion = Integer.parseInt(nroUbicacionField.getText());
                String sector = sectorField.getText();
                boolean disponible = disponibleCheckBox.isSelected();
                int espacioLibre = Integer.parseInt(espacioLibreField.getText());
                boolean extremo = extremoCheckBox.isSelected();
                boolean completo_10 = completo10CheckBox.isSelected();
                controller.actualizarGondola( nroUbicacion, sector, disponible, espacioLibre, extremo, completo_10);
            }
        });
        inputPanel.add(updateButton);
        JButton deleteButton = new JButton("Eliminar Góndola");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int  nroUbicacion = Integer.parseInt(nroUbicacionField.getText());
                controller.eliminarGondola(nroUbicacion);
            }
        });
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);

        controller.mostrarGondolas();
    }

    public void mostrarGondolas(ArrayList<Gondola> gondolas) {
        displayArea.setText("");
        for (Gondola gondola : gondolas) {
            displayArea.append( "NroUbicacion: " + gondola.getNroUbicacion() + ", Sector: " + gondola.getSector() + ", Disponible: " + gondola.isDisponible() + ", Espacio Libre: " + gondola.getEspacioLibre() + ", Extremo: " + gondola.isExtremo() + ", Completo 10%: " + gondola.isCompleto_10() + "\n");
        }
    }
}
