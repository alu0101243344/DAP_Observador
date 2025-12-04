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
        setTitle("ADMINISTRADOR - Gestión de Catálogo");
        setSize(550, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        cargarInventario();
        inicializarComponentes();
        registrarUsuarios();
    }

    private void cargarInventario() {
        inventario = new ArrayList<>();

        inventario.add(new Perfume("Valentino Extradose", "Valentino", 145.0, "valentino.jpg"));
        inventario.add(new Perfume("Acqua di Gio Parfum", "Armani", 110.0, "gio_parfum.jpg"));
        inventario.add(new Perfume("Versace Pour Homme", "Versace", 75.0, "versace.jpg"));
        inventario.add(new Perfume("Liquid Brun", "French Avenue", 60.0, "liquid.jpg"));
        inventario.add(new Perfume("Vulcan Feu", "Fragrance World", 55.0, "vulcan.jpg"));
        inventario.add(new Perfume("Xerjoff Naxos", "Xerjoff", 230.0, "naxos.jpg"));
        inventario.add(new Perfume("SWY Intensely", "Armani", 90.0, "swy.jpg"));
        inventario.add(new Perfume("Le Beau Le Parfum", "JPG", 105.0, "lebeau.jpg"));
    }

    private void inicializarComponentes() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 5, 5));

        for (Perfume p : inventario) {
            panelProductos.add(new PanelControlProducto(p));
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(BorderFactory.createTitledBorder("Inventario Actual"));

        add(scroll, BorderLayout.CENTER);
    }

    private void registrarUsuarios() {
        new Usuario("Kevin", "kevin@tienda.com", inventario);
        new Usuario("David", "david@tienda.com", inventario);
        new Usuario("Alvaro", "alvaro@tienda.com", inventario);
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