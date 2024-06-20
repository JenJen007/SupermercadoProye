package org.example.Vista;

import org.example.Control.ArticuloController;
import org.example.Modelo.Articulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArticuloView extends JFrame {
    private ArticuloController controller;
    private JTextField nroSerieField, stockMinimoField;
    private JCheckBox comprarCheckBox;
    private JTextArea displayArea;

    public ArticuloView(ArticuloController controller) {
        this.controller = controller;
        initialize();
        controller.mostrarArticulos();
    }

    private void initialize() {
        setTitle("Gestión de Artículos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Número de Serie:"));
        nroSerieField = new JTextField();
        inputPanel.add(nroSerieField);

        inputPanel.add(new JLabel("Stock Mínimo:"));
        stockMinimoField = new JTextField();
        inputPanel.add(stockMinimoField);

        inputPanel.add(new JLabel("Comprar:"));
        comprarCheckBox = new JCheckBox();
        inputPanel.add(comprarCheckBox);

        JButton addButton = new JButton("Agregar Artículo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nroSerieText = nroSerieField.getText();
                    String stockMinimoText = stockMinimoField.getText();

                    if (nroSerieText.isEmpty() || stockMinimoText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroSerie = Integer.parseInt(nroSerieText);
                    int stockMinimo = Integer.parseInt(stockMinimoText);
                    boolean comprar = comprarCheckBox.isSelected();

                    controller.crearArticulo(nroSerie, stockMinimo, comprar);
                    JOptionPane.showMessageDialog(null, "Artículo agregado exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Serie y Stock Mínimo deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Actualizar Artículo");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nroSerieText = nroSerieField.getText();
                    String stockMinimoText = stockMinimoField.getText();

                    if (nroSerieText.isEmpty() || stockMinimoText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroSerie = Integer.parseInt(nroSerieText);
                    int stockMinimo = Integer.parseInt(stockMinimoText);
                    boolean comprar = comprarCheckBox.isSelected();

                    controller.actualizarArticulo(nroSerie, stockMinimo, comprar);
                    JOptionPane.showMessageDialog(null, "Artículo actualizado exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Serie y Stock Mínimo deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar Artículo");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nroSerieText = nroSerieField.getText();

                    if (nroSerieText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El campo de Número de Serie debe estar lleno", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int nroSerie = Integer.parseInt(nroSerieText);

                    controller.eliminarArticulo(nroSerie);
                    JOptionPane.showMessageDialog(null, "Artículo eliminado exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de Serie debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
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
    }

    public void mostrarArticulos(ArrayList<Articulo> articulos) {
        displayArea.setText("");
        for (Articulo articulo : articulos) {
            displayArea.append("NroSerie: " + articulo.getNroSerie() + ", Stock Min: " + articulo.getStockMinimo() + ", Comprar: " + articulo.isComprar() + "\n");
        }
    }
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
