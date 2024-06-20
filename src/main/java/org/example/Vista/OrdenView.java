package org.example.Vista;

import org.example.Control.OrdenController;
import org.example.Modelo.Orden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdenView extends JFrame {
    private OrdenController controller;
    private JTextField nroSerieArticuloField, cantidadAComprarField, fechaField;
    private JTextArea displayArea;

    public OrdenView(OrdenController controller) {
        this.controller = controller;
        initialize();
        mostrarOrdenes(controller.mostrarOrdenes());
    }

    private void initialize() {
        setTitle("Gestión de Órdenes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Número de Serie del Artículo:"));
        nroSerieArticuloField = new JTextField();
        inputPanel.add(nroSerieArticuloField);

        inputPanel.add(new JLabel("Cantidad a Comprar:"));
        cantidadAComprarField = new JTextField();
        inputPanel.add(cantidadAComprarField);

        inputPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        fechaField = new JTextField();
        inputPanel.add(fechaField);

        JButton addButton = new JButton("Agregar Orden");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                    int cantidadAComprar = Integer.parseInt(cantidadAComprarField.getText());
                    java.sql.Date fecha = java.sql.Date.valueOf(fechaField.getText());
                    controller.crearOrden(nroSerieArticulo, cantidadAComprar, fecha);
                    mostrarOrdenes(controller.mostrarOrdenes());
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    mostrarError("Número de serie y cantidad deben ser números enteros.");
                } catch (IllegalArgumentException ex) {
                    mostrarError("Fecha debe tener el formato YYYY-MM-DD.");
                } catch (Exception ex) {
                    mostrarError("Ocurrió un error: " + ex.getMessage());
                }
            }
        });
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Actualizar Orden");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                    int cantidadAComprar = Integer.parseInt(cantidadAComprarField.getText());
                    java.sql.Date fecha = java.sql.Date.valueOf(fechaField.getText());
                    controller.actualizarOrden(nroSerieArticulo, cantidadAComprar, fecha);
                    mostrarOrdenes(controller.mostrarOrdenes());
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    mostrarError("Número de serie y cantidad deben ser números enteros.");
                } catch (IllegalArgumentException ex) {
                    mostrarError("Fecha debe tener el formato YYYY-MM-DD.");
                } catch (Exception ex) {
                    mostrarError("Ocurrió un error: " + ex.getMessage());
                }
            }
        });
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar Orden");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                    controller.eliminarOrden(nroSerieArticulo);
                    mostrarOrdenes(controller.mostrarOrdenes());
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    mostrarError("Número de serie debe ser un número entero.");
                } catch (Exception ex) {
                    mostrarError("Ocurrió un error: " + ex.getMessage());
                }
            }
        });
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public void mostrarOrdenes(ArrayList<Orden> ordenes) {
        displayArea.setText("");
        for (Orden orden : ordenes) {
            displayArea.append("NroSerieArticulo: " + orden.getNroSerieArticulo() + ", Cantidad: " + orden.getCantidadAComprar() + ", Fecha: " + orden.getFecha() + "\n");
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarCampos() {
        nroSerieArticuloField.setText("");
        cantidadAComprarField.setText("");
        fechaField.setText("");
    }
}
