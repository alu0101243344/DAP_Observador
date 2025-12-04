package com.tienda.vista;

import com.tienda.interfaces.IObservador;
import javax.swing.JTextArea;
import java.time.LocalTime;

public class UsuarioLog implements IObservador {
    private JTextArea areaTexto;

    public UsuarioLog(JTextArea areaTexto) {
        this.areaTexto = areaTexto;
    }

    @Override
    public void actualizar(String producto, String mensaje, double precioActual) {
        String log = String.format("[%s] NOTIFICACIÓN: %s - %s (Precio: $%.2f)\n",
                LocalTime.now(), producto, mensaje, precioActual);
        areaTexto.append(log);
    }
}