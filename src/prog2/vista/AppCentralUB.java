package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    private JPanel panell;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarIC;
    private JButton btnFiDia;
    private JButton btnGCDades;
    private Adaptador adaptador = new Adaptador();    // Mirar com obtenir l'adaptador que utilitza CentralUB

    public AppCentralUB() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral frmGCC = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador);
                frmGCC.setVisible(true);
            }
        });
        btnVisualitzarIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
