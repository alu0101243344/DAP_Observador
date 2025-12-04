package com.tienda.vista;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VentanaCorreo extends JFrame {
    private JTextArea areaMensajes;
    private String nombreUsuario;

    public VentanaCorreo(String nombreUsuario, String email) {
        this.nombreUsuario = nombreUsuario;
        setTitle("Bandeja de Entrada - " + nombreUsuario + " [" + email + "]");
        setSize(400, 300);
        setLayout(new BorderLayout());

        inicializarComponentes();

        // Ubicamos las ventanas en posiciones aleatorias para que no se solapen todas
        setLocation((int)(Math.random() * 500), (int)(Math.random() * 500));
        setVisible(true);
    }

    private void inicializarComponentes() {
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMensajes.setBackground(new Color(245, 245, 220));

        JScrollPane scroll = new JScrollPane(areaMensajes);
        scroll.setBorder(BorderFactory.createTitledBorder("Notificaciones Recibidas"));

        add(scroll, BorderLayout.CENTER);
    }

    public void recibirNotificacion(String producto, String mensaje) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String formato = String.format("[%s] %s:\n   >>> %s\n\n", hora, producto, mensaje);
        areaMensajes.append(formato);

        toFront();
    }
}