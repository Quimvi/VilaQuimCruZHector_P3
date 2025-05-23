package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuardarCarregar extends JDialog {
    private JPanel panell;
    private JButton btnCarregar;
    private JButton btnGuardar;

    public GuardarCarregar(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Guardar i carregar");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        btnCarregar.setText("Carregar");
        btnGuardar.setText("Guardar");


        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser seleccionadorArxiu = new JFileChooser();
                File fitxer;
                int resultat = seleccionadorArxiu.showSaveDialog(null);
                //Assegurem que hi hagi un fitxer seleccionat
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    //Obtenim el fitxer
                    fitxer = seleccionadorArxiu.getSelectedFile();
                    //Posem la ruta del fitxer al quadre de text
                    adaptador.guardaDades(fitxer.toString());
                }
            }
        });

        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser seleccionadorArxiu = new JFileChooser();
                File fitxer;
                int resultat = seleccionadorArxiu.showOpenDialog(null);
                //Assegurem que hi hagi un fitxer seleccionat
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    //Obtenim el fitxer
                    fitxer = seleccionadorArxiu.getSelectedFile();
                    //Posem la ruta del fitxer al quadre de text
                    adaptador.carregaDades(fitxer.toString());
                }
            }
        });


    }
}
