package org.example.Control;

import org.example.Dao.OrdenDAO;
import org.example.Modelo.Articulo;
import org.example.Modelo.Gondola;
import org.example.Modelo.Orden;
import org.example.Vista.OrdenView;

import java.util.ArrayList;

public class OrdenController {
    private OrdenView view;
    private OrdenDAO ordenDAO;

    public OrdenController(OrdenView view) {
        this.view = view;
        this.ordenDAO = new OrdenDAO();
    }

    public OrdenController() {

    }

    public void setView(OrdenView view) {
        this.view = view;
    }

    public void crearOrden(int nroSerieArticulo, int cantidadAComprar, java.sql.Date fecha) {
        Orden orden = new Orden(nroSerieArticulo,cantidadAComprar,fecha);
        ordenDAO.crearOrden(orden);
        actualizarVista();
    }

    public void actualizarOrden( int nroSerieArticulo, int cantidadAComprar, java.sql.Date fecha) {
        Orden orden = ordenDAO.leerOrden(nroSerieArticulo);
        if (orden != null) {
            orden.setCantidadAComprar(cantidadAComprar);
            orden.setFecha(fecha);
            ordenDAO.crearOrden(orden);
            actualizarVista();
        }
    }

    public void eliminarOrden(int nroSerieArticulo) {
        Orden orden = ordenDAO.leerOrden(nroSerieArticulo);
        if (orden != null){
            ordenDAO.eliminarOrden(nroSerieArticulo);
            actualizarVista();
        }
    }

    public void mostrarOrdenes() {
        ArrayList<Orden> ordenes = ordenDAO.listarOrdenes();
        view.mostrarOrdenes(ordenes);
    }
    private void actualizarVista() {
        mostrarOrdenes();
    }
}