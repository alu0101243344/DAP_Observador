package com.tienda.modelo;

import com.tienda.interfaces.IObservador;
import com.tienda.interfaces.ISujeto;
import java.util.ArrayList;
import java.util.List;

public abstract class Producto implements ISujeto {
    protected String nombre;
    protected double precio;
    protected boolean enStock;
    protected List<IObservador> observadores;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.enStock = false;
        this.observadores = new ArrayList<>();
    }

    @Override
    public void agregarObservador(IObservador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (IObservador observador : observadores) {
            observador.actualizar(this.nombre, mensaje, this.precio);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEnStock() {
        return enStock;
    }
}