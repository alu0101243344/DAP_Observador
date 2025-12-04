package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JPanel panelProductos;
    private JPanel panelNotificaciones;
    private JTextArea areaLog;

    public VentanaPrincipal() {
        setTitle("DAP_Observador - Tienda de Perfumes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(2, 1));
        panelProductos.setBorder(BorderFactory.createTitledBorder("Gestión de Productos"));

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        panelNotificaciones = new JPanel(new BorderLayout());
        panelNotificaciones.setBorder(BorderFactory.createTitledBorder("Notificaciones a Usuarios"));
        panelNotificaciones.add(new JScrollPane(areaLog), BorderLayout.CENTER);

        add(panelProductos, BorderLayout.NORTH);
        add(panelNotificaciones, BorderLayout.CENTER);

        configurarDatosPrueba();
    }

    private void configurarDatosPrueba() {
        Perfume p1 = new Perfume("Chanel No. 5", "Chanel", 150.0);
        Perfume p2 = new Perfume("Sauvage", "Dior", 120.0);

        Usuario u1 = new Usuario("Ana", "ana@dap.com");
        Usuario u2 = new Usuario("Carlos", "carlos@dap.com");

        UsuarioLog visualLog = new UsuarioLog(areaLog);

        p1.agregarObservador(visualLog);
        p2.agregarObservador(visualLog);

        panelProductos.add(new PanelControlProducto(p1));
        panelProductos.add(new PanelControlProducto(p2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}