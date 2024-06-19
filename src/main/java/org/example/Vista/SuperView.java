package org.example.Vista;

import org.example.Control.ArticuloController;
import org.example.Control.GondolaController;
import org.example.Control.OrdenController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperView extends JFrame {
    private ArticuloController articuloController;
    private GondolaController gondolaController;
    private OrdenController ordenController;

    public SuperView(ArticuloController articuloController, GondolaController gondolaController, OrdenController ordenController) {
        this.articuloController = articuloController;
        this.gondolaController = gondolaController;
        this.ordenController = ordenController;

        setTitle("Supermercado EcoExpress");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");

        JMenuItem menuItemArticulo = new JMenuItem("Articulo", new ImageIcon("path_to_articulo_icon"));
        menuItemArticulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ArticuloView(articuloController);
            }
        });

        JMenuItem menuItemGondola = new JMenuItem("Gondola", new ImageIcon("path_to_gondola_icon"));
        menuItemGondola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GondolaView(gondolaController);
            }
        });

        JMenuItem menuItemOrden = new JMenuItem("Orden", new ImageIcon("path_to_orden_icon"));
        menuItemOrden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrdenView(ordenController);
            }
        });

        JMenuItem menuItemSalir = new JMenuItem("Salir", new ImageIcon("path_to_salir_icon"));
        menuItemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(menuItemArticulo);
        menu.add(menuItemGondola);
        menu.add(menuItemOrden);
        menu.add(menuItemSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Crear los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton articuloButton = new JButton("Articulo");
        articuloButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ArticuloView(articuloController);
            }
        });
        buttonPanel.add(articuloButton);

        JButton gondolaButton = new JButton("Gondola");
        gondolaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GondolaView(gondolaController);
            }
        });
        buttonPanel.add(gondolaButton);

        JButton ordenButton = new JButton("Orden");
        ordenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new OrdenView(ordenController);
            }
        });
        buttonPanel.add(ordenButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }


}

