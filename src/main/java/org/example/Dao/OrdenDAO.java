package org.example.Dao;

import org.example.DatabaseConnection;
import org.example.Modelo.Orden;

import java.sql.*;
import java.util.ArrayList;

public class OrdenDAO {
    // Crear Orden
    public void crearOrden(Orden orden) {
        String query = "INSERT INTO ordenes (nroSerieArticulo, cantidadAComprar, fecha) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, orden.getNroSerieArticulo());
                stmt.setInt(2, orden.getCantidadAComprar());
                stmt.setDate(3, new java.sql.Date(orden.getFecha().getTime()));
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer Orden
    public  Orden leerOrden(int nroSerieArticulo) {
        Orden orden = null;
        String query = "SELECT * FROM ordenes WHERE nroSerieArticulo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nroSerieArticulo);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        orden = new Orden(rs.getInt("nroSerieArticulo"), rs.getInt("cantidadAComprar"), rs.getDate("fecha"));
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orden;
    }

    // Actualizar Orden
    public void actualizarOrden(Orden orden) {
        String query = "UPDATE ordenes SET  cantidadAComprar = ?, fecha = ? WHERE nroSerieArticulo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, orden.getCantidadAComprar());
                stmt.setDate(2, new java.sql.Date(orden.getFecha().getTime()));
                stmt.setInt(3,orden.getNroSerieArticulo());

                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar Orden
    public void eliminarOrden(int nroSerieArticulo) {
        String query = "DELETE FROM ordenes WHERE nroSerieArticulo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nroSerieArticulo);
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas las órdenes
    public  ArrayList<Orden> listarOrdenes() {
        ArrayList<Orden> ordenes = new ArrayList<>();
        String query = "SELECT * FROM ordenes";
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Orden orden = new Orden( rs.getInt("nroSerieArticulo"), rs.getInt("cantidadAComprar"), rs.getDate("fecha"));
                    ordenes.add(orden);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordenes;
    }
    // Buscar órdenes por fecha
    public  ArrayList<Orden> buscarOrdenesPorFecha(java.util.Date fecha) {
        ArrayList<Orden> ordenes = new ArrayList<>();
        String query = "SELECT * FROM ordenes WHERE fecha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDate(1, new java.sql.Date(fecha.getTime()));
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Orden orden = new Orden(rs.getInt("nroSerieArticulo"), rs.getInt("cantidadAComprar"), rs.getDate("fecha"));
                        ordenes.add(orden);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordenes;
    }

    // Actualizar cantidad a comprar
    public void actualizarCantidadAComprar(int nuevaCantidad,Orden orden) {
        String query = "UPDATE ordenes SET cantidadAComprar = ? WHERE nroSerieArticulo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nuevaCantidad);
                stmt.setInt(2, orden.getNroSerieArticulo());
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar órdenes por artículo
    public  ArrayList<Orden> listarOrdenesPorArticulo(int nroSerieArticulo) {
        ArrayList<Orden> ordenes = new ArrayList<>();
        String query = "SELECT * FROM ordenes WHERE nroSerieArticulo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nroSerieArticulo);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Orden orden = new Orden(rs.getInt("nroSerieArticulo"), rs.getInt("cantidadAComprar"), rs.getDate("fecha"));
                        ordenes.add(orden);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordenes;
    }
}
