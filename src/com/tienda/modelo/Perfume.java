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
                notificarObservadores("Stock disponible nuevamente");
            }
        }
    }

    public void setPrecio(double nuevoPrecio) {
        if (nuevoPrecio < this.precio) {
            this.precio = nuevoPrecio;
            notificarObservadores("¡Bajada de precio!");
        } else if (nuevoPrecio > this.precio) {
            this.precio = nuevoPrecio;
            notificarObservadores("El precio ha subido");
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