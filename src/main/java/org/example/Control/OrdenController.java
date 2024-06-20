package org.example.Control;

import org.example.Dao.OrdenDAO;
import org.example.Modelo.Orden;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdenController {
    private OrdenDAO ordenDAO;

    public OrdenController() {
        this.ordenDAO = new OrdenDAO();
    }

    public void crearOrden(int nroSerieArticulo, int cantidadAComprar, Date fecha) {
        try {
            validarOrden(nroSerieArticulo, cantidadAComprar, fecha);
            Orden orden = new Orden(nroSerieArticulo, cantidadAComprar, fecha);
            ordenDAO.crearOrden(orden);
            actualizarVista();
            System.out.println("Orden creada exitosamente.");
        } catch (IllegalArgumentException ex) {
            System.err.println("Error al crear la orden: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Ocurrió un error inesperado: " + ex.getMessage());
        }
    }

    public void actualizarOrden(int nroSerieArticulo, int cantidadAComprar, Date fecha) {
        try {
            validarOrden(nroSerieArticulo, cantidadAComprar, fecha);
            Orden orden = ordenDAO.leerOrden(nroSerieArticulo);
            if (orden != null) {
                orden.setCantidadAComprar(cantidadAComprar);
                orden.setFecha(fecha);
                ordenDAO.actualizarOrden(orden);
                actualizarVista();
                System.out.println("Orden actualizada exitosamente.");
            } else {
                System.err.println("Orden no encontrada con el número de serie: " + nroSerieArticulo);
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("Error al actualizar la orden: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Ocurrió un error inesperado: " + ex.getMessage());
        }
    }

    public void eliminarOrden(int nroSerieArticulo) {
        try {
            if (nroSerieArticulo <= 0) {
                throw new IllegalArgumentException("Número de serie del artículo debe ser positivo.");
            }
            Orden orden = ordenDAO.leerOrden(nroSerieArticulo);
            if (orden != null) {
                ordenDAO.eliminarOrden(nroSerieArticulo);
                actualizarVista();
                System.out.println("Orden eliminada exitosamente.");
            } else {
                System.err.println("Orden no encontrada con el número de serie: " + nroSerieArticulo);
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("Error al eliminar la orden: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Ocurrió un error inesperado: " + ex.getMessage());
        }
    }

    public ArrayList<Orden> mostrarOrdenes() {
        return ordenDAO.listarOrdenes();
    }

    private void actualizarVista() {
        mostrarOrdenes();
    }

    private void validarOrden(int nroSerieArticulo, int cantidadAComprar, Date fecha) {
        if (nroSerieArticulo <= 0) {
            throw new IllegalArgumentException("Número de serie del artículo debe ser positivo.");
        }
        if (cantidadAComprar <= 0) {
            throw new IllegalArgumentException("Cantidad a comprar debe ser positiva.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
    }
}
