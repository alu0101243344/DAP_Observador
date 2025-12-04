package com.tienda.interfaces;

public interface ISujeto {
    void agregarObservador(IObservador observador);
    void eliminarObservador(IObservador observador);
    void notificarObservadores(String mensaje);
}