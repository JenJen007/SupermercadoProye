package org.example.Modelo;

import java.util.ArrayList;

public class Articulo {
    //Atributos
    private int nroSerie;
    private int stockMinimo;
    private boolean comprar;
    private ArrayList<Orden> orden = new ArrayList<>();
    private ArrayList<Gondola>gondolas = new ArrayList<>();

    //Crear constructor

    public Articulo(int nroSerie, int stockMinimo, boolean comprar) {
        this.nroSerie = nroSerie;
        this.stockMinimo = stockMinimo;
        this.comprar = comprar;
    }

    //Getter&Setter

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean isComprar() {
        return comprar;
    }

    public void setComprar(boolean comprar) {
        this.comprar = comprar;
    }

    public ArrayList<Orden> getOrden() {
        return orden;
    }

    public void setOrden(ArrayList<Orden> orden) {
        this.orden = orden;
    }

    public ArrayList<Gondola> getGondolas() {
        return gondolas;
    }

    public void setGondolas(ArrayList<Gondola> gondolas) {
        this.gondolas = gondolas;
    }

    //Crear métodos correspondientes a la lógica
    public void verificarStock() {
        if (this.stockMinimo > this.gondolas.size()) {
            this.comprar = true;
        } else {
            this.comprar = false;
        }
    }
}
