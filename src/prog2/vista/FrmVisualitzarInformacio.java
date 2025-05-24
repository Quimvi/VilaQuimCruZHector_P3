package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FrmVisualitzarInformacio extends JDialog{
    private JComboBox cmboxOpcionsVisualitzar;
    private JButton btnVisualitzar;
    private JPanel panell;

    public FrmVisualitzarInformacio(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Visualitzar Informació Central");
        setContentPane(panell);
        setSize(300, 100);
        setLocationRelativeTo(parent);
        setModal(true);

        btnVisualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Estat de la central")) {
                    JOptionPane.showMessageDialog(panell, adaptador.getEstatActual());
                }
                else if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Quadern de bitàcola")){
                    JOptionPane.showMessageDialog(panell, adaptador.getBitacolaCompleta());
                }
                else if (cmboxOpcionsVisualitzar.getSelectedItem().toString().equals("Incidències")){
                    JOptionPane.showMessageDialog(panell, adaptador.getIncidencies());
                }

            }
        });
    }
}
