package com.tienda.modelo;

import com.tienda.interfaces.IObservador;
import com.tienda.vista.VentanaCorreo;
import javax.swing.ImageIcon;
import java.util.List;

public class Usuario implements IObservador {
    private String nombre;
    private String email;
    private VentanaCorreo ventanaPersonal;

    public Usuario(String nombre, String email, List<Perfume> listaProductosDisponibles) {
        this.nombre = nombre;
        this.email = email;
        this.ventanaPersonal = new VentanaCorreo(this, listaProductosDisponibles);
    }

    @Override
    public void actualizar(String producto, String mensaje, double precioActual, ImageIcon imagen) {
        ventanaPersonal.recibirNotificacion(producto, mensaje, imagen);
    }

    public String getNombre() { return nombre; }
}