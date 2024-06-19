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

    public void setController(OrdenController controller) {
        this.controller = controller;
    }

    public OrdenView(OrdenController controller) {
        this.controller = controller;

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
                int nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                int cantidadAComprar = Integer.parseInt(cantidadAComprarField.getText());
                java.sql.Date fecha = java.sql.Date.valueOf(fechaField.getText());
                controller.crearOrden(nroSerieArticulo, cantidadAComprar, fecha);
            }
        });
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Actualizar Orden");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                int cantidadAComprar = Integer.parseInt(cantidadAComprarField.getText());
                java.sql.Date fecha = java.sql.Date.valueOf(fechaField.getText());
                controller.actualizarOrden(nroSerieArticulo, cantidadAComprar,fecha);
            }
        });
        inputPanel.add(updateButton);
        JButton deleteButton = new JButton("Eliminar Orden");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int  nroSerieArticulo = Integer.parseInt(nroSerieArticuloField.getText());
                controller.eliminarOrden(nroSerieArticulo);
            }
        });
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);

        controller.mostrarOrdenes();
    }

    public void mostrarOrdenes(ArrayList<Orden> ordenes) {
        displayArea.setText("");
        for (Orden orden : ordenes) {
            displayArea.append( "NroSerieArticulo: " + orden.getNroSerieArticulo() + ", Cantidad: " + orden.getCantidadAComprar() + ", Fecha: " + orden.getFecha() + "\n");
        }
    }
}
