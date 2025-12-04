package com.tienda.modelo;

public class Perfume extends Producto {
    private String marca;

    public Perfume(String nombre, String marca, double precio, String urlImagen) {
        super(nombre, precio, urlImagen);
        this.marca = marca;
    }

    public void setStock(boolean nuevoStock) {
        if (this.enStock != nuevoStock) {
            this.enStock = nuevoStock;
            if (this.enStock) {
                notificarObservadores("Stock disponible nuevamente");
            } else {
                notificarObservadores("Producto agotado temporalmente");
            }
        }
    }

    public void setPrecio(double nuevoPrecio) {
        if (nuevoPrecio != this.precio) {
            double precioAnterior = this.precio;
            double porcentaje = ((nuevoPrecio - precioAnterior) / precioAnterior) * 100;

            this.precio = nuevoPrecio;

            String signo = (porcentaje > 0) ? "+" : "";

            String mensaje = String.format("Precio: $%.2f -> $%.2f (%s%.2f%%)",
                    precioAnterior, this.precio, signo, porcentaje);

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