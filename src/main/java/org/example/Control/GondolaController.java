package org.example.Control;

import org.example.Dao.GondolaDAO;
import org.example.Modelo.Gondola;
import org.example.Vista.GondolaView;

import java.util.ArrayList;

public class GondolaController {
    private GondolaView view;
    private GondolaDAO gondolaDAO;

    public GondolaController(GondolaView view) {
        this.view = view;
        this.gondolaDAO = new GondolaDAO();
    }

    public GondolaController() {

    }

    public void setView(GondolaView view) {
        this.view = view;
    }

    public void crearGondola(int nroUbicacion, String sector, boolean disponible, int espacioLibre, boolean extremo, boolean completo_10) {
        Gondola gondola = new Gondola(nroUbicacion, sector, disponible, espacioLibre, extremo, completo_10);
        gondolaDAO.crearGondola(gondola);
        actualizarVista();
    }

    public void actualizarGondola(int nroUbicacion, String sector, boolean disponible, int espacioLibre, boolean extremo, boolean completo_10) {
        Gondola gondola = gondolaDAO.leerGondola(nroUbicacion);
        if (gondola != null){
            gondola.setSector(sector);
            gondola.setDisponible(disponible);
            gondola.setEspacioLibre(espacioLibre);
            gondola.setExtremo(extremo);
            gondola.setCompleto_10(completo_10);
            gondolaDAO.actualizarGondola(gondola);
            actualizarVista();
        }

    }
    public void eliminarGondola(int nroUbicacion) {
        Gondola gondola = gondolaDAO.leerGondola(nroUbicacion);
        if (gondola != null) {
            gondolaDAO.eliminarGondola(nroUbicacion);
            actualizarVista();
        }
    }

    public void mostrarGondolas() {
        ArrayList<Gondola> gondolas = gondolaDAO.listarGondolas();
        view.mostrarGondolas(gondolas);
    }
    private void actualizarVista() {
        mostrarGondolas();
    }
}