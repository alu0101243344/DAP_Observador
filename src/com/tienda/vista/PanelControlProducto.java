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

    public PanelControlProducto(Perfume perfume) {
        this.perfume = perfume;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lblInfo = new JLabel(perfume.toString());
        txtPrecio = new JTextField(String.valueOf(perfume.getPrecio()), 10);
        btnActualizarPrecio = new JButton("Actualizar Precio");
        chkStock = new JCheckBox("En Stock");

        btnActualizarPrecio.addActionListener(e -> actualizarPrecio());
        chkStock.addActionListener(e -> actualizarStock());

        add(new JLabel("Producto: "));
        add(lblInfo);
        add(new JLabel(" | Precio: "));
        add(txtPrecio);
        add(btnActualizarPrecio);
        add(chkStock);
    }

    private void actualizarPrecio() {
        try {
            double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
            perfume.setPrecio(nuevoPrecio);
            lblInfo.setText(perfume.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido");
        }
    }

    private void actualizarStock() {
        perfume.setStock(chkStock.isSelected());
    }
}