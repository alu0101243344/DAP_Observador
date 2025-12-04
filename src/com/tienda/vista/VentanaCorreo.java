package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaCorreo extends JFrame {
    private JPanel panelBuzon; // Cambiamos TextArea por Panel
    private JPanel panelCheckboxes;
    private JTabbedPane pestanas;
    private Usuario usuarioPropietario;
    private List<Perfume> listaProductos;

    public VentanaCorreo(Usuario usuario, List<Perfume> productos) {
        this.usuarioPropietario = usuario;
        this.listaProductos = productos;

        setTitle("Usuario: " + usuario.getNombre());
        setSize(400, 500); // Un poco más alta para ver bien las fotos
        setLayout(new BorderLayout());

        inicializarComponentes();

        setLocation((int)(Math.random() * 600) + 400, (int)(Math.random() * 400));
        setVisible(true);
    }

    private void inicializarComponentes() {
        pestanas = new JTabbedPane();

        // Pestaña 1: Buzón visual
        panelBuzon = new JPanel();
        panelBuzon.setLayout(new BoxLayout(panelBuzon, BoxLayout.Y_AXIS));
        panelBuzon.setBackground(new Color(240, 240, 245));

        JScrollPane scrollBuzon = new JScrollPane(panelBuzon);
        scrollBuzon.getVerticalScrollBar().setUnitIncrement(16);
        pestanas.addTab("Notificaciones", scrollBuzon);

        // Pestaña 2: Suscripciones
        panelCheckboxes = new JPanel();
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
        panelCheckboxes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        generarCheckboxes();
        JScrollPane scrollSuscripciones = new JScrollPane(panelCheckboxes);
        pestanas.addTab("Configuración", scrollSuscripciones);

        add(pestanas, BorderLayout.CENTER);
    }

    private void generarCheckboxes() {
        panelCheckboxes.add(new JLabel("Suscripciones activas:"));
        panelCheckboxes.add(Box.createVerticalStrut(10));

        for (Perfume p : listaProductos) {
            JCheckBox check = new JCheckBox(p.getNombre());
            check.addActionListener(e -> {
                if (check.isSelected()) {
                    p.agregarObservador(usuarioPropietario);
                } else {
                    p.eliminarObservador(usuarioPropietario);
                }
            });
            panelCheckboxes.add(check);
        }
    }

    // Nuevo método que recibe la imagen y crea una "tarjeta" visual
    public void recibirNotificacion(String producto, String mensaje, ImageIcon imagen) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.GRAY, 1)
        ));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setMaximumSize(new Dimension(360, 80));

        // 1. Imagen a la izquierda
        JLabel lblImg = new JLabel(imagen);

        // 2. Texto al centro
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String html = String.format("<html><font color='gray'>[%s]</font> <b>%s</b><br>%s</html>",
                hora, producto, mensaje);
        JLabel lblTexto = new JLabel(html);

        tarjeta.add(lblImg, BorderLayout.WEST);
        tarjeta.add(lblTexto, BorderLayout.CENTER);

        // Añadir al principio del panel (como un feed de redes sociales)
        panelBuzon.add(tarjeta, 0);
        panelBuzon.revalidate();
        panelBuzon.repaint();

        pestanas.setSelectedIndex(0);
        toFront();
    }
}