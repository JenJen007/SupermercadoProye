package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL =  "jdbc:mysql://localhost:3306/supermercado";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static void crearTablas(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            //Crear tablas Articulos
            String createArticulosTable = "CREATE TABLE IF NOT EXISTS articulos (" +
                    "nroSerie INT PRIMARY KEY, " +
                    "stockMinimo INT, " +
                    "comprar BOOLEAN)";
            stmt.executeUpdate(createArticulosTable);

            // Crear tabla Gondolas
            String createGondolasTable = "CREATE TABLE IF NOT EXISTS gondolas (" +
                    "nroUbicacion INT PRIMARY KEY, " +
                    "sector VARCHAR(100), " +
                    "disponible BOOLEAN, " +
                    "espacioLibre INT, " +
                    "extremo BOOLEAN, " +
                    "completo_10 BOOLEAN)";
            stmt.executeUpdate(createGondolasTable);

            // Crear tabla Ordenes
            String createOrdenesTable = "CREATE TABLE IF NOT EXISTS ordenes (" +
                    "nroSerieArticulo INT PRIMARY KEY, " +
                    "cantidadAComprar INT, " +
                    "fecha DATE, " +
                    "FOREIGN KEY (nroSerieArticulo) REFERENCES articulos(nroSerie))";
            stmt.executeUpdate(createOrdenesTable);

            // Crear tabla Articulo_Gondola (relación muchos a muchos)
            String createArticuloGondolaTable = "CREATE TABLE IF NOT EXISTS articulo_gondola (" +
                    "articuloNroSerie INT, " +
                    "gondolaNroUbicacion INT, " +
                    "PRIMARY KEY (articuloNroSerie, gondolaNroUbicacion), " +
                    "FOREIGN KEY (articuloNroSerie) REFERENCES articulos(nroSerie), " +
                    "FOREIGN KEY (gondolaNroUbicacion) REFERENCES gondolas(nroUbicacion))";
            stmt.executeUpdate(createArticuloGondolaTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        }



