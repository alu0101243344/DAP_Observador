package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaCorreo extends JFrame {
    private JTextArea areaMensajes;
    private JPanel panelCheckboxes;
    private JTabbedPane pestanas;
    private Usuario usuarioPropietario;
    private List<Perfume> listaProductos;

    public VentanaCorreo(Usuario usuario, List<Perfume> productos) {
        this.usuarioPropietario = usuario;
        this.listaProductos = productos;

        setTitle("Usuario: " + usuario.getNombre());
        setSize(450, 350);
        setLayout(new BorderLayout());

        inicializarComponentes();

        setLocation((int)(Math.random() * 500) + 500, (int)(Math.random() * 500));
        setVisible(true);
    }

    private void inicializarComponentes() {
        pestanas = new JTabbedPane();

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMensajes.setBackground(new Color(250, 250, 240));
        JScrollPane scrollMensajes = new JScrollPane(areaMensajes);
        pestanas.addTab("Buzón", scrollMensajes);

        panelCheckboxes = new JPanel();
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
        panelCheckboxes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        generarCheckboxes();
        JScrollPane scrollSuscripciones = new JScrollPane(panelCheckboxes);
        pestanas.addTab("Gestionar Suscripciones", scrollSuscripciones);

        add(pestanas, BorderLayout.CENTER);
    }

    private void generarCheckboxes() {
        panelCheckboxes.add(new JLabel("Selecciona los productos que te interesan:"));
        panelCheckboxes.add(Box.createVerticalStrut(10));

        for (Perfume p : listaProductos) {
            JCheckBox check = new JCheckBox(p.getNombre() + " (" + p.getMarca() + ")");

            check.addActionListener(e -> {
                if (check.isSelected()) {
                    p.agregarObservador(usuarioPropietario);
                    agregarLogSistema("Te has suscrito a " + p.getNombre());
                } else {
                    p.eliminarObservador(usuarioPropietario);
                    agregarLogSistema("Te has dado de baja de " + p.getNombre());
                }
            });

            panelCheckboxes.add(check);
        }
    }

    public void recibirNotificacion(String producto, String mensaje) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String texto = String.format(" >> [%s] %s: %s\n\n", hora, producto, mensaje);
        areaMensajes.append(texto);

        pestanas.setSelectedIndex(0);
        toFront();
    }

    private void agregarLogSistema(String mensaje) {
        areaMensajes.append(" [SISTEMA]: " + mensaje + "\n\n");
    }
}