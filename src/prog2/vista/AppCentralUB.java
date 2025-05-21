package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    private JPanel panell;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarIC;
    private JButton btnFiDia;
    private JButton btnGCDades;
    private final Adaptador adaptador;
    private final SistemaRefrigeracio sistemaRefrigeracio;

    public AppCentralUB() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        adaptador = new Adaptador();
        sistemaRefrigeracio = adaptador.getSistemaRefrigeracio();
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral frmGCC = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador, sistemaRefrigeracio);
                frmGCC.setVisible(true);
            }
        });
        btnVisualitzarIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio frmVIC = new FrmVisualitzarInformacio(AppCentralUB.this, adaptador);
                frmVIC.setVisible(true);
            }
        });
        btnFiDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnGCDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCUB = new AppCentralUB();
            appCUB.setVisible(true);
        });
    }
}
