package prog2.vista;

import prog2.provaGUI.Prova;

import javax.swing.*;

public class AppCentralUB extends JFrame {
    private JPanel panell;

    public AppCentralUB() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB prova = new AppCentralUB();
            prova.setVisible(true);
        });
    }
}
