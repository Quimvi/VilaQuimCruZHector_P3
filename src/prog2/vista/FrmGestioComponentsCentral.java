package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel panell;
    private JTextField txtIntroduirInsercioBarresControl;
    private JSlider sldBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JButton btnActivatDesactivatButton;
    private JButton btnCancelarModificacions;
    private JButton btnAplicarModificacions;
    private JComboBox bomba;
    private JTextArea txtArea;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador) {
        super(parent);
        setTitle("Gestió Components Central");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        txtIntroduirInsercioBarresControl.setText(String.valueOf(adaptador.getInsercioBarres()));
        btnActivatDesactivatButton.setText("Activat");
        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txtIntroduirInsercioBarresControl.setText(sldBarresControl.getValue()+"");
            }
        });
        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sldBarresControl.setValue(Integer.parseInt(txtIntroduirInsercioBarresControl.getText()));
            }
        });
        btnActivatDesactivatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnActivatDesactivatButton.getText().equals("Activat")) {
                    btnActivatDesactivatButton.setText("Desactivat");
                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivat")) {
                    btnActivatDesactivatButton.setText("Activat");
                }
            }
        });
        btnAplicarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.setInsercioBarres(Integer.parseInt(txtIntroduirInsercioBarresControl.getText()));

                if (btnActivatDesactivatButton.getText().equals("Activat")) {
                    adaptador.desactivaReactor();
                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivat")) {
                    adaptador.activaReactor();
                }
                dispose();
            }
        });
        btnCancelarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
