package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JPanel panelProductos;

    public VentanaPrincipal() {
        setTitle("Panel de Control - ADMINISTRADOR");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 10, 10)); // Grid dinámico vertical

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(BorderFactory.createTitledBorder("Inventario de Perfumes"));

        add(scroll, BorderLayout.CENTER);
        add(new JLabel("Control de Stock y Precios (Los usuarios recibirán ventanas emergentes)"), BorderLayout.SOUTH);

        configurarDatosPrueba();
    }

    private void configurarDatosPrueba() {
        Perfume p1 = new Perfume("Chanel No. 5", "Chanel", 150.0);
        Perfume p2 = new Perfume("Sauvage", "Dior", 120.0);
        Perfume p3 = new Perfume("Acqua Di Gio", "Armani", 95.0);

        Usuario u1 = new Usuario("Ana", "ana@gmail.com");
        Usuario u2 = new Usuario("Carlos", "carlos@hotmail.com");
        Usuario u3 = new Usuario("Lucía", "lucia@yahoo.com");

        p1.agregarObservador(u1);
        p1.agregarObservador(u3);

        p2.agregarObservador(u1);
        p2.agregarObservador(u2);

        p3.agregarObservador(u3);

        panelProductos.add(new PanelControlProducto(p1));
        panelProductos.add(new PanelControlProducto(p2));
        panelProductos.add(new PanelControlProducto(p3));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}