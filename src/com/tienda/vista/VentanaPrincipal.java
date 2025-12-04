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
        setTitle("ADMINISTRADOR - Tienda Perfumes");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Imágenes reales de internet
        String imgChanel = "https://fimgs.net/mdimg/perfume/375x500.640.jpg";
        String imgDior = "https://fimgs.net/mdimg/perfume/375x500.31861.jpg";
        String imgArmani = "https://fimgs.net/mdimg/perfume/375x500.418.jpg";
        String imgOne = "https://fimgs.net/mdimg/perfume/375x500.3747.jpg";

        inventario = new ArrayList<>();
        inventario.add(new Perfume("Chanel No. 5", "Chanel", 150.0, imgChanel));
        inventario.add(new Perfume("Sauvage", "Dior", 120.0, imgDior));
        inventario.add(new Perfume("Acqua Di Gio", "Armani", 95.0, imgArmani));
        inventario.add(new Perfume("One Million", "Paco Rabanne", 85.0, imgOne));

        inicializarComponentes();
        crearUsuariosDePrueba();
    }

    private void inicializarComponentes() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 5, 5));

        for (Perfume p : inventario) {
            panelProductos.add(new PanelControlProducto(p));
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        add(scroll, BorderLayout.CENTER);
    }

    private void crearUsuariosDePrueba() {
        new Usuario("Ana", "ana@gmail.com", inventario);
        new Usuario("Carlos", "carlos@hotmail.com", inventario);
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