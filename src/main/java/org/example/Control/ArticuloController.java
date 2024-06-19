package org.example.Control;

import org.example.Dao.ArticuloDAO;
import org.example.Modelo.Articulo;
import org.example.Vista.ArticuloView;

import java.util.ArrayList;

public class ArticuloController {
    private ArticuloView view;
    private ArticuloDAO articuloDAO;

    public ArticuloController(ArticuloView view) {
        this.view = view;
        this.articuloDAO = new ArticuloDAO();
    }

    public ArticuloController() {

    }

    public void setView(ArticuloView view) {
        this.view = view;
    }

    public void crearArticulo(int nroSerie, int stockMinimo, boolean comprar) {
        Articulo articulo = new Articulo(nroSerie, stockMinimo, comprar);
        articuloDAO.crearArticulo(articulo);
        actualizarVista();
    }

    public void actualizarArticulo(int nroSerie, int stockMinimo, boolean comprar) {
        Articulo articulo = articuloDAO.leerArticulo(nroSerie);
        if (articulo != null) {
            articulo.setStockMinimo(stockMinimo);
            articulo.setComprar(comprar);
            articuloDAO.actualizarArticulo(articulo);
            actualizarVista();
        }
    }

    public void eliminarArticulo(int nroSerie) {
        Articulo articulo = articuloDAO.leerArticulo(nroSerie);
        if (articulo != null) {
            articuloDAO.eliminarArticulo(nroSerie);
            actualizarVista();
        }
    }

    public void mostrarArticulos() {
        ArrayList<Articulo> articulos = articuloDAO.listarArticulos();
        view.mostrarArticulos(articulos);
    }

    private void actualizarVista() {
        mostrarArticulos();
    }
}
