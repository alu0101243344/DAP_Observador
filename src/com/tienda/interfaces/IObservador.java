package com.tienda.interfaces;

import javax.swing.ImageIcon;

public interface IObservador {
    void actualizar(String producto, String mensaje, double precioActual, ImageIcon imagen);
}