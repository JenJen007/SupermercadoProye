
package org.example.Dao;

import org.example.DatabaseConnection;
import org.example.Modelo.Articulo;

import java.sql.*;
import java.util.ArrayList;

public class ArticuloDAO {

    // Crear Artículo
    public void crearArticulo(Articulo articulo) {
        String query = "INSERT INTO articulos (nroSerie, stockMinimo, comprar) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, articulo.getNroSerie());
            stmt.setInt(2, articulo.getStockMinimo());
            stmt.setBoolean(3, articulo.isComprar());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer Artículo
    public  Articulo leerArticulo(int nroSerie) {
        Articulo articulo = null;
        String query = "SELECT * FROM articulos WHERE nroSerie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nroSerie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    articulo = new Articulo(rs.getInt("nroSerie"), rs.getInt("stockMinimo"), rs.getBoolean("comprar"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulo;
    }

    // Actualizar Artículo
    public void actualizarArticulo(Articulo articulo) {
        String query = "UPDATE articulos SET stockMinimo = ?, comprar = ? WHERE nroSerie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, articulo.getStockMinimo());
            stmt.setBoolean(2, articulo.isComprar());
            stmt.setInt(3, articulo.getNroSerie());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar Artículo
    public void eliminarArticulo(int nroSerie) {
        String query = "DELETE FROM articulos WHERE nroSerie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nroSerie);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos los artículos
    public  ArrayList<Articulo> listarArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM articulos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Articulo articulo = new Articulo(rs.getInt("nroSerie"), rs.getInt("stockMinimo"), rs.getBoolean("comprar"));
                articulos.add(articulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulos;
    }
    // Buscar artículos por stock mínimo
    public  ArrayList<Articulo> buscarPorStockMinimo(int stockMinimo) {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM articulos WHERE stockMinimo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, stockMinimo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo(rs.getInt("nroSerie"), rs.getInt("stockMinimo"), rs.getBoolean("comprar"));
                    articulos.add(articulo);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulos;
    }

    // Actualizar stock
    public void actualizarStock(int nuevoStock,Articulo articulo) {
        String query = "UPDATE articulos SET stockMinimo = ? WHERE nroSerie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, articulo.getNroSerie());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar artículos en una góndola específica
    public  ArrayList<Articulo> listarArticulosEnGondola(int nroUbicacion) {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT a.* FROM articulos a INNER JOIN articulo_gondola ag ON a.nroSerie = ag.articuloNroSerie WHERE ag.gondolaNroUbicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nroUbicacion);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Articulo articulo = new Articulo(rs.getInt("nroSerie"), rs.getInt("stockMinimo"), rs.getBoolean("comprar"));
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articulos;
    }
}
