package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame{
    private JPanel panell;
    private JTextField txtMissatge;
    private JButton btnHola;
    private JButton btnAdeu;

    public AppCentralUB(){
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnHola.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMissatge.setText(btnHola.getText());
            }
        });
        btnAdeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMissatge.setText(btnAdeu.getText());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB prova = new AppCentralUB();
            prova.setVisible(true);
        });
    }
}
