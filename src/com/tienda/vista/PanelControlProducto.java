package com.tienda.vista;

import com.tienda.modelo.Perfume;
import javax.swing.*;
import java.awt.*;

public class PanelControlProducto extends JPanel {
    private Perfume perfume;
    private JLabel lblInfo;
    private JTextField txtPrecio;
    private JButton btnActualizarPrecio;
    private JCheckBox chkStock;
    private JLabel lblImagen; // Nuevo componente

    public PanelControlProducto(Perfume perfume) {
        this.perfume = perfume;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Mostramos la imagen
        lblImagen = new JLabel(perfume.getImagen());

        lblInfo = new JLabel("<html><b>" + perfume.getNombre() + "</b><br>" + perfume.getMarca() + "</html>");
        lblInfo.setPreferredSize(new Dimension(150, 40));

        txtPrecio = new JTextField(String.valueOf(perfume.getPrecio()), 6);
        btnActualizarPrecio = new JButton("Actualizar");
        chkStock = new JCheckBox("Stock");

        btnActualizarPrecio.addActionListener(e -> actualizarPrecio());
        chkStock.addActionListener(e -> actualizarStock());

        add(lblImagen);
        add(lblInfo);
        add(new JLabel(" $"));
        add(txtPrecio);
        add(btnActualizarPrecio);
        add(chkStock);
    }

    private void actualizarPrecio() {
        try {
            double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
            perfume.setPrecio(nuevoPrecio);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio erróneo");
        }
    }

    private void actualizarStock() {
        perfume.setStock(chkStock.isSelected());
    }
}