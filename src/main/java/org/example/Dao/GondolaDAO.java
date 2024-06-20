
package org.example.Dao;

import org.example.DatabaseConnection;
import org.example.Modelo.Gondola;

import java.sql.*;
import java.util.ArrayList;

public class GondolaDAO {
    // Crear Góndola
    public void crearGondola(Gondola gondola) {
        String query = "INSERT INTO gondolas (nroUbicacion, sector, disponible, espacioLibre, extremo, completo_10) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, gondola.getNroUbicacion());
            stmt.setString(2, gondola.getSector());
            stmt.setBoolean(3, gondola.isDisponible());
            stmt.setInt(4, gondola.getEspacioLibre());
            stmt.setBoolean(5, gondola.isExtremo());
            stmt.setBoolean(6, gondola.isCompleto_10());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer Góndola
    public  Gondola leerGondola(int nroUbicacion) {
        Gondola gondola = null;
        String query = "SELECT * FROM gondolas WHERE nroUbicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nroUbicacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    gondola = new Gondola(rs.getInt("nroUbicacion"), rs.getString("sector"), rs.getBoolean("disponible"), rs.getInt("espacioLibre"), rs.getBoolean("extremo"), rs.getBoolean("completo_10"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gondola;
    }

    // Actualizar Góndola
    public void actualizarGondola(Gondola gondola) {
        String query = "UPDATE gondolas SET  sector = ?, disponible = ?, espacioLibre = ?, extremo = ?, completo_10 = ? WHERE  nroUbicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gondola.getSector());
            stmt.setBoolean(2, gondola.isDisponible());
            stmt.setInt(3, gondola.getEspacioLibre());
            stmt.setBoolean(4, gondola.isExtremo());
            stmt.setBoolean(5, gondola.isCompleto_10());
            stmt.setInt(6, gondola.getNroUbicacion());
            stmt.executeUpdate();
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar Góndola
    public void eliminarGondola(int nroUbicacion) {
        String query = "DELETE FROM gondolas WHERE nroUbicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nroUbicacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas las góndolas
    public  ArrayList<Gondola> listarGondolas() {
        ArrayList<Gondola> gondolas = new ArrayList<>();
        String query = "SELECT * FROM gondolas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Gondola gondola = new Gondola(rs.getInt("nroUbicacion"), rs.getString("sector"), rs.getBoolean("disponible"), rs.getInt("espacioLibre"), rs.getBoolean("extremo"), rs.getBoolean("completo_10"));
                gondolas.add(gondola);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gondolas;
    }
    // Buscar góndolas disponibles
    public  ArrayList<Gondola> buscarGondolasDisponibles() {
        ArrayList<Gondola> gondolas = new ArrayList<>();
        String query = "SELECT * FROM gondolas WHERE disponible = true";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Gondola gondola = new Gondola(rs.getInt("nroUbicacion"), rs.getString("sector"), rs.getBoolean("disponible"), rs.getInt("espacioLibre"), rs.getBoolean("extremo"), rs.getBoolean("completo_10"));
                gondolas.add(gondola);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gondolas;
    }

    // Actualizar disponibilidad
    public void actualizarDisponibilidad(boolean nuevaDisponibilidad,Gondola gondola) {
        String query = "UPDATE gondolas SET disponible = ? WHERE nroUbicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, nuevaDisponibilidad);
            stmt.setInt(2, gondola.getNroUbicacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar góndolas por sector
    public  ArrayList<Gondola> listarGondolasPorSector(String sector) {
        ArrayList<Gondola> gondolas = new ArrayList<>();
        String query = "SELECT * FROM gondolas WHERE sector = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sector);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Gondola gondola = new Gondola(rs.getInt("nroUbicacion"), rs.getString("sector"), rs.getBoolean("disponible"), rs.getInt("espacioLibre"), rs.getBoolean("extremo"), rs.getBoolean("completo_10"));
                    gondolas.add(gondola);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gondolas;
    }
}
