package org.example.Modelo;
import java.util.ArrayList;
import java.util.Date;

public class Orden {
    //Atributos
    private int nroSerieArticulo;
    private int cantidadAComprar;
    private Date fecha;
    private ArrayList<Articulo> articulos = new ArrayList<>();

    //Constructor
    public Orden(int nroSerieArticulo, int cantidadAComprar, Date fecha) {
        this.nroSerieArticulo = nroSerieArticulo;
        this.cantidadAComprar = cantidadAComprar;
        this.fecha = fecha;
    }

    //Getter&Setter
    public int getNroSerieArticulo() {
        return nroSerieArticulo;
    }

    public void setNroSerieArticulo(int nroSerieArticulo) {
        this.nroSerieArticulo = nroSerieArticulo;
    }

    public int getCantidadAComprar() {
        return cantidadAComprar;
    }

    public void setCantidadAComprar(int cantidadAComprar) {
        this.cantidadAComprar = cantidadAComprar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Articulo> getArticulo() {
        return articulos;
    }

    public void setArticulo(ArrayList<Articulo> articulo) {
        this.articulos = articulo;
    }

    //Crear métodos correspondientes a la lógica
   /* public void generarOrden(Articulo articulo, int cantidad) {
        this.articulos.add(articulo);
        this.cantidadAComprar = cantidad;
        this.fecha = new Date();
    }*/
}
