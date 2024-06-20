package org.example;

import org.example.Control.ArticuloController;
import org.example.Control.GondolaController;
import org.example.Control.OrdenController;
import org.example.Vista.ArticuloView;
import org.example.Vista.GondolaView;
import org.example.Vista.OrdenView;
import org.example.Vista.SuperView;

public class Main {
    public static void main(String[] args) {
        // Crear las tablas en la base de datos
        DatabaseConnection.crearTablas();


        // Crear instancias de los controladores
        ArticuloController articuloController = new ArticuloController();
        GondolaController gondolaController = new GondolaController();
        OrdenController ordenController = new OrdenController();

        // Crear y mostrar la vista principal
        new SuperView(articuloController, gondolaController, ordenController);



    }
}
