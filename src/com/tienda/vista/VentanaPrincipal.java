package com.tienda.vista;

import com.tienda.modelo.Perfume;
import com.tienda.modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private JPanel panelProductos;
    private List<Perfume> inventario;

    public VentanaPrincipal() {
        setTitle("Panel de Control - ADMINISTRADOR");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        inventario = new ArrayList<>();
        inventario.add(new Perfume("Chanel No. 5", "Chanel", 150.0));
        inventario.add(new Perfume("Sauvage", "Dior", 120.0));
        inventario.add(new Perfume("Acqua Di Gio", "Armani", 95.0));
        inventario.add(new Perfume("One Million", "Paco Rabanne", 85.0));

        inicializarComponentes();

        crearUsuariosDePrueba();
    }

    private void inicializarComponentes() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 10, 10));

        for (Perfume p : inventario) {
            panelProductos.add(new PanelControlProducto(p));
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(BorderFactory.createTitledBorder("Gestión de Inventario"));

        add(scroll, BorderLayout.CENTER);
        add(new JLabel("Control de Stock y Precios"), BorderLayout.SOUTH);
    }

    private void crearUsuariosDePrueba() {
        new Usuario("Ana", "ana@gmail.com", inventario);
        new Usuario("Carlos", "carlos@hotmail.com", inventario);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}