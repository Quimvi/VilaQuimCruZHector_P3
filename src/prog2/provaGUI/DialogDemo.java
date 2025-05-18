package prog2.provaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDemo extends JFrame {
    private JPanel panell;
    private JTextField txtNom;
    private JTextField txtTipus;
    private JButton btnEditar;
    private Arxiu arxiu;

    public DialogDemo() {
        setTitle("App Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panell);
        setSize(500,400);
        setLocationRelativeTo(null);
        arxiu = new Arxiu();
        txtNom.setEditable(false);
        txtTipus.setEditable(false);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarDialog dialog = new EditarDialog(DialogDemo.this, arxiu);
                dialog.setVisible(true);
                txtNom.setText(arxiu.getNom());
                txtTipus.setText(arxiu.getTipus());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DialogDemo dialogDemo = new DialogDemo();
            dialogDemo.setVisible(true);
        });
    }

    public class Arxiu {
        private String nom;
        private String tipus;
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getTipus() {
            return tipus;
        }
        public void setTipus(String tipus) {
            this.tipus = tipus;
        }
    }
}