package com.tienda.modelo;

import com.tienda.interfaces.IObservador;
import com.tienda.interfaces.ISujeto;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public abstract class Producto implements ISujeto {
    protected String nombre;
    protected double precio;
    protected boolean enStock;
    protected ImageIcon imagen;
    protected List<IObservador> observadores;

    public Producto(String nombre, double precio, String nombreArchivoImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.enStock = false;
        this.observadores = new ArrayList<>();

        try {
            java.net.URL url = getClass().getResource("/com/tienda/recursos/" + nombreArchivoImagen);
            if (url != null) {
                ImageIcon original = new ImageIcon(url);
                java.awt.Image escalada = original.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                this.imagen = new ImageIcon(escalada);
            } else {
                this.imagen = new ImageIcon();
            }
        } catch (Exception e) {
            this.imagen = new ImageIcon();
        }
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
            observador.actualizar(this.nombre, mensaje, this.precio, this.imagen);
        }
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public boolean isEnStock() { return enStock; }
    public ImageIcon getImagen() { return imagen; }
}