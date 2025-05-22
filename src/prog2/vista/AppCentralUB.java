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
    private JTextField demandaPotencia;
    private JTextField nDia;
    private JTextField guanysAcumulats;
    private final Adaptador adaptador = new Adaptador();


    public AppCentralUB() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        nDia.setText("Dia: " + adaptador.getDia());
        demandaPotencia = gene
        nDia.setText("Demanda de potencia: " + adaptador.());
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral frmGCC = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador, adaptador.getSistemaRefrigeracio());
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
