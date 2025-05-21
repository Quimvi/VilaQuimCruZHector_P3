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
    private JTextPane mostrarInformacio;


    public FrmVisualitzarInformacio(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Visualitzar Informació Central");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        cmboxOpcionsVisualitzar.addItem("Mostrar estat de la central");
        cmboxOpcionsVisualitzar.addItem("Cuadern de Bitàcola");
        cmboxOpcionsVisualitzar.addItem("Incidencies");

        cmboxOpcionsVisualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVisualitzar.setText((String) cmboxOpcionsVisualitzar.getSelectedItem());

            }
        });

        btnVisualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(btnVisualitzar.getText(), "Mostrar estat de la central")){
                    mostrarInformacio.setText(adaptador.getEstatActual());

                }else if (Objects.equals(btnVisualitzar.getText(), "Cuadern de Bitàcola")){
                    mostrarInformacio.setText(adaptador.getBitacolaCompleta());

                }else if (Objects.equals(btnVisualitzar.getText(), "Incidencies")){
                    mostrarInformacio.setText(adaptador.getIncidencies());
                }

            }
        });
    }
}
