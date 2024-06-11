package org.example.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Orden {
    //Atributos
    private int nroSerieArticulo;
    private int cantidadAComprar;
    private Date fecha;
    private ArrayList<Articulo> articulo = new ArrayList<>();

    //Constructor

    public Orden(int nroSerieArticulo, int cantidadAComprar, Date fecha) {
        this.nroSerieArticulo = nroSerieArticulo;
        this.cantidadAComprar = cantidadAComprar;
        this.fecha = fecha;
    }

    //Getter&Setter

    //Crear métodos correspondientes a la lógica
    public void generarOrden(Articulo articulo, int cantidad) {
        this.articulo.add(articulo);
        this.cantidadAComprar = cantidad;
        this.fecha = new Date();
    }
}
