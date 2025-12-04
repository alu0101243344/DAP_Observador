package com.tienda.modelo;

import com.tienda.interfaces.IObservador;

public class Usuario implements IObservador {
    private String nombre;
    private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public void actualizar(String producto, String mensaje, double precioActual) {
        System.out.println("LOG: " + nombre + " recibió aviso de " + producto + ": " + mensaje);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}