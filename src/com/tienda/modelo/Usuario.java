package com.tienda.modelo;

import com.tienda.interfaces.IObservador;
import com.tienda.vista.VentanaCorreo;

public class Usuario implements IObservador {
    private String nombre;
    private String email;
    private VentanaCorreo ventanaPersonal;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.ventanaPersonal = new VentanaCorreo(nombre, email);
    }

    @Override
    public void actualizar(String producto, String mensaje, double precioActual) {
        ventanaPersonal.recibirNotificacion(producto, mensaje);
    }

    public String getNombre() {
        return nombre;
    }
}