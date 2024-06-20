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

    public GondolaView(GondolaController controller) {
        this.controller = controller;
        initialize();
        controller.mostrarGondolas();
    }

    private void initialize() {
        setTitle("Gestión de Góndolas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
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
                try {
                    String nroUbicacionText = nroUbicacionField.getText();
                    String sector = sectorField.getText();
                    String espacioLibreText = espacioLibreField.getText();

                    if (nroUbicacionText.isEmpty() || sector.isEmpty() || espacioLibreText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroUbicacion = Integer.parseInt(nroUbicacionText);
                    int espacioLibre = Integer.parseInt(espacioLibreText);
                    boolean disponible = disponibleCheckBox.isSelected();
                    boolean extremo = extremoCheckBox.isSelected();
                    boolean completo10 = completo10CheckBox.isSelected();

                    controller.crearGondola(nroUbicacion, sector, disponible, espacioLibre, extremo, completo10);
                    JOptionPane.showMessageDialog(null, "Góndola agregada exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Ubicación y Espacio Libre deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Actualizar Góndola");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nroUbicacionText = nroUbicacionField.getText();
                    String sector = sectorField.getText();
                    String espacioLibreText = espacioLibreField.getText();

                    if (nroUbicacionText.isEmpty() || sector.isEmpty() || espacioLibreText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroUbicacion = Integer.parseInt(nroUbicacionText);
                    int espacioLibre = Integer.parseInt(espacioLibreText);
                    boolean disponible = disponibleCheckBox.isSelected();
                    boolean extremo = extremoCheckBox.isSelected();
                    boolean completo10 = completo10CheckBox.isSelected();

                    controller.actualizarGondola(nroUbicacion, sector, disponible, espacioLibre, extremo, completo10);
                    JOptionPane.showMessageDialog(null, "Góndola actualizada exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Ubicación y Espacio Libre deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar Góndola");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nroUbicacionText = nroUbicacionField.getText();

                    if (nroUbicacionText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo de Número de Ubicación debe estar lleno", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroUbicacion = Integer.parseInt(nroUbicacionText);

                    controller.eliminarGondola(nroUbicacion);
                    JOptionPane.showMessageDialog(null, "Góndola eliminada exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Ubicación debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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
            displayArea.append("NroUbicacion: " + gondola.getNroUbicacion() + ", Sector: " + gondola.getSector() + ", Disponible: " + gondola.isDisponible() + ", Espacio Libre: " + gondola.getEspacioLibre() + ", Extremo: " + gondola.isExtremo() + ", Completo 10%: " + gondola.isCompleto_10() + "\n");
        }
    }
}
