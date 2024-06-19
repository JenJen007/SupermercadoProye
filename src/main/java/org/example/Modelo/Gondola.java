package org.example.Modelo;
import java.util.ArrayList;

public class Gondola {
    //Atributos
    private int nroUbicacion;
    private String sector;
    private boolean disponible;
    private int espacioLibre;
    private boolean extremo;
    private boolean completo_10;
    private ArrayList<Articulo> articulos;

    //Constructor

    public Gondola(int nroUbicacion, String sector, boolean disponible, int espacioLibre, boolean extremo, boolean completo_10) {
        this.nroUbicacion = nroUbicacion;
        this.sector = sector;
        this.disponible = disponible;
        this.espacioLibre = espacioLibre;
        this.extremo = extremo;
        this.completo_10 = completo_10;
        this.articulos = new ArrayList<>();
    }

    //Getter&Setter
    public int getNroUbicacion() {
        return nroUbicacion;
    }

    public void setNroUbicacion(int nroUbicacion) {
        this.nroUbicacion = nroUbicacion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getEspacioLibre() {
        return espacioLibre;
    }

    public void setEspacioLibre(int espacioLibre) {
        this.espacioLibre = espacioLibre;
    }

    public boolean isExtremo() {
        return extremo;
    }

    public void setExtremo(boolean extremo) {
        this.extremo = extremo;
    }

    public boolean isCompleto_10() {
        return completo_10;
    }

    public void setCompleto_10(boolean completo_10) {
        this.completo_10 = completo_10;
    }

    public ArrayList<Articulo> getArticulo() {
        return articulos;
    }

    public void setArticulo(ArrayList<Articulo> articulo) {
        this.articulos = articulo;
    }

   /*/Crear métodos correspoondientes a la lógica
    public void agregarArticulo(Articulo articulo) {
        if (this.espacioLibre > 0) {
            this.articulos.add(articulo);
            this.espacioLibre--;
        }
    }*/
}
