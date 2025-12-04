package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VentanaCorreo extends JFrame {
    private JPanel panelBuzon;
    private JPanel panelConfiguracion;
    private JTabbedPane pestanas;
    private Usuario usuarioPropietario;
    private List<Perfume> listaProductos;

    public VentanaCorreo(Usuario usuario, List<Perfume> productos) {
        this.usuarioPropietario = usuario;
        this.listaProductos = productos;

        setTitle("Usuario: " + usuario.getNombre());
        setSize(450, 600);
        setLayout(new BorderLayout());

        inicializarComponentes();

        setLocation((int)(Math.random() * 600) + 400, (int)(Math.random() * 400));
        setVisible(true);
    }

    private void inicializarComponentes() {
        pestanas = new JTabbedPane();

        // Pestaña 1: Buzón
        panelBuzon = new JPanel();
        panelBuzon.setLayout(new BoxLayout(panelBuzon, BoxLayout.Y_AXIS));
        panelBuzon.setBackground(new Color(245, 245, 250));

        JScrollPane scrollBuzon = new JScrollPane(panelBuzon);
        scrollBuzon.getVerticalScrollBar().setUnitIncrement(16);
        pestanas.addTab("Mis Notificaciones", scrollBuzon);

        // Pestaña 2: Configuración con Botones
        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new BoxLayout(panelConfiguracion, BoxLayout.Y_AXIS));
        panelConfiguracion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        generarListaSuscripciones();

        JScrollPane scrollConfig = new JScrollPane(panelConfiguracion);
        scrollConfig.getVerticalScrollBar().setUnitIncrement(16);
        pestanas.addTab("Catálogo y Suscripciones", scrollConfig);

        add(pestanas, BorderLayout.CENTER);
    }

    private void generarListaSuscripciones() {
        panelConfiguracion.add(new JLabel("Gestiona tus suscripciones:"));
        panelConfiguracion.add(Box.createVerticalStrut(15));

        for (Perfume p : listaProductos) {
            JPanel tarjeta = new JPanel(new BorderLayout(10, 0));
            tarjeta.setMaximumSize(new Dimension(400, 70));
            tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));

            // Imagen pequeña
            JLabel lblImg = new JLabel(p.getImagen());

            // Nombre del producto
            JLabel lblNombre = new JLabel(p.getNombre());
            lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));

            // BOTÓN DE ACCIÓN
            JButton btnAccion = new JButton("Suscribirse");
            btnAccion.setBackground(new Color(220, 255, 220)); // Verde claro
            btnAccion.setFocusPainted(false);

            btnAccion.addActionListener(e -> {
                if (btnAccion.getText().equals("Suscribirse")) {
                    p.agregarObservador(usuarioPropietario);
                    btnAccion.setText("Dejar de seguir");
                    btnAccion.setBackground(new Color(255, 200, 200)); // Rojo claro
                } else {
                    p.eliminarObservador(usuarioPropietario);
                    btnAccion.setText("Suscribirse");
                    btnAccion.setBackground(new Color(220, 255, 220)); // Verde claro
                }
            });

            tarjeta.add(lblImg, BorderLayout.WEST);
            tarjeta.add(lblNombre, BorderLayout.CENTER);
            tarjeta.add(btnAccion, BorderLayout.EAST);

            panelConfiguracion.add(tarjeta);
            panelConfiguracion.add(Box.createVerticalStrut(5));
        }
    }

    public void recibirNotificacion(String producto, String mensaje, ImageIcon imagen) {
        JPanel tarjeta = new JPanel(new BorderLayout(15, 10));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
        ));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setMaximumSize(new Dimension(380, 90));

        JLabel lblImg = new JLabel(imagen);

        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        String html = String.format("<html><font color='#888888'>[%s]</font> <b>%s</b><br>%s</html>",
                hora, producto, mensaje);
        JLabel lblTexto = new JLabel(html);

        tarjeta.add(lblImg, BorderLayout.WEST);
        tarjeta.add(lblTexto, BorderLayout.CENTER);

        panelBuzon.add(tarjeta, 0);
        panelBuzon.revalidate();
        panelBuzon.repaint();

        pestanas.setSelectedIndex(0);
        toFront();
    }
}