package com.tienda.modelo;

public class Perfume extends Producto {
    private String marca;

    public Perfume(String nombre, String marca, double precio) {
        super(nombre, precio);
        this.marca = marca;
    }

    public void setStock(boolean nuevoStock) {
        if (this.enStock != nuevoStock) {
            this.enStock = nuevoStock;
            if (this.enStock) {
                notificarObservadores("Stock disponible. ¡Corre que se acaba!");
            } else {
                notificarObservadores("Producto agotado temporalmente.");
            }
        }
    }

    public void setPrecio(double nuevoPrecio) {
        if (nuevoPrecio != this.precio) {
            double variacion = ((nuevoPrecio - this.precio) / this.precio) * 100;
            this.precio = nuevoPrecio;

            String mensaje = String.format("Nuevo precio: $%.2f (Variación: %.1f%%)", nuevoPrecio, variacion);
            notificarObservadores(mensaje);
        }
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return nombre + " - " + marca + " ($" + precio + ")";
    }
}