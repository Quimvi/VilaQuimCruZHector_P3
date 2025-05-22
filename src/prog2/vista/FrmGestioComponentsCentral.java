package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel panell;
    private JTextField txtIntroduirInsercioBarresControl;
    private JSlider sldBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JButton btnActivatDesactivatButton;
    private JButton btnCancelarModificacions;
    private JButton btnAplicarModificacions;
    private JTextArea txtArea;
    private JCheckBox bomba1CheckBox;
    private JCheckBox bomba3CheckBox;
    private JCheckBox bomba2CheckBox;
    private JCheckBox bomba4CheckBox;
    private ArrayList<BombaRefrigerant> llistaBomba;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador, SistemaRefrigeracio sistemaRefrigeracio) {
        super(parent);
        setTitle("Gestió Components Central");
        setContentPane(panell);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
        txtIntroduirInsercioBarresControl.setText(String.valueOf(adaptador.getInsercioBarres()));
        btnActivatDesactivatButton.setText("Activat");
        llistaBomba = sistemaRefrigeracio.getLlistaBomba();
        txtArea.setText("Bomba 1: " + (llistaBomba.get(0).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(0).getActivat() ? "activada\n" : "desactivada\n") +
                        "Bomba 2: " + (llistaBomba.get(1).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(1).getActivat() ? "activada\n" : "desactivada\n") +
                        "Bomba 3: " + (llistaBomba.get(2).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(2).getActivat() ? "activada\n" : "desactivada\n") +
                        "Bomba 4: " + (llistaBomba.get(3).getForaDeServei() ? "fora de servei" : "en servei") + " i " + (llistaBomba.get(3).getActivat() ? "activada\n" : "desactivada\n"));
        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txtIntroduirInsercioBarresControl.setText(sldBarresControl.getValue()+"");
            }
        });
        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sldBarresControl.setValue(Integer.parseInt(txtIntroduirInsercioBarresControl.getText()));   // El valor s'ha modificar o deixar igual però s'ha de tocar perquè sinó salta error
            }
        });
        btnActivatDesactivatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnActivatDesactivatButton.getText().equals("Activat")) {
                    adaptador.desactivaReactor();
                    btnActivatDesactivatButton.setText("Desactivat");
                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivat")) {
                    adaptador.activaReactor();
                    btnActivatDesactivatButton.setText("Activat");
                }
            }
        });
        btnAplicarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.setInsercioBarres(Float.parseFloat(txtIntroduirInsercioBarresControl.getText()));

                if (btnActivatDesactivatButton.getText().equals("Activat")) {
                    adaptador.desactivaReactor();
                }
                else if (btnActivatDesactivatButton.getText().equals("Desactivat")) {
                    adaptador.activaReactor();
                }

                if(bomba1CheckBox.isSelected()) {
                    if (llistaBomba.get(0).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 1 està fora de servei");
                    } else {
                        if (llistaBomba.get(0).getActivat())
                            llistaBomba.get(0).desactiva();
                        else
                            llistaBomba.get(0).activa();
                    }
                }
                if(bomba2CheckBox.isSelected()) {
                    if (llistaBomba.get(1).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 2 està fora de servei");
                    } else {
                        if (llistaBomba.get(1).getActivat())
                            llistaBomba.get(1).desactiva();
                        else
                            llistaBomba.get(1).activa();
                    }
                }
                if(bomba3CheckBox.isSelected()) {
                    if (llistaBomba.get(2).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 3 està fora de servei");
                    } else {
                        if (llistaBomba.get(2).getActivat())
                            llistaBomba.get(2).desactiva();
                        else
                            llistaBomba.get(2).activa();
                    }
                }
                if(bomba4CheckBox.isSelected()) {
                    if (llistaBomba.get(3).getForaDeServei()) {
                        JOptionPane.showMessageDialog(panell, "La bomba 4 està fora de servei");
                    } else {
                        if (llistaBomba.get(3).getActivat())
                            llistaBomba.get(3).desactiva();
                        else
                            llistaBomba.get(3).activa();
                    }
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
